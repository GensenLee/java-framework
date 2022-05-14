package org.devops.mjar.autotest.core;

import java.util.List;

import org.devops.core.utils.vo.ApiResult;
import org.devops.core.utils.vo.PageResult;

public interface ITestRestCore {

	public <T> ApiResult<T> get(String url,Class<T> clazz);
	
	public <T> ApiResult<T> get(String url,Object params,Class<T> clazz);
	
	public <T> ApiResult<List<T>> getList(String url,Class<T> clazz);
	
	public <T> ApiResult<List<T>> getList(String url,Object params,Class<T> clazz);
	
	public <T> ApiResult<PageResult<T>> getPage(String url,Class<T> clazz);
	
	public <T> ApiResult<PageResult<T>> getPage(String url,Object params,Class<T> clazz);
	
	public <T> ApiResult<Object> post(String url,Object params);
	
	public <T> ApiResult<T> post(String url,Class<T> clazz);
	
	public <T> ApiResult<T> post(String url,Object params,Class<T> clazz);
	
	public <T> ApiResult<List<T>> postList(String url,Class<T> clazz);
	
	public <T> ApiResult<List<T>> postList(String url,Object params,Class<T> clazz);
	
	public <T> ApiResult<PageResult<T>> postPage(String url,Class<T> clazz);
	
	public <T> ApiResult<PageResult<T>> postPage(String url,Object params,Class<T> clazz);
	
	@SuppressWarnings("rawtypes")
	public <T> ApiResult<List<T>> returnList(ApiResult result,Class<T> clazz);
	
	@SuppressWarnings("rawtypes")
	public <T> ApiResult<T> returnObject(ApiResult result,Class<T> clazz);
}
