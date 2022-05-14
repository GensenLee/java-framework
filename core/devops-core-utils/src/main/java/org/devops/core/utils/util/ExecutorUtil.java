package org.devops.core.utils.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.devops.core.utils.util.IPUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gensen.Lee
 * @date 2019/10/24 12:42
 */
@Slf4j
public class ExecutorUtil {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();


    /**
     * 限时执行
     * @param future
     * @param timeout
     * @param <T>
     * @return
     */
    public static <T> T executeLimit(FutureTask<T> future ,long timeout){
        executor.execute(future);
        try {
            /*执行时间超过 timeout*/
            return future.get(timeout ,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        	log.error("[exception 出错啦]",e);
        	Thread.currentThread().interrupt();
          
        } catch (ExecutionException e) {
        	log.error("[exception 出错啦]",e);
        } catch (TimeoutException e) {
            //超时
            return null;
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
        return null;
    }
}
