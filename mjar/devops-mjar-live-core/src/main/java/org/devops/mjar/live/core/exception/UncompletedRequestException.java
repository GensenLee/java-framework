package org.devops.mjar.live.core.exception;

/**
 * @author GENSEN
 * @date 2021/3/17 16:17
 * @description：未完成的请求
 */
public class UncompletedRequestException extends LiveApiRuntimeException {

    public UncompletedRequestException(String message) {
        super(message);
    }
}
