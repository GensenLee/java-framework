package org.devops.iweb.operationlog.record;

import org.devops.iweb.operationlog.resolver.AbstractOperationDescriptionResolver;
import org.springframework.beans.BeanUtils;

/**
 * @author GENSEN
 * @date 2021/11/19 16:03
 * @description：解析器工厂
 */
public class ResolverFactory {


    private static final ThreadLocal<Object> RESOLVER_INSTANCE_CACHE = new ThreadLocal<>();

    static <T> T getResolverOnThread(Class<T> clazz) {
        Object resolver = RESOLVER_INSTANCE_CACHE.get();
        if (resolver != null && clazz.isAssignableFrom(resolver.getClass())) {
            return (T) resolver;
        }
        if (resolver == null) {
            resolver = BeanUtils.instantiateClass(clazz);
            if (resolver instanceof AbstractOperationDescriptionResolver) {
                RESOLVER_INSTANCE_CACHE.set(resolver);
            }
        }
        return (T) resolver;
    }


    static void cleanThread(){
        RESOLVER_INSTANCE_CACHE.remove();
    }

}
