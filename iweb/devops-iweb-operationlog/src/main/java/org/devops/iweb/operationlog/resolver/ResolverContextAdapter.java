package org.devops.iweb.operationlog.resolver;

import org.devops.iweb.operationlog.record.OperationContext;

/**
 * @author GENSEN
 * @date 2021/11/18 18:05
 * @description：上下文连接器
 */
public interface ResolverContextAdapter {

    /**
     * 绑定任务实例
     *
     * @param context
     */
    default void bindContext(OperationContext context) {
    }

}
