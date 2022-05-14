package org.devops.web.template.handle;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.xxl.handle.XxlJobAdminControllerExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常统一处理
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends XxlJobAdminControllerExceptionHandler {


    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ApiResult<Object> runtimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        log.info("[ControllerExceptionHandler.runtimeExceptionHandler] Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());

        ApiResult<Object> apiResult;

        log.info("异常信息：" + e.getMessage());

        if (e instanceof CommonRuntimeException) {
            CommonRuntimeException commonRuntimeException = (CommonRuntimeException) e;
            apiResult = ApiResult.get(commonRuntimeException.getCode(), commonRuntimeException.getMessage());
            log.info("[ControllerExceptionHandler.runtimeExceptionHandler] 业务异常处理后返回参数 result: {}", FastJsonUtils.toJsonString(apiResult));
        } else {
            apiResult = ApiResult.getFailed(e.getMessage());
            log.error("[ControllerExceptionHandler.runtimeExceptionHandler exception]", e);
            log.info("[ControllerExceptionHandler.runtimeExceptionHandler] 系统异常处理后返回参数 result: {}", FastJsonUtils.toJsonString(apiResult));
            //收集异常
        }

        return apiResult;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult<Object> exceptionHandler(HttpServletRequest req, Exception e) {

        log.info("[ControllerExceptionHandler.exceptionHandler] Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());

        ApiResult<Object> apiResult;

        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            apiResult = ApiResult.get(commonException.getCode(), commonException.getMessage());
        } else {
            apiResult = ApiResult.getFailed(e.getMessage());

        }
        log.info("[ControllerExceptionHandler.exceptionHandler] 业务异常处理后返回参数 result: {}", FastJsonUtils.toJsonString(apiResult));
        return apiResult;
    }
}
