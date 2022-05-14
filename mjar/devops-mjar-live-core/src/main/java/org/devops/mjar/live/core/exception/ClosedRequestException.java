package org.devops.mjar.live.core.exception;

/**
 * @author GENSEN
 * @date 2021/3/17 18:35
 * @description：已关闭的请求
 */
public class ClosedRequestException extends LiveApiRuntimeException {
    public ClosedRequestException() {
        super("request closed");
    }
}
