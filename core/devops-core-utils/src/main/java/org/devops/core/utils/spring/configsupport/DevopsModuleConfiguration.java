package org.devops.core.utils.spring.configsupport;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/9/22 15:11
 * @description：spring配置拓展
 */
public interface DevopsModuleConfiguration {

    /**
     * Override this method to add resource handlers for serving static resources.
     * @param registry 
     * @see ResourceHandlerRegistry
     */
    default void addResourceHandlers(ResourceHandlerRegistry registry){};

    /**
     * Override this method to add Spring MVC interceptors for
     * pre- and post-processing of controller invocation.
     * @param registry 
     * @see InterceptorRegistry
     */
    default void addInterceptors(InterceptorRegistry registry){};

    /**
     * Add custom {@link HandlerMethodArgumentResolver HandlerMethodArgumentResolvers}
     * to use in addition to the ones registered by default.
     * <p>Custom argument resolvers are invoked before built-in resolvers except for
     * those that rely on the presence of annotations (e.g. {@code @RequestParameter},
     * {@code @PathVariable}, etc). The latter can be customized by configuring the
     * {@link RequestMappingHandlerAdapter} directly.
     * @param argumentResolvers the list of custom converters (initially an empty list)
     */
    default void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){};

    /**
     * Add custom {@link HandlerMethodReturnValueHandler HandlerMethodReturnValueHandlers}
     * in addition to the ones registered by default.
     * <p>Custom return value handlers are invoked before built-in ones except for
     * those that rely on the presence of annotations (e.g. {@code @ResponseBody},
     * {@code @ModelAttribute}, etc). The latter can be customized by configuring the
     * {@link RequestMappingHandlerAdapter} directly.
     * @param returnValueHandlers the list of custom handlers (initially an empty list)
     */
    default void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers){};

    /**
     * Override this method to add custom {@link HttpMessageConverter HttpMessageConverters}
     * to use with the {@link RequestMappingHandlerAdapter} and the
     * {@link ExceptionHandlerExceptionResolver}.
     * <p>Adding converters to the list turns off the default converters that would
     * otherwise be registered by default. Also see {@link WebMvcConfigurationSupport#addDefaultHttpMessageConverters}
     * for adding default message converters.
     * @param converters a list to add message converters to (initially an empty list)
     */
    default void configureMessageConverters(List<HttpMessageConverter<?>> converters){};

    /**
     * Return the registered {@link CorsConfiguration} objects,
     * keyed by path pattern.
     * @since 4.2
     */
    default void addCorsMappings(CorsRegistry registry) {}
}
