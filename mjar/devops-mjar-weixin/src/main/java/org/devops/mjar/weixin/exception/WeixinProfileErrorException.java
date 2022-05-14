package org.devops.mjar.weixin.exception;

import org.devops.core.utils.exception.CommonRuntimeException;

/**
 * @author GENSEN
 * @date 2021/9/9 16:55
 * @description：配置异常
 */
public class WeixinProfileErrorException extends CommonRuntimeException {
    public WeixinProfileErrorException(String message) {
        super(message);
    }
}
