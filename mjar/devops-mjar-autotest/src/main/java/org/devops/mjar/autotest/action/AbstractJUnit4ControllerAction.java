package org.devops.mjar.autotest.action;

import java.lang.reflect.Method;
import java.util.List;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.core.utils.vo.PageResult;
import org.devops.mjar.autotest.core.ITestRestCore;
import org.devops.mjar.autotest.core.TestRestCore;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractJUnit4ControllerAction extends AbstractJUnit4SpringContextTests implements ITestRestCore{

	@Autowired
	protected TestRestCore testRestCore;
	
	protected abstract String getSessionKey();
	
	protected abstract Class<?> getTargetClass();

	@Before
	public void _before(){
		SpringContextUtil.setContext(this.applicationContext);
		testRestCore.setSessionKey(getSessionKey());
	}
	
	@After
	public void _after(){
		testRestCore.clear();
	}
	
	protected String getMethodUrl(){
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return getMethodUrl(methodName);
	}
	
	protected String getMethodUrl(String methodName){
		Class<?> targetClazz = getTargetClass();
		if(targetClazz == null) {
			throw new CommonRuntimeException("请先配置目标class");
		}
		String url = "";
		RequestMapping requestMapping = AnnotationUtils.findAnnotation(targetClazz, RequestMapping.class);
		if(requestMapping != null && requestMapping.value().length > 0) {
			url = requestMapping.value()[0];
		}
		
		Method method = BeanUtil.findMethod(targetClazz, methodName);
		if(method == null) {
			throw new CommonRuntimeException(targetClazz.getName() + "找不到方法:" + methodName);
		}
		requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
		if(requestMapping != null && requestMapping.value().length > 0) {
			url += requestMapping.value()[0];
		} else {
			PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
			if(postMapping != null && postMapping.value().length > 0) {
				url += postMapping.value()[0];
			} else {
				GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
				if(getMapping != null && getMapping.value().length > 0) {
					url += getMapping.value()[0];
				}
			}
		}
		
		return url;
	}

	public <T> ApiResult<T> get(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.get(getMethodUrl(methodName), clazz);
	}
	
	@Override
	public <T> ApiResult<T> get(String url, Class<T> clazz) {
		return testRestCore.get(url, clazz);
	}

	public <T> ApiResult<T> get(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.get(getMethodUrl(methodName), params, clazz);
	}
	
	@Override
	public <T> ApiResult<T> get(String url, Object params, Class<T> clazz) {
		return testRestCore.get(url, params, clazz);
	}
	
	public <T> ApiResult<List<T>> getList(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.getList(getMethodUrl(methodName), clazz);
	}

	@Override
	public <T> ApiResult<List<T>> getList(String url, Class<T> clazz) {
		return testRestCore.getList(url, clazz);
	}

	public <T> ApiResult<List<T>> getList(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.getList(getMethodUrl(methodName), params, clazz);
	}
	
	@Override
	public <T> ApiResult<List<T>> getList(String url, Object params, Class<T> clazz) {
		return testRestCore.getList(url, params, clazz);
	}

	public <T> ApiResult<PageResult<T>> getPage(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.getPage(getMethodUrl(methodName), clazz);
	}
	
	@Override
	public <T> ApiResult<PageResult<T>> getPage(String url, Class<T> clazz) {
		return testRestCore.getPage(url, clazz);
	}
	
	public <T> ApiResult<PageResult<T>> getPage(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.getPage(getMethodUrl(methodName), params, clazz);
	}

	@Override
	public <T> ApiResult<PageResult<T>> getPage(String url, Object params, Class<T> clazz) {
		return testRestCore.getPage(url, params, clazz);
	}

	
	public <T> ApiResult<Object> post(Object params) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.post(getMethodUrl(methodName), params);
	}
	
	@Override
	public <T> ApiResult<Object> post(String url, Object params) {
		return testRestCore.post(url, params);
	}
	
	public <T> ApiResult<T> post(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.post(getMethodUrl(methodName), clazz);
	}

	@Override
	public <T> ApiResult<T> post(String url, Class<T> clazz) {
		return testRestCore.post(url, clazz);
	}
	
	public <T> ApiResult<T> post(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.post(getMethodUrl(methodName), params, clazz);
	}

	@Override
	public <T> ApiResult<T> post(String url, Object params, Class<T> clazz) {
		return testRestCore.post(url, params, clazz);
	}

	public <T> ApiResult<List<T>> postList(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.postList(getMethodUrl(methodName),clazz);
	}
	
	@Override
	public <T> ApiResult<List<T>> postList(String url, Class<T> clazz) {
		return testRestCore.postList(url, clazz);
	}

	public <T> ApiResult<List<T>> postList(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.postList(getMethodUrl(methodName), params, clazz);
	}
	
	@Override
	public <T> ApiResult<List<T>> postList(String url, Object params, Class<T> clazz) {
		return testRestCore.postList(url, params, clazz);
	}
	
	public <T> ApiResult<PageResult<T>> postPage(Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.postPage(getMethodUrl(methodName), clazz);
	}

	@Override
	public <T> ApiResult<PageResult<T>> postPage(String url, Class<T> clazz) {
		return testRestCore.postPage(url, clazz);
	}
	
	public <T> ApiResult<PageResult<T>> postPage(Object params, Class<T> clazz) {
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String methodName = callInfo.getMethodName();
		return testRestCore.postPage(getMethodUrl(methodName), params, clazz);
	}

	@Override
	public <T> ApiResult<PageResult<T>> postPage(String url, Object params, Class<T> clazz) {
		return testRestCore.postPage(url, params, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T> ApiResult<List<T>> returnList(ApiResult result, Class<T> clazz) {
		return testRestCore.returnList(result, clazz);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T> ApiResult<T> returnObject(ApiResult result, Class<T> clazz) {
		return testRestCore.returnObject(result, clazz);
	}
}
