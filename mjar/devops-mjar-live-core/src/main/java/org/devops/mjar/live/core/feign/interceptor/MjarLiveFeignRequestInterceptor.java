package org.devops.mjar.live.core.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 代理头部信息
 * @author GENSEN
 */
@Slf4j
@Component
public class MjarLiveFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null == attributes){
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if("host".equals(name)){
                	//不代理host
                	continue;
                }
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
        MjarLiveContext.getContext().getMjarLiveFeignRequestTemplateHandler().handle(requestTemplate);
    }
}
