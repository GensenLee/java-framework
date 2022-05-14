package org.devops.core.model.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.devops.core.model.annotation.ModelAutowired;
import org.devops.core.model.annotation.ModelRedis;
import org.devops.core.model.data.ModelData;
import org.devops.core.model.dto.ModelSet;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.model.interfaces.BaseModelDao;
import org.devops.core.model.interfaces.IModel;
import org.devops.core.model.interfaces.IModelRedis;
import org.devops.core.model.redis.ModelRedisDao;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import lombok.Data;

public abstract class AbstractModelRepository<T, K> implements IModel<T, K>, IModelRedis<T, K>,InitializingBean {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ThreadLocal<RedisModelVO> redisModelHolder = new ThreadLocal<RedisModelVO>();

	@ModelAutowired
	private Model<T, K> model;

	@Autowired
	private ModelRedisDao<T, K> modelRedisDao;

	protected IModel<T, K> getModel() {
		return model;
	}

	protected ModelRedisDao<T, K> getRedis() {
		return modelRedisDao;
	}
	
	public Class<T> getClazz() {
		return this.model.getClazz();
	}

	/**
	 * redis隔离key
	 * 
	 * @return
	 */
	protected String[] getRedisKey() {
		ModelRedis modelRedis = AnnotationUtils.findAnnotation(this.getClass(), ModelRedis.class);
		if(modelRedis != null) {
			return modelRedis.redisKey();
		}
		return null;
	}
	
	/**
	 * redis主键
	 * 
	 * @return
	 */
	protected String getRedisPriKey() {
		ModelRedis modelRedis = AnnotationUtils.findAnnotation(this.getClass(), ModelRedis.class);
		if(modelRedis != null && StringUtil.isNotEmpty(modelRedis.redisPriKey())) {
			return modelRedis.redisPriKey();
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		modelRedisDao.init(model, getRedisKey(), getRedisPriKey());
	}

	@Override
	public List<T> list() {
		return getModel().list();
	}

	@Override
	public List<T> list(List<K> keys) {
		return getModel().list(keys);
	}

	@Override
	public <T2> List<T2> list(Class<T2> clazz) {
		return getModel().list(clazz);
	}

	@Override
	public T get() {
		return getModel().get();
	}

	@Override
	public T get(K key) {
		return getModel().get(key);
	}

	@Override
	public <T2> T2 get(Class<T2> clazz) {
		return getModel().get(clazz);
	}

	@Override
	public boolean isExists() {
		return getModel().isExists();
	}

	@Override
	public boolean isNotExists() {
		return getModel().isNotExists();
	}

	@Override
	public boolean isExists(K value) {
		return getModel().isExists(value);
	}

	@Override
	public boolean isNotExists(K value) {
		return getModel().isNotExists(value);
	}

	@Override
	public long count() {
		return getModel().count();
	}

	@Override
	public <T2> List<T2> listSingle(Class<T2> clazz) {
		return getModel().listSingle(clazz);
	}

	@Override
	public <T2> T2 getSingle(Class<T2> clazz) {
		return getModel().getSingle(clazz);
	}

	@Override
	public String getString(K value) {
		return getModel().getString(value);
	}

	@Override
	public String getString() {
		return getModel().getString();
	}

	@Override
	public Integer getInteger(K value) {
		return getModel().getInteger(value);
	}

	@Override
	public Integer getInteger() {
		return getModel().getInteger();
	}

	@Override
	public List<String> listString() {
		return getModel().listString();
	}

	@Override
	public List<Integer> listInteger() {
		return getModel().listInteger();
	}

	@Override
	public IModel<T, K> ignoreOnce(boolean ignoreOnce) {
		getModel().ignoreOnce(ignoreOnce);
		return this;
	}

	@Override
	public IModel<T, K> closeLog() {
		getModel().closeLog();
		return this;
	}
	
	@Override
	public IModel<T, K> closeCreateTable() {
		getModel().closeCreateTable();
		return this;
	}

	@Override
	public IModel<T, K> tableSuffix(String suffix) {
		getModel().tableSuffix(suffix);
		return this;
	}

	@Override
	public IModel<T, K> tableSuffix(String suffixPrefix, String suffix) {
		getModel().tableSuffix(suffixPrefix, suffix);
		return this;
	}
	
	@Override
	public String getTableSuffix() {
		return getModel().getTableSuffix();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public IModel<T, K> limit(int size) {
		getModel().limit(size);
		return this;
	}

	@Override
	public IModel<T, K> limit(int start, int size) {
		getModel().limit(start, size);
		return this;
	}

	@Override
	public IModel<T, K> distinct() {
		getModel().distinct();
		return this;
	}

	@Override
	public IModel<T, K> orderBy(String orderBy) {
		getModel().orderBy(orderBy);
		return this;
	}

	@Override
	public IModel<T, K> orderBy(String... args) {
		getModel().orderBy(args);
		return this;
	}

	@Override
	public IModel<T, K> groupBy(String groupBy) {
		getModel().groupBy(groupBy);
		return this;
	}

	@Override
	public IModel<T, K> fields(String fields) {
		getModel().fields(fields);
		return this;
	}

	@Override
	public IModel<T, K> include(String fields) {
		getModel().include(fields);
		return this;
	}

	@Override
	public IModel<T, K> include(String... args) {
		getModel().include(args);
		return this;
	}

	@Override
	public IModel<T, K> include(String[] arrArgs, String... args) {
		getModel().include(arrArgs, args);
		return this;
	}

	@Override
	public IModel<T, K> exclude(String fields) {
		getModel().exclude(fields);
		return this;
	}

	@Override
	public IModel<T, K> exclude(String... args) {
		getModel().exclude(args);
		return this;
	}

	@Override
	public IModel<T, K> exclude(String[] arrArgs, String... args) {
		getModel().exclude(arrArgs, args);
		return this;
	}

	//////////////////////////////////////////////////////////////////////////////////
	// where start
	//////////////////////////////////////////////////////////////////////////////////

	@Override
	public IModel<T, K> or(String column, Object value) {
		getModel().or(column, value);
		return this;
	}

	@Override
	public IModel<T, K> and(String column, Object value) {
		getModel().and(column, value);
		return this;
	}

	@Override
	public IModel<T, K> where(String column, Object value) {
		getModel().where(column, value);
		return this;
	}

	@Override
	public IModel<T, K> where(String column, Object value, ModelOperator mo) {
		getModel().where(column, value, mo);
		return this;
	}

	@Override
	public IModel<T, K> where(String column, Object value, ModelCondition mc) {
		getModel().where(column, value, mc);
		return this;
	}

	@Override
	public IModel<T, K> where(ModelWhere mw) {
		getModel().where(mw);
		return this;
	}

	@Override
	public IModel<T, K> where(Object value) {
		getModel().where(value);
		return this;
	}

	@Override
	public IModel<T, K> where(ModelWhere mw, ModelCondition mc) {
		getModel().where(mw, mc);
		return this;
	}

	@Override
	public IModel<T, K> where(String column, ModelOperator mo) {
		getModel().where(column, mo);
		return this;
	}

	@Override
	public IModel<T, K> where(String column, Object value, ModelOperator mo, ModelCondition mc) {
		getModel().where(column, value, mo, mc);
		return this;
	}

	@Override
	public IModel<T, K> whereFormat(String whereStr, Object... args) {
		getModel().whereFormat(whereStr, args);
		return this;
	}

	@Override
	public IModel<T, K> whereFormat(ModelCondition mc, String whereStr, Object... args) {
		getModel().whereFormat(mc, whereStr, args);
		return this;
	}

	@Override
	public IModel<T, K> whereSQL(String sql) {
		getModel().whereSQL(sql);
		return this;
	}

	/////////////////////////////////////////////////////////////////////
	// where end
	/////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////
	// join start
	/////////////////////////////////////////////////////////////////////

	@Override
	public IModel<T, K> join(Object source, String sourceColumn, String sourceColumnAlias, String... targetColumn) {
		getModel().join(source, sourceColumn, sourceColumnAlias, targetColumn);
		return this;
	}

	/////////////////////////////////////////////////////////////////////
	// join end
	/////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////
	// insert
	/////////////////////////////////////////////////////////////////////
	
	@Override
	public long insertByObject(Object insertModel, boolean ignorePriKey) {
		return getModel().insertByObject(insertModel, ignorePriKey);
	}

	@Override
	public long insertByObject(Object insertModel) {
		return getModel().insertByObject(insertModel);
	}

	@Override
	public long insert(T insertModel, boolean ignorePriKey) {
		return getModel().insert(insertModel, ignorePriKey);
	}

	@Override
	public long insert(T insertModel) {
		return getModel().insert(insertModel);
	}
	
	@Override
	public long insert(List<T> insertModel, boolean ignorePriKey) {
		return getModel().insert(insertModel, ignorePriKey);
	}

	@Override
	public long insert(List<T> insertModel) {
		return getModel().insert(insertModel);
	}

	public <T> T processAutoId(T insertModel) {
		return (T) getModel().preprocessInsert(insertModel);
	}

	public <T> List<T> processAutoId(List<T> insertModel) {
		return (List<T>) getModel().preprocessInsert(insertModel);
	}

	@Override
	public IModel<T, K> add(String column, Object value) {
		getModel().add(column, value);
		return this;
	}

	@Override
	public IModel<T, K> add(String column, String valueColumn, Object value) {
		getModel().add(column, valueColumn, value);
		return this;
	}

	@Override
	public IModel<T, K> sub(String column, Object value) {
		getModel().sub(column, value);
		return this;
	}

	@Override
	public IModel<T, K> sub(String column, String valueColumn, Object value) {
		getModel().sub(column, valueColumn, value);
		return this;
	}

	@Override
	public IModel<T, K> mcl(String column, Object value) {
		getModel().mcl(column, value);
		return this;
	}

	@Override
	public IModel<T, K> mcl(String column, String valueColumn, Object value) {
		getModel().mcl(column, valueColumn, value);
		return this;
	}

	@Override
	public IModel<T, K> divide(String column, Object value) {
		getModel().divide(column, value);
		return this;
	}

	@Override
	public IModel<T, K> divide(String column, String valueColumn, Object value) {
		getModel().divide(column, valueColumn, value);
		return this;
	}

	@Override
	public long update() {
		return getModel().update();
	}

	@Override
	public long update(String column, Object value) {
		return getModel().update(column, value);
	}

	@Override
	public long update(ModelSet updateModel) {
		return getModel().update(updateModel);
	}
	
	@Override
	public long updateByObject(Object updateModel) {
		return getModel().updateByObject(updateModel);
	}

	@Override
	public long update(T updateModel) {
		return getModel().update(updateModel);
	}
	
	@Override
	public long update(List<T> updateModel) {
		return getModel().update(updateModel);
	}

	// 保存
	@Override
	public long saveByObject(Object saveModel) {
		return getModel().saveByObject(saveModel);
	}
	
	@Override
	public long save(T saveModel) {
		return getModel().save(saveModel);
	}
	
	@Override
	public long save(List<T> saveModel) {
		return getModel().save(saveModel);
	}

	@Override
	public long delete(K value) {
		return getModel().delete(value);
	}

	@Override
	public long delete() {
		return getModel().delete();
	}

	////////////////////////////////////////////////////////
	@Override
	public List<Map<String, Object>> query(String sql) {
		return getModel().query(sql);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Map<String, Object> parameter) {
		return getModel().query(sql, parameter);
	}

	@Override
	public ModelSelect createSelect() {
		return getModel().createSelect();
	}

	@Override
	public void init() {
		getModel().init();
	}

	@Override
	public long count(K key) {
		return getModel().count(key);
	}

	@Override
	public Long getLong(K value) {
		return getModel().getLong(value);
	}

	@Override
	public Long getLong() {
		return getModel().getLong();
	}

	@Override
	public List<Long> listLong() {
		return getModel().listLong();
	}

	@Override
	public String deleteSQL() {
		return getModel().deleteSQL();
	}

	@Override
	public void setModelDao(BaseModelDao modelDao) {
		getModel().setModelDao(modelDao);

	}


	////////////////////////////////////////////////////////////////////////////////////////// \
	// redis 方法
	
	/**
	 * args 对应的key 值必须经过字段排序
	 * @param args
	 * @return
	 */
	private T getRedisModel(Object ...args){
		if(getRedisKey() != null && getRedisKey().length != args.length) {
			throw new CommonRuntimeException("redis参数个数错误");
		}
		if(args.length == 0) {
			return null;
		}
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				throw new CommonRuntimeException("redis参数不能为空");
			}
		}
		T vo;
		try {
			vo = model.getClazz().newInstance();
			List<String> keys = modelRedisDao.sortKey(getRedisKey());
			for(int i = 0; i < keys.size(); i++) {
				ModelHelper.setValue(vo, keys.get(i), args[i]);
			}
		} catch (Exception e) {
			throw new CommonRuntimeException(e);
		} 
		return vo;
	}
	
	protected IModelRedis<T, K> redis(Object ... args) {
		return redis(getRedisModel(args));
	}
	
	protected IModelRedis<T, K> redis(T redisModel) {
		RedisModelVO redisModelVO = new RedisModelVO();
		redisModelVO.setRedisModel(redisModel);
		redisModelHolder.set(redisModelVO);
		return this;
	}
	
	public boolean hasRedis() {
		Method method = BeanUtil.findMethod(this.getClass(), "redis");
		if(method == null || !method.getReturnType().isAssignableFrom(IModelRedis.class)) {
			return false;
		}
		return true;
	}
	
	private void assertRedisKeyNotNull() throws CommonRuntimeException {

		if(!hasRedis() || (redisModelHolder.get() == null && getRedisKey() != null && getRedisKey().length != 0) ) {
			throw new CommonRuntimeException("redis操作失败，请先调用redis");
		}
	}
	
	private T getLocalRedisModel() {
		assertRedisKeyNotNull();
		if(getRedisKey() == null || getRedisKey().length == 0) {
			return null;
		}
		return redisModelHolder.get().getRedisModel();
	}
	
	private void clearLocalRedisModel() {
		redisModelHolder.remove();
	}
	
	
	public List<T> listAllByRedisForBaseData() {
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			return listAllByRedisForBaseData(redisModel);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	private List<T> listAllByRedisForBaseData(T redisModel) {
		ModelWhere mw = new ModelWhere();
		if(getRedisKey() != null && getRedisKey().length > 0) {
			for(String key : getRedisKey()) {
				mw.add(key,ModelHelper.getValue(redisModel, key));
			}
		}
		List<T> result = new ArrayList<>();
		List<T> r = null;
		int begin = 0, size = 5000;
		while(true) {
			r = this.where(mw).limit(begin, size).list();
			result.addAll(r);
			begin += size;
			if(r.size() == 0 || r.size() < size) {
				r.clear();
				break;
			}
			r.clear();
		}
		return result;
	}
	
	
	/**
	 * 此方法，数据量大于 200以上禁止使用，会有性能问题
	 * @param vo
	 * @return
	 */
	@Override
	public ModelData<T, K> listAllByRedis(){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			
			List<T> list = new ArrayList<>();
			list = modelRedisDao.listAll(redisModel);
			if(CollectionUtils.isEmpty(list)) {
				list = listAllByRedisForBaseData(redisModel);
				modelRedisDao.setList(list);
			}
			return new ModelData<T, K>(list);
			
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public void clearRedis(){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			modelRedisDao.clear(redisModel);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}

  @Override
  public void clearRedis(T vo){
    try {
      redis(vo);
      assertRedisKeyNotNull();
      T redisModel = getLocalRedisModel();
      modelRedisDao.clear(redisModel);
    } catch (RuntimeException re) {
      throw re;
    } finally {
      clearLocalRedisModel();
    }
  }

	@Override
	public String getRealRedisKey(T vo) {
		 return modelRedisDao.getKey(vo);
	}
	
	@Override
	public void deleteRedis(Integer priKey){
		deleteRedisByPriKey(priKey);
	}
	
	@Override
	public void deleteRedis(String priKey){
		deleteRedisByPriKey(priKey);
	}
	
	@Override
	public void deleteRedis(Long priKey){
		deleteRedisByPriKey(priKey);
	}
	
	
	
	private void deleteRedisByPriKey(Object priKey){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			ModelHelper.setValue(redisModel, modelRedisDao.getPriKey(), priKey);
			modelRedisDao.delete(redisModel);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public void deleteRedis(String[] keys){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			modelRedisDao.delete(redisModel, keys);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public void deleteRedis(List<T> list){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			modelRedisDao.delete(redisModel, list);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public void updateRedis(T vo){
		try {
			redis(vo);
			assertRedisKeyNotNull();
			modelRedisDao.set(vo);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	public void updateRedisByPriKey(K priKey) {
		updateRedis(this.get(priKey));
	}
	
	@Override
	public void updateListRedis(List<T> list){
		try {
			modelRedisDao.setList(list);
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public T getByRedis(String priKey) {
		return getByRedisByPriKey(priKey);
	}
	
	@Override
	public T getByRedis(Integer priKey) {
		return getByRedisByPriKey(priKey);
	}
	
	@Override
	public T getByRedis(Long priKey) {
		return getByRedisByPriKey(priKey);
	}
	
	
	private T getByRedisByPriKey(Object priKey) {
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			if(redisModel == null) {
				try {
					redisModel = model.getClazz().newInstance();
				} catch (Exception e) {
					throw new CommonRuntimeException(e);
				} 
			}
			ModelHelper.setValue(redisModel, modelRedisDao.getPriKey(), priKey);

			T result = modelRedisDao.get(redisModel);
			
			if(result == null) {
				// 做一次数据库穿透
				result = this.tableSuffix(this.getTableSuffix()).where(modelRedisDao.getPriKey(),priKey).get();
			}
			
			if(result == null) {
				return null;
			}
			
			modelRedisDao.set(result);
			
			return result;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<T> listByRedis(List keys) {
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			List<T> liResult = modelRedisDao.list(redisModel, keys);
			if(CollectionUtils.isEmpty(liResult)) {
				liResult = listAllByRedis().where(modelRedisDao.getPriKey(), keys).list();
			}
			return liResult;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Override
	public List<T> listByRedis(String ...keys){
		try {
			assertRedisKeyNotNull();
			T redisModel = getLocalRedisModel();
			List<T> liResult = modelRedisDao.list(redisModel, keys);
			if(CollectionUtils.isEmpty(liResult)) {
				liResult = listAllByRedis().where(modelRedisDao.getPriKey(), keys).list();
			}
			return liResult;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			clearLocalRedisModel();
		}
	}
	
	@Data
	protected class RedisModelVO {
		private T redisModel;
	}
}
