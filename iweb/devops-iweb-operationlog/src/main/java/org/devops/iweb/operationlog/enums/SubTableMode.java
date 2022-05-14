package org.devops.iweb.operationlog.enums;

/**
 * @author GENSEN
 * @date 2021/11/30 16:51
 * @description：分表模式
 */
public enum SubTableMode {

    /**
     * 无，不分表
     */
    NONE,
    /**
     * 按年分表，后缀 "_2021"
     */
    YEAR,
    /**
     * 按月分表，后缀 "_202101"
     */
    MONTH,
}
