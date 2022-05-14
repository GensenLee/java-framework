package org.devops.core.model.interfaces;

import java.util.List;

import org.devops.core.model.data.ModelData;

public interface IModelRedis<T, K> {
	
	public ModelData<T, K> listAllByRedis();
	
	public void clearRedis();

	public void clearRedis(T vo);

	public String getRealRedisKey(T vo);
	
	public void deleteRedis(String priKey);
	
	public void deleteRedis(Integer priKey);
	
	public void deleteRedis(Long priKey);
	
	public void deleteRedis(String[] keys);
	
	public void deleteRedis(List<T> list);
	
	public void updateRedis(T vo);
	
	public void updateListRedis(List<T> list);
	
	public T getByRedis(String priKey);
	
	public T getByRedis(Integer priKey);
	
	public T getByRedis(Long priKey);
	
	public List<T> listByRedis(String ...keys);
	
	@SuppressWarnings("rawtypes")
	public List<T> listByRedis(List keys);
}
