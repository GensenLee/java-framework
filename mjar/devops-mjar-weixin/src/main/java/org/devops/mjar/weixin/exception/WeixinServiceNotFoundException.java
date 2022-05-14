package org.devops.mjar.weixin.exception;

import org.devops.core.utils.exception.CommonRuntimeException;

/**
 * @author GENSEN
 * @date 2021/9/9 16:55
 * @description：未配置
 */
public class WeixinServiceNotFoundException extends CommonRuntimeException {

    public WeixinServiceNotFoundException() {
        super("service不存在");
    }
}
