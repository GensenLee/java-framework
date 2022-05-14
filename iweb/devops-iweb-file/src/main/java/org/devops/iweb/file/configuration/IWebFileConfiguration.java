package org.devops.iweb.file.configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.devops.iweb.file.web.controller.IWebFileViewController;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
@ComponentScan(basePackages = {
        "org.devops.iweb.file.configuration",
        "org.devops.iweb.file.core",
        "org.devops.iweb.file.main",
        "org.devops.iweb.file.repository",
        "org.devops.iweb.file.service",
})
public class IWebFileConfiguration {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(104857600); //100M
        commonsMultipartResolver.setMaxInMemorySize(4096); //4K
        return commonsMultipartResolver;
    }

    @Bean
    public FilterRegistrationBean urlRewrite() {
        UrlRewriteFilter rewriteFilter = new UrlRewriteFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(rewriteFilter);
        registration.setUrlPatterns(Arrays.asList(PTAH_PREFIX + "*"));
        Map<String, String> initParam = new HashMap<String, String>();
        initParam.put("modRewriteConfText", "RewriteRule ^" + PTAH_PREFIX + "(.*)([^\\.do])$ " + IWebFileViewController.FILE_VIEW_API + "?fileName=/$1$2");
        initParam.put("infoLevel", "INFO");
        registration.setInitParameters(initParam);
        return registration;
    }

    /**
     * 上传文件地址前缀
     */
    public final static String PTAH_PREFIX = "/api/file/";

}
