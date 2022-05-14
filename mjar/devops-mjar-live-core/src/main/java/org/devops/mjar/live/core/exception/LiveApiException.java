package org.devops.mjar.live.core.exception;

import org.devops.core.utils.exception.CommonException;
import org.devops.mjar.live.core.model.PolyvApiResult;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/27 13:49
 * @description：live sdk
 */
@Data
public class LiveApiException extends CommonException {

    private PolyvApiResult<?> errorBean;

    public LiveApiException() {
        super();
    }

    public LiveApiException(String message) {
        super(message);
    }

    public LiveApiException(int code, String message) {
        super(code, message);
    }
}
