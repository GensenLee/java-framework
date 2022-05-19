package org.devops.core.utils.modules.log.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/11/5 9:56
 * @description：请求持有
 *
 * 替代 {@link org.devops.core.utils.util.RequestUtil}
 */
@Deprecated
public class RequestGlobalHandler {

    private static final ThreadLocal<HttpServletRequest> REQUESTS = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> RESPONSES = new ThreadLocal<>();
    private static final ThreadLocal<String> REQUEST_IDS = new ThreadLocal<>();


    /**
     * @param request
     * @param requestId
     */
    static void set(HttpServletRequest request, HttpServletResponse response, String requestId){
        clean();
        REQUESTS.set(request);
        RESPONSES.set(response);
        REQUEST_IDS.set(requestId);
    }

    static void clean(){
        REQUESTS.remove();
        RESPONSES.remove();
        REQUEST_IDS.remove();
    }

    /**
     * 获取当前请求id
     * @return
     */
    public static String getRequestId(){
        return REQUEST_IDS.get();
    }

    /**
     * 获取当前请求
     * @return
     */
    public static HttpServletRequest getRequest(){
        return REQUESTS.get();
    }

    /**
     * 获取当前响应
     * @return
     */
    public static HttpServletResponse getResponse(){
        return RESPONSES.get();
    }

}
