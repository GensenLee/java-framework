package org.devops.iweb.operationlog.resolver;

import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/11/18 18:01
 * @description：自定义参数解析
 */
public interface CustomParamResolver extends ResolverContextAdapter{

    /**
     * 解析入参中的部分参数
     * @param input
     * @return
     */
    default CustomParams parseParam(Object[] input) {
        return null;
    }


    class DefaultCustomParamsResolver implements CustomParamResolver {
    }

    @Data
    class CustomParams {
        private String param1;
        private String param2;
        private String param3;
        private String param4;
    }

}
