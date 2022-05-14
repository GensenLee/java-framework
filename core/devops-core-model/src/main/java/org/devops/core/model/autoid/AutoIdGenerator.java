package org.devops.core.model.autoid;

/**
 * @author GENSEN
 * @date 2021/9/15 16:42
 * @description：id生成器
 */
public interface AutoIdGenerator<T> {

    /**
     * 生成一个id
     * @return
     */
    T nextId();
}
