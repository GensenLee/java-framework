package org.devops.iweb.auth.configuration;

import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.devops.core.utils.spring.configsupport.DevopsModuleConfiguration;
import org.devops.iweb.auth.interceptor.GlobalAuthInterceptor;
import org.devops.iweb.auth.interceptor.InstanceAuthInterceptor;
import org.devops.iweb.auth.interceptor.UserAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@ComponentScan(basePackages = {
        "org.devops.iweb.auth"
})
@EnableRedis
public class IWebAuthConfiguration implements DevopsModuleConfiguration , Ordered {

    @Autowired
    private GlobalAuthInterceptor globalAuthInterceptor;

    @Autowired
    private UserAuthInterceptor userAuthInterceptor;

    @Autowired
    private InstanceAuthInterceptor instanceAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalAuthInterceptor);
        registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**/user/**");
        registry.addInterceptor(instanceAuthInterceptor).addPathPatterns("/**/instance/**");
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
