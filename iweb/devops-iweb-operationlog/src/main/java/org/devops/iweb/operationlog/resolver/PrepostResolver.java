package org.devops.iweb.operationlog.resolver;

import org.devops.iweb.operationlog.model.OperationLog;

/**
 * @author GENSEN
 * @date 2021/11/19 15:12
 * @description：保存数据库前置操作
 */
public interface PrepostResolver extends ResolverContextAdapter{

    /**
     * operationLog 被插入数据库之前调用
     * @param operationLog
     */
    default void prepost(OperationLog operationLog){
    }

    public class DefaultPrepostResolver implements PrepostResolver {

    }
}
