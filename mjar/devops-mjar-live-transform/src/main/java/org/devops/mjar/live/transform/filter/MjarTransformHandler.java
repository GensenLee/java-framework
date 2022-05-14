package org.devops.mjar.live.transform.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/2 16:23
 * @description：
 */
public class MjarTransformHandler {

    public final static ThreadLocal<HttpServletRequest> REQUEST_HANDLER = new ThreadLocal<>();
    public final static ThreadLocal<HttpServletResponse> RESPONSE_HANDLER = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request){
        REQUEST_HANDLER.set(request);
    }

    public static void setResponse(HttpServletResponse response){
        RESPONSE_HANDLER.set(response);
    }

    public static HttpServletRequest getRequest(){
        return REQUEST_HANDLER.get();
    }

    public static HttpServletResponse getResponse(){
        return RESPONSE_HANDLER.get();
    }

    public static void clean(){
        REQUEST_HANDLER.remove();
        RESPONSE_HANDLER.remove();
    }

}
