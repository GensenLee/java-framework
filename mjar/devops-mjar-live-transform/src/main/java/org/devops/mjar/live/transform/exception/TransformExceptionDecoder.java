package org.devops.mjar.live.transform.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/1 16:57
 * @description：异常解析器
 */
public interface TransformExceptionDecoder {

    /**
     * 异常解析
     * @param request
     * @param throwable
     * @return
     */
    Object decode(HttpServletRequest request, HttpServletResponse response, Throwable throwable);

}
