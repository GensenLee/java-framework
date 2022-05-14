package org.devops.mjar.live.transform.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/7/2 15:04
 * @description：调用链
 */
public interface ProcessorChain {

    /**
     * 向下调用
     * @param request
     * @param response
     * @return
     */
    void doProcessing(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
