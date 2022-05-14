package org.devops.core.utils.spring.configsupport;

import org.devops.core.utils.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/9/22 15:20
 * @description：WebMvcConfigurationSupport 单例, springboot仅支持一个WebMvcConfigurationSupport实例
 */
@Primary
@Configuration
public class SingletonWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Autowired(required = false)
    private List<DevopsModuleConfiguration> moduleConfigurationList;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.addInterceptors(registry);
        }
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.addResourceHandlers(registry);
        }
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.addArgumentResolvers(argumentResolvers);
        }
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.configureMessageConverters(converters);
        }
        super.configureMessageConverters(converters);
    }

    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.addReturnValueHandlers(returnValueHandlers);
        }
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        if (ListUtil.isNull(moduleConfigurationList)) {
            return;
        }
        for (DevopsModuleConfiguration configuration : moduleConfigurationList) {
            configuration.addCorsMappings(registry);
        }
        super.addCorsMappings(registry);
    }
}
