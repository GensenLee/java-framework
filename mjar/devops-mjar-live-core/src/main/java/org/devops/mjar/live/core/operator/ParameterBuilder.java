package org.devops.mjar.live.core.operator;

/**
 * @author GENSEN
 * @date 2020/10/26 17:52
 * @description：请求参数构造器
 */
public abstract class ParameterBuilder<T> {

    /**
     * @return
     */
    public abstract T build();
}
