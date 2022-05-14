package org.devops.core.utils.modules.log.request;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.interceptor.AbstractInterceptor;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.util.identifier.ULID;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/9/18 15:56
 * @description：请求日志
 */
@Slf4j
public class RequestLogHandlerInterceptor extends AbstractInterceptor {

    public static final String DEFAULT_KEY = "log_id";
    public static final String X_REQUEST_ID_KEY = "x-request-id";
    public static final String REQUEST_ID_KEY = "_requestId";

    private static final ULID ULID = new ULID();

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 注入日志id，方便日志追踪
        String requestId = request.getParameter(REQUEST_ID_KEY);
        if (StringUtil.isEmpty(requestId)) {
            requestId = request.getHeader(X_REQUEST_ID_KEY);
        }
        if (StringUtil.isEmpty(requestId)) {
            // 前端传入时，直接使用前端的值
            requestId = ULID.nextULID();
        }
        MDC.put(DEFAULT_KEY, requestId);
        log.info("request url [{}] query [{}]", request.getRequestURL(), request.getQueryString());
        RequestGlobalHandler.set(request, response, requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(DEFAULT_KEY);
        RequestGlobalHandler.clean();
    }
}
