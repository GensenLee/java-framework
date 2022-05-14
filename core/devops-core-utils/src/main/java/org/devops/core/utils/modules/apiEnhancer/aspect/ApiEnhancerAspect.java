package org.devops.core.utils.modules.apiEnhancer.aspect;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.apiEnhancer.annotation.ApiEnhancer;
import org.devops.core.utils.modules.apiEnhancer.data.ApiEnhancerData;
import org.devops.core.utils.modules.apiEnhancer.enums.ApiEnhancerType;
import org.devops.core.utils.modules.apiEnhancer.properties.ApiEnhancerPoolProperties;
import org.devops.core.utils.modules.apiEnhancer.redis.ApiEnhancerCreateRedisDao;
import org.devops.core.utils.modules.apiEnhancer.redis.ApiEnhancerSerialRedisDao;
import org.devops.core.utils.modules.apiEnhancer.util.ApiEnhancerUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.BaseBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Order(999)
@Slf4j
public class ApiEnhancerAspect implements InitializingBean {
	
	@Autowired
	private ApiEnhancerPoolProperties apiEnhancerPoolProperties;
	
	@Autowired
	private ApiEnhancerData apiEnhancerData;
	
	@Autowired
	private ApiEnhancerCreateRedisDao apiEnhancerCreateRedisDao;
	
	@Autowired
	private ApiEnhancerSerialRedisDao apiEnhancerSerialRedisDao;
	
	@Value("${api-enhancer.enable: true}")
	private boolean enable;
	
	private String KEY_TEMPALTE = "%s.%s.";
	
	private static ExecutorService pool;
	
	private Map<String,Type> cacheResultType = new HashMap<>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		pool = new ThreadPoolExecutor(apiEnhancerPoolProperties.getCorePoolSize(), apiEnhancerPoolProperties.getMaximumPoolSize(), apiEnhancerPoolProperties.getKeepAliveTime(), TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	}
	
	//定义环绕通知
	@Around("execution( * *(..)) and @annotation(apiEnhancer)")
	public Object around(final ProceedingJoinPoint  pjp, ApiEnhancer apiEnhancer) throws Throwable{
		
		if(!enable) {
			return pjp.proceed(pjp.getArgs());
		}
		
		String className = pjp.getTarget().getClass().getName();
		
		Class<?> clazz = pjp.getTarget().getClass();
		
		if(Proxy.isProxyClass(pjp.getTarget().getClass())) {
			Class<?>[] arrClazzInterface = pjp.getTarget().getClass().getInterfaces();
			if(arrClazzInterface != null && arrClazzInterface.length > 0) {
				className = "";
				for(Class<?> clazzInterface : arrClazzInterface) {
					clazz = clazzInterface;
					className += clazzInterface.getName();
				}
			}
		}
		String key = String.format(KEY_TEMPALTE, className,pjp.getSignature().getName());
		
		Type typeResult = null;
		String[] parameterNames = null;
		
		if(pjp.getSignature() instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
			// typeResult = clazz.getMethod(methodSignature.getName(), methodSignature.getParameterTypes()).getGenericReturnType();
			typeResult = cacheResultType.get(key);
			parameterNames = methodSignature.getParameterNames();
			// 必须缓存，要不然会出现fastjson内存泄露
			if(typeResult == null) {
				typeResult = clazz.getMethod(methodSignature.getName(), methodSignature.getParameterTypes()).getGenericReturnType();
				cacheResultType.put(key, typeResult);
			}
		}
				
		log.info("[ApiEnhancerAspect.around] 类型:{} key:{} 接口缓存前置拦截aop启动......",apiEnhancer.type().toString(), key);
		
		if(apiEnhancer.expire() == 0) {
			// 如果为0表示关闭缓存
			log.info("[ApiEnhancerAspect.around] {} expire为0，关闭缓存.",key);
			return pjp.proceed(pjp.getArgs());
		}
		
		if(apiEnhancer.async() && pool != null && typeResult != null) {
			// 如果是异步的
			log.info("[ApiEnhancerAspect.around] {} 开启异步，不关心返回值",key);
			pool.execute(new Runnable(){
				@Override
				public void run() {
					try {
						pjp.proceed();
					} catch (Throwable e) {
					}
				}
				
			});
			if(typeResult instanceof ParameterizedType) {
				return ((Class<?>)((ParameterizedType)typeResult).getRawType()).newInstance();
			}else if(typeResult.equals(Void.TYPE)){
			  return null;
			}else{
				return ((Class<?>)typeResult).newInstance();
			}
		}
		
		// 获取接口参数
		Object[] args = pjp.getArgs();
		if(args.length == 0) {
			key += "";
		}else {
			Object req = null;
			Map<String,Object> params = new HashMap<String,Object>();
			if(parameterNames != null && parameterNames.length == args.length) {
				for(int i=0;i<args.length;i++) {
					params.put(parameterNames[i], args[i]);
				}
			} else {
				for(Object o : args) {
					if(o instanceof BaseBean) {
						req = o;
					} else {
					  if (o != null) {
			            params.put(o.getClass().getName(), o);
			          }
					}
				}
			}
			if(req != null) {
				// 对参数进行排序和签名
				key += ApiEnhancerUtil.encryptHashCode(req,apiEnhancer.exclude());
				log.info("[ApiEnhancerAspect.around] {} 接口缓存key加权",key);
			} else if(params.size() != 0 ) {
				// 对参数进行排序和签名
				key += ApiEnhancerUtil.encryptHashCode(params,apiEnhancer.exclude());
				log.info("[ApiEnhancerAspect.around] {} 接口缓存key加权",key);
			}
			
		}
		
		if(apiEnhancer.type().equals(ApiEnhancerType.CREATE)) {
			// 创建
			return proceedCreate(key, apiEnhancer.expire(),pjp,typeResult);
		} else if(apiEnhancer.type().equals(ApiEnhancerType.SERIAL)){
			// 串行执行
			return proceedSerial(key, apiEnhancer.expire(),pjp,typeResult);
		}

		
		
		Object result = null;
		if(apiEnhancer.type().equals(ApiEnhancerType.API_INTERVAL)) {
			log.info("[ApiEnhancerAspect.around] {} 接口有操作间隔要求.....",key);
			String value = apiEnhancerData.get(ApiEnhancerType.CACHEABLE, key);
			if(StringUtil.isEmpty(value)) {
				log.info("[ApiEnhancerAspect.around] {} 接口有操作间隔要求,缓存没有数据,通过,......",key);
				result = pjp.proceed();
				apiEnhancerData.set(ApiEnhancerType.CACHEABLE, key, apiEnhancer.expire(), FastJsonUtils.toJsonString(result));
			} else {
				// 操作频繁,提示
				throw new CommonRuntimeException(ApiResultCode.API_INTERVAL_ERROR);
			}
		} else {
			String value = apiEnhancerData.get(apiEnhancer.type(), key);
			if(StringUtil.isEmpty(value)) {
				log.info("[ApiEnhancerAspect.around] {} 接口缓存没有命中,缓存中没有数据......",key);
				long start = System.currentTimeMillis();
				result = pjp.proceed();
				log.info("[ApiEnhancerAspect.around] {} 耗时：{}ms",String.format(KEY_TEMPALTE, className,pjp.getSignature().getName()),(System.currentTimeMillis() - start));
				apiEnhancerData.set(apiEnhancer.type(), key, apiEnhancer.expire(), FastJsonUtils.toJsonString(result));
			} else if(typeResult != null){
				log.info("[ApiEnhancerAspect.around] {} 接口命中缓存......",key);
				result = FastJsonUtils.getBean(value,typeResult);
			}
		}
		
		return result;
	}
	
	private Object proceedCreate(String key,long expire, ProceedingJoinPoint pjp, Type typeResult) throws Throwable{
		Object result = null;
		if(apiEnhancerCreateRedisDao.lock(key, expire)) {
			result = pjp.proceed();
			apiEnhancerCreateRedisDao.set(key, FastJsonUtils.toJsonString(result), expire);
		} else {
			long now = System.currentTimeMillis();
			while(true) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					log.error("[Exception] 出错啦!!!",e);
					Thread.interrupted();
				}
				if(System.currentTimeMillis() - now > expire) {
					throw new CommonRuntimeException("创建超时，请重试");
				}
				String value = apiEnhancerCreateRedisDao.get(key);
				if(StringUtil.isEmpty(value)) {
					continue;
				}
				log.info("[ApiEnhancerAspect.create] {} {}",key,value);
				return FastJsonUtils.getBean(value,typeResult);
			}
		}
		return result;
	}
	
	private Object proceedSerial(String key,long expire, ProceedingJoinPoint pjp, Type typeResult) throws Throwable{
		long now = System.currentTimeMillis();
		while(true) {
			if(System.currentTimeMillis() - now > expire) {
				throw new CommonRuntimeException("执行超时，请重试");
			}
			if(apiEnhancerSerialRedisDao.lock(key, expire)) {
				Object result = pjp.proceed();
				apiEnhancerSerialRedisDao.unlock(key);
				return result;
			} else {
				continue;
			}
		}
	}

}
