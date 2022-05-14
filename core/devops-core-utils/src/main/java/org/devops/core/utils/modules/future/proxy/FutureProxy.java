package org.devops.core.utils.modules.future.proxy;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.future.interceptor.FutureInterceptor;
import org.springframework.cglib.proxy.Enhancer;

import java.util.HashMap;
import java.util.Map;

public class FutureProxy {

    private Map<String, Object> mapProxyClass = new HashMap<>();

    private Map<String, FutureInterceptor> mapFutureInterceptor = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getProxyClass(T target) {

        if (target == null) {
            throw new CommonRuntimeException("被代理的类不能为空");
        }

        Class<?> clazz = target.getClass();
        if (Enhancer.isEnhanced(target.getClass())) {
            clazz = clazz.getSuperclass();
        }
        Object targetProxyClass = mapProxyClass.get(clazz.getName());

        if (targetProxyClass == null) {

            Enhancer enhancer = new Enhancer();

            enhancer.setSuperclass(clazz);

            FutureInterceptor futureInterceptor = new FutureInterceptor(target);
            mapFutureInterceptor.put(clazz.getName(), futureInterceptor);
            enhancer.setCallback(futureInterceptor);
            targetProxyClass = enhancer.create();

            mapProxyClass.put(clazz.getName(), targetProxyClass);
        }

        return (T) targetProxyClass;
    }

    /**
     * 这个方法不做逻辑，只做标记
     */
    public void complete() {
//		for(String key : mapFutureInterceptor.keySet()) {
//			mapFutureInterceptor.get(key).complete();
//		}
    }

    public void clear() {
        mapProxyClass.clear();
        for (String key : mapFutureInterceptor.keySet()) {
            mapFutureInterceptor.get(key).clear();
        }
        mapFutureInterceptor.clear();
    }
}
