package org.devops.core.model.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.devops.core.model.core.Model;
import org.devops.core.model.core.ModelHelper;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Pipeline;

@Component
// 多例
@Scope("prototype")
public class ModelRedisDao<T, K> extends AspectRedisDao{

	
	private static final String KEY_TEMPLATE_PREFIX = "DEVOPS";
	
	private Class<T> clazz;
	
	private Model<T, K> model;
	
	private List<String> keys;
	
	private String priKey = null;
	
	public String getPriKey() {
		assertModelNotNull();
		if(StringUtil.isNotEmpty(priKey)) {
			return priKey;
		}
		String priKey = model.getPriKey();
		if(StringUtil.isEmpty(priKey)) {
			priKey = "id";
		}
		return priKey;
	}
	
	public String getKey(T vo) {
		assertModelNotNull();
		String keyTemplatePrefix = KEY_TEMPLATE_PREFIX + "." + ModelHelper.toUUCase(clazz.getSimpleName()).toUpperCase();
		if(keys.size() == 0) {
			return keyTemplatePrefix;
		}
		if(vo == null) {
			throw new CommonRuntimeException("请传入正确的值:"+FastJsonUtils.toJsonString(keys));
		}
		for(String key : keys) {
			Object v = ModelHelper.getValue(vo, key);
			if(v == null) {
				throw new CommonRuntimeException("请传入正确的值:" + key);
			}
			keyTemplatePrefix += "."+key.toUpperCase() + "="+v.toString();
		}
		return keyTemplatePrefix;
	}
	
	public void assertModelNotNull() throws CommonRuntimeException {
		if(this.model == null) {
			throw new CommonRuntimeException("redis操作失败，请先配置model");
		}
	}
	
	public List<String> sortKey(String[] keys){
		List<String> liKey = Arrays.asList(keys);
		liKey.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return liKey;
	}

	public void init(Model<T, K> model, String[] key, String priKey){
		this.clazz = model.getClazz();
		this.model = model;
		if(key == null || key.length == 0) {
			this.keys = new ArrayList<>();
		} else {
			this.keys = sortKey(key);
		}
		this.priKey = priKey;
	}
	
	@Redis
	public void set(T vo){
		assertModelNotNull();
		RedisClient redis = getJedis();
		Object priValue = ModelHelper.getValue(vo, getPriKey());
		if(priValue == null) {
			throw new CommonRuntimeException("请传入正确的值:" + getPriKey());
		}
		redis.hset(getKey(vo),  priValue.toString(), FastJsonUtils.toJsonString(vo));
	}
	
	@Redis
	public T get(T vo){
		assertModelNotNull();
		RedisClient redis = getJedis();
		Object priValue = ModelHelper.getValue(vo, getPriKey());
		if(priValue == null) {
			throw new CommonRuntimeException("请传入正确的值:" + getPriKey());
		}
		String value = redis.hget(getKey(vo),  priValue.toString());
		return FastJsonUtils.getBean(value, clazz);
	}
	
	
	@Redis
	public void setList(List<T> list){
		assertModelNotNull();
		RedisClient redis = getJedis();
		
		if(list == null || list.size() == 0) {
			return;
		}
		
		if(list.size() == 1) {
			set(list.get(0));
			return;
		}
		
		for(T vo :list) {
			String key = getKey(vo);
			
			Pipeline pipeline = redis.pipeline(key);
			Object priValue = ModelHelper.getValue(vo, getPriKey());
			if(priValue == null) {
				throw new CommonRuntimeException("请传入正确的值:" + getPriKey());
			}
			pipeline.hset(key, priValue.toString(), FastJsonUtils.toJsonString(vo));
		}
		
		redis.pipelineSync();

	}
	
	@Redis
	public void clear(T vo){
		assertModelNotNull();
		
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		redis.del(key);
	}
	
	@Redis
	public void delete(T vo){
		assertModelNotNull();
		
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		Object priValue = ModelHelper.getValue(vo, getPriKey());
		if(priValue == null) {
			throw new CommonRuntimeException("请传入正确的值:" + getPriKey());
		}
		
		redis.hdel(key, priValue.toString());
	}
	
	@Redis
	public void delete(T vo,String[] keys){
		assertModelNotNull();
		
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		redis.hdel(key, keys);
	}
	
	@Redis
	public void delete(T vo,List<T> list){
		assertModelNotNull();
		
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		List<String> keys = new ArrayList<String>();
		for(T o : list) {
			Object priValue = ModelHelper.getValue(o, getPriKey());
			if(priValue == null) {
				continue;
			}
			keys.add(priValue.toString());
		}
		if(keys.size() > 0) {
			redis.hdel(key, keys.toArray(new String[keys.size()]));
		}
	}
	
	/**
	 * 此方法，数据量大于 200以上禁止使用，会有性能问题
	 * @param vo
	 * @return
	 */
	@Redis
	public List<T> listAll(T vo){
		
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		List<T> list = new ArrayList<>();
		Map<String,String> map =  redis.hgetAll(key);
		for(String itemKey : map.keySet()) {
			String value = map.get(itemKey);
			list.add(FastJsonUtils.getBean(value, clazz));
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	@Redis
	public List<T> list(T vo, Object keys){
		assertModelNotNull();
		List<String> strKeys = new ArrayList<>();
		if(keys instanceof List) {
			for(Object o : (List)keys) {
				strKeys.add(o.toString());
			}
		} else {
			strKeys.add(keys.toString());
		}
		return list(vo,strKeys.toArray(new String[strKeys.size()]));
	}
	
	@Redis
	public List<T> list(T vo,String ... fields){
		assertModelNotNull();
		RedisClient redis = getJedis();
		
		String key = getKey(vo);
		
		List<String> liValue = redis.hmget(key, fields);
		
		List<T> result = new ArrayList<>();
		for(String value : liValue) {
			if(StringUtil.isEmpty(value)) {
				continue;
			}
			result.add(FastJsonUtils.getBean(value, clazz));
		}
		
		return result;
	}
	
}
