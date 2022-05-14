package org.devops.core.utils.modules.future.interceptor;

import com.google.common.util.concurrent.Futures;
import org.devops.core.utils.modules.future.factory.FutureFactory;
import org.devops.core.utils.modules.future.util.FutureUtil;
import org.devops.core.utils.util.StringUtil;
import org.slf4j.MDC;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class FutureInterceptor implements MethodInterceptor {

    private Object target;

    private Map<String, Future<Object>> mapFuture = new HashMap<String, Future<Object>>();

    // 日志跟踪标识
    private static final String PROCESS_ID = "processId";

    public FutureInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, final Method method, final Object[] args, MethodProxy proxy) throws Throwable {

        String key = method.getName();
        String argsKey = FutureUtil.encryptHashCode(Arrays.asList(args));
        if (StringUtil.isNotEmpty(argsKey)) {
            key += "." + argsKey;
        }

        Future<Object> future = mapFuture.get(key);

        if (future == null) {
            ThreadPoolExecutor threadPoolExecutor = FutureFactory.getThreadPoolExecutor();
            final String processId = MDC.get(PROCESS_ID);
            future = threadPoolExecutor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    MDC.put(PROCESS_ID, processId);
                    try {
                        // 如果方法为protected方法时候，必须设置为可以访问
                        method.setAccessible(true);
                        return method.invoke(target, args);
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        MDC.remove(PROCESS_ID);
                    }
                }
            });
            mapFuture.put(key, future);
            return null;
        } else {
            mapFuture.remove(key);
            return Futures.getUnchecked(future);
        }
    }

    public void clear() {
        mapFuture.clear();
    }

}
