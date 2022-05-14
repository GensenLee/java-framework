package org.devops.mjar.autotest.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.core.utils.vo.PageResult;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class TestRestCore extends TestRestTemplate implements ITestRestCore {
	
	private ThreadLocal<String> sessionKeyHolder = new ThreadLocal<String>();
	
	public void setSessionKey(String sessionKey){
		sessionKeyHolder.set(sessionKey);
	}
	
	public String getSessionKey(){
		return sessionKeyHolder.get();
	}
	
	public void clear(){
		sessionKeyHolder.remove();
	}
	
	public TestRestCore(RestTemplate restTemplate, String username, String password,HttpClientOption... httpClientOptions){
		super(username,password,httpClientOptions);
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	private ApiResult getForApiResult(String url,Object params){
		Map paramerter = null;
		Object source = params;
		if(source != null && !(source instanceof Map)){
			source = FastJsonUtils.getBean(FastJsonUtils.toJsonString(source), Map.class);
		}
		if(source instanceof Map){
			paramerter = (Map)source;
		}
		
		if(paramerter != null){
			List<String> listParams = new ArrayList<String>();
			for(Object key : paramerter.keySet()){
				Object v = paramerter.get(key);
				if(v == null){
					continue;
				}
				if(BeanUtil.isType(params, key.toString(), Date.class)){
					//如果是date 那么转成 yyyy/MM/dd HH:mm:ss
					v = DateUtil.getDateTimeFormat((Date)BeanUtil.getValue(params,key.toString())).replaceAll("-", "/");
				}
				listParams.add(key.toString()+"="+v.toString());
			}
			if(!listParams.isEmpty()){
				if(url.contains("?")){
					url += "&";
				}else{
					url += "?";
				}
				url+=ListUtil.join(listParams,"&");
			}
		}
		return this.getForObject(url, ApiResult.class);
	}
	
	private HttpHeaders createHttpHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("session-key", getSessionKey());
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return headers;
	}
	
	@Override
	public <T> ApiResult<T> get(String url,Class<T> clazz){
		return get(url,null,clazz);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public <T> ApiResult<T> get(String url,Object params,Class<T> clazz){
		ApiResult result = getForApiResult(url,params);
		
		return returnObject(result,clazz);
	}
	
	@Override
	public <T> ApiResult<List<T>> getList(String url,Class<T> clazz){
		return getList(url,clazz);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public <T> ApiResult<List<T>> getList(String url,Object params,Class<T> clazz){
		ApiResult result = getForApiResult(url,params);
		
		return returnList(result,clazz);
	}
	
	@Override
	public <T> ApiResult<PageResult<T>> getPage(String url,Class<T> clazz){
		return getPage(url,null,clazz);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public <T> ApiResult<PageResult<T>> getPage(String url,Object params,Class<T> clazz){
		ApiResult result = getForApiResult(url,params);
		
		return returnPage(result,clazz);
	}
	
	@Override
	public <T> ApiResult<Object> post(String url,Object params){
		return post(url,params,Object.class);
	}
	
	@Override
	public <T> ApiResult<T> post(String url,Class<T> clazz){
		return post(url,null,clazz);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public <T> ApiResult<T> post(String url,Object params,Class<T> clazz){
		
		if(!(params instanceof HttpEntity)){
			HttpHeaders headers = createHttpHeader();
			params = new HttpEntity<Object>(params,headers);
		}
		
		ApiResult result = postForObject(url, params, ApiResult.class);
		
		return returnObject(result,clazz);
	}
	
	@Override
	public <T> ApiResult<List<T>> postList(String url,Class<T> clazz){
		return postList(url,clazz);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public <T> ApiResult<List<T>> postList(String url,Object params,Class<T> clazz){
		
		if(!(params instanceof HttpEntity)){
			HttpHeaders headers = createHttpHeader();
			params = new HttpEntity<Object>(params,headers);
		}
		
		ApiResult result = postForObject(url, params, ApiResult.class);
		
		return returnList(result,clazz);
	}
	
	
	@Override
	public <T> ApiResult<PageResult<T>> postPage(String url,Class<T> clazz){
		return postPage(url,null,clazz);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public <T> ApiResult<PageResult<T>> postPage(String url,Object params,Class<T> clazz){

		if(!(params instanceof HttpEntity)){
			HttpHeaders headers = createHttpHeader();
			params = new HttpEntity<Object>(params,headers);
		}

		ApiResult result = this.postForObject(url, params, ApiResult.class);
		
		return returnPage(result,clazz);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> ApiResult<PageResult<T>> returnPage(ApiResult result,Class<T> clazz){
		if(BeanUtil.isNotBaseType(result.getObject())){
			PageResult o = FastJsonUtils.getBean(FastJsonUtils.toJsonString(result.getObject()), PageResult.class);
			if(o == null){
				return result;
			}
			result.setObject(o);
			if(BeanUtil.isNotBaseType(o.getList())){
				o.setList(FastJsonUtils.getBeanList(FastJsonUtils.toJsonString(o.getList()), clazz));
			}
		}
		return result;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> ApiResult<List<T>> returnList(ApiResult result,Class<T> clazz){
		
		if(BeanUtil.isNotBaseType(result.getObject())){
			result.setObject(FastJsonUtils.getBeanList(FastJsonUtils.toJsonString(result.getObject()), clazz));
		}
		return result;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> ApiResult<T> returnObject(ApiResult result,Class<T> clazz){
		if(BeanUtil.isNotBaseType(result.getObject())){
			result.setObject(FastJsonUtils.getBean(FastJsonUtils.toJsonString(result.getObject()), clazz));
		}
		return result;
	}
	
	
	
	
	
}
