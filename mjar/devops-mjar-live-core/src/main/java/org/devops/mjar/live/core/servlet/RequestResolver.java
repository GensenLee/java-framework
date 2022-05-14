package org.devops.mjar.live.core.servlet;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author GENSEN
 * @date 2021/7/23 16:00
 * @description：
 */
@Component
public class RequestResolver {

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public HttpServletRequest resolve(ServletRequest request) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());

        if (resolver.isMultipart(httpServletRequest)) {
            // 兼容 MultipartHttpServletRequest
            httpServletRequest = new StandardMultipartHttpServletRequestBodyWrapper(httpServletRequest);
        }else {
            httpServletRequest = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        }
        return httpServletRequest;
    }
}
