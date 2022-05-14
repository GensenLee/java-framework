package org.devops.mjar.live.core.exception;


import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;

/**
 * @author GENSEN
 * @date 2020/10/27 13:49
 * @description：live sdk
 */
public class LiveApiRuntimeException extends CommonRuntimeException {

    public LiveApiRuntimeException(ApiResultCode apiResultCode) {
        super(apiResultCode);
    }

    public LiveApiRuntimeException() {
        super();
    }

    public LiveApiRuntimeException(String message) {
        super(message);
    }
}
