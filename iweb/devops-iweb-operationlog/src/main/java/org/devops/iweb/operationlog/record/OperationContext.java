package org.devops.iweb.operationlog.record;

import org.devops.iweb.operationlog.annotation.OperationRecord;
import org.devops.core.utils.interfaces.BaseUserInfo;

import java.util.Date;

/**
 * @author GENSEN
 * @date 2021/9/27 19:49
 * @description：过程持有
 */
public interface OperationContext {

    /**
     * @return
     */
    Object[] getInput();

    /**
     * 方法异常中断时返回null
     * @return
     */
    Object getOutput();

    /**
     * 无异常时返回null
     * @return
     */
    Throwable getThrowable();

    /**
     * @return
     */
    BaseUserInfo getUserInfo();

    /**
     * @return
     */
    OperationRecord getOperationRecord();

    /**
     * @return
     */
    Date getRecordTime();

}
