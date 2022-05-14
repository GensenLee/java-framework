package org.devops.iweb.operationlog.resolver;

import org.devops.iweb.operationlog.record.OperationContext;

/**
 * @author GENSEN
 * @date 2021/9/30 16:07
 * @description：默认解析器，解析器集成该父类时，只在任务线程中维护一个单例
 */
public class AbstractOperationDescriptionResolver implements CustomParamResolver, OperationDescriptionResolver, PrepostResolver {

    private OperationContext context;

    @Override
    public void bindContext(OperationContext context) {
        this.context = context;
    }

    protected OperationContext getContext() {
        return context;
    }
}
