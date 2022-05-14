package org.devops.core.utils.modules.apiEnhancer.redis;

import org.devops.core.utils.modules.redis.AspectRedisDao;
import org.devops.core.utils.modules.redis.annotation.Redis;
import org.devops.core.utils.modules.redis.client.RedisClient;
import org.springframework.stereotype.Component;

@Component
public class ApiEnhancerCreateRedisDao extends AspectRedisDao{

	private String CREATE_KEY = "REDIS.LINKKAP.API_ENHANCER.CREATE.KEY.%s";
	
	private String CREATE_RESULT_KEY = "REDIS.LINKKAP.API_ENHANCER.CREATE_RESULT.KEY.%s";
	
	@Redis
	public boolean lock(String key,long expire) {
		RedisClient jedis = getJedis();
		String k = String.format(CREATE_KEY , key);
		if(!jedis.exists(k)) {
			jedis.expire(k, (int)(expire / 1000));
			return true;
		}
		return false;
	}
	
	@Redis
	public void set(String key,String value, long expire){
		RedisClient jedis = getJedis();
		String k = String.format(CREATE_RESULT_KEY , key);
		jedis.setex(k, (int)(expire / 1000), value);
	}
	
	@Redis
	public String get(String key){
		RedisClient jedis = getJedis();
		String k = String.format(CREATE_RESULT_KEY , key);
		return jedis.get(k);
	}
}
