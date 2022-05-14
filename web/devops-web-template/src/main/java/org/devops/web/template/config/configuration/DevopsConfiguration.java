package org.devops.web.template.config.configuration;

import org.devops.core.utils.modules.apiEnhancer.configuration.EnableApiEnhancer;
import org.devops.core.utils.spring.configsupport.DevopsModuleConfiguration;
import org.devops.iweb.auth.configuration.EnableIWebAuth;
import org.devops.iweb.file.configuration.EnableIWebFile;
import org.devops.mjar.ext.configuration.EnableMjarExt;
import org.devops.mjar.weixin.configuration.EnableWeixin;
import org.devops.mjar.weixin.configuration.ProfileLoadMode;
import org.devops.web.template.config.datasource.DevopsDataSourceType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

@Configuration
@EnableMjarExt(datasource = DevopsDataSourceType.DEVOPS_01)
@EnableApiEnhancer
//@EnableIWebApi(dataSource = DevopsDataSourceType.NAME)
@EnableIWebAuth(datasource = DevopsDataSourceType.DEVOPS_01)
@EnableIWebFile(datasource = DevopsDataSourceType.DEVOPS_01)
@EnableWeixin(datasource = DevopsDataSourceType.DEVOPS_01, initialMode = ProfileLoadMode.DatabaseLoader)
public class DevopsConfiguration implements DevopsModuleConfiguration {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
// 外部文件目录
//        registry.addResourceHandler("/**")
//                .addResourceLocations("file:D:/data/tmp/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
