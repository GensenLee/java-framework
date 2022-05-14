package org.devops.mjar.live.transform.exception;

import org.devops.core.utils.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/1 16:58
 * @description：过滤器异常处理
 */
@Component
public class FilterExceptionHandler {

    @Autowired(required = false)
    private TransformExceptionDecoder exceptionDecoder;

    /**
     * @param request
     * @param response
     * @param throwable
     * @return
     */
    public boolean handle(HttpServletRequest request, HttpServletResponse response, Throwable throwable){
        if (exceptionDecoder == null) {
            return false;
        }
        Object decode = exceptionDecoder.decode(request, response, throwable);
        RequestUtil.writeToResponse(response, decode);
        return true;
    }

}
