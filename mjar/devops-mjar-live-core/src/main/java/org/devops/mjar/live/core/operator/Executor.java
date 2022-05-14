package org.devops.mjar.live.core.operator;

/**
 * @author GENSEN
 * @date 2021/3/12 15:45
 * @description：执行器
 */
public interface Executor<R> {

    /**
     * 执行器
     * @return
     */
    R execute();

}
