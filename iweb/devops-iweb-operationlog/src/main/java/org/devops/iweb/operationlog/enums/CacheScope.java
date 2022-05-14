package org.devops.iweb.operationlog.enums;

/**
 * @author GENSEN
 * @date 2021/9/26 18:31
 * @description：数据保留范围
 */
public enum CacheScope {
    /**
     * 不保留
     */
    none,
    /**
     * 返回值
     */
    output,
    /**
     * 入参
     */
    input;
}
