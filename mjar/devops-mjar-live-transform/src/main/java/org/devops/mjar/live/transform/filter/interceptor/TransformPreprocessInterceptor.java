package org.devops.mjar.live.transform.filter.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GENSEN
 * @date 2021/6/30 10:20
 * @description：请求前置处理
 */
public interface TransformPreprocessInterceptor {

    /**
     * 接口进行转化前业务系统需要进行类似请求签名的鉴权检查
     * @param request
     * @return 前置检查结果
     */
    PreprocessResult processing(HttpServletRequest request);

}
