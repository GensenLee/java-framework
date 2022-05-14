package org.devops.mjar.live.transform.transformer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GENSEN
 * @date 2021/6/24 19:52
 * @description：转换器
 */
public interface Transformer {

    /**
     * 转换
     *  PolyvProfiles polyvProfiles = PolyvContext.getContext().getPolyvProfileHandler().get(); 获取配置
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    Object trans(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
