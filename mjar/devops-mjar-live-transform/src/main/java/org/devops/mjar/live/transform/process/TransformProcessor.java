package org.devops.mjar.live.transform.process;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/2 15:00
 * @description：增强处理器
 */
public interface TransformProcessor {

    /**
     * 调用链
     *  request.getAttribute(ProcessRequestAttribute.REQUEST_DATA) 前置调用链间传参方式
     *
     *
     * @param request
     * @param response
     * @param processChain
     */
    void processing(HttpServletRequest request, HttpServletResponse response, ProcessorChain processChain) throws Exception;

}
