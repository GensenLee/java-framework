package org.devops.iweb.operationlog.annotation;

import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/11/18 17:55
 * @description：自定义参数
 */
public @interface CustomParamDefine {

    /**
     * spel转换表达式
     * @return
     */
    String define();

    /**
     * @return
     */
    Param param();

    @Getter
    enum Param{
        /**
         * param1
         */
        PARAM1("param1"),
        /**
         * param2
         */
        PARAM2("param2"),
        /**
         * param3
         */
        PARAM3("param3"),
        /**
         * param4
         */
        PARAM4("param4");
        private String name;

        Param(String name) {
            this.name = name;
        }
    }

}
