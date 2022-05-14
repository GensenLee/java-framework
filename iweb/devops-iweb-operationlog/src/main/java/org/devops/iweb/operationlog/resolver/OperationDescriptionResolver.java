package org.devops.iweb.operationlog.resolver;

/**
 * @author GENSEN
 * @date 2021/9/26 18:35
 * @description：数据解析器，用于解析保存的入参、返回值
 */
public interface OperationDescriptionResolver extends ResolverContextAdapter{

    /**
     * 解析操作描述
     *
     * @param input
     * @param output
     * @return
     */
    default String parseOperationDescription(Object[] input, Object output) {
        return "";
    }

    public class DefaultOperationDescriptionResolver implements OperationDescriptionResolver {
    }
}
