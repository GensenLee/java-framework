package org.devops.core.utils.modules.future.factory;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureFactory {

    private static ThreadPoolExecutor threadPoolExecutor;

    private static int corePoolSize = 10;

    private static int maximumPoolSize = 2000;

    private static int keepAliveTime = 1000;

    static {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(10000);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, linkedBlockingQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public static ThreadPoolExecutor newThreadPoolExecutor(int maximumPoolSize) {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(10000);
        return new ThreadPoolExecutor(corePoolSize > maximumPoolSize ? maximumPoolSize : corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, linkedBlockingQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static void init(int corePoolSize, int maximumPoolSize, int keepAliveTime) {
        FutureFactory.corePoolSize = corePoolSize;
        FutureFactory.maximumPoolSize = maximumPoolSize;
        FutureFactory.keepAliveTime = keepAliveTime;

        threadPoolExecutor.setCorePoolSize(corePoolSize);
        threadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
        threadPoolExecutor.setKeepAliveTime(keepAliveTime, TimeUnit.MICROSECONDS);
    }
}
