package org.devops.iweb.xxl.configuration;

import org.devops.core.utils.spring.configsupport.DevopsModuleConfiguration;
import org.devops.iweb.xxl.interceptor.CookieInterceptor;
import org.devops.iweb.xxl.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.iweb.xxl"
	})
@PropertySource("classpath:xxl-job-admin.properties")
public class IWebXxlConfiguration implements DevopsModuleConfiguration {

	@Autowired
	private PermissionInterceptor permissionInterceptor;

	@Autowired
	private CookieInterceptor cookieInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor).addPathPatterns("/xxl-job-admin/**");
		registry.addInterceptor(cookieInterceptor).addPathPatterns("/xxl-job-admin/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/xxl-job-admin/static/**")
				.addResourceLocations("classpath:/META-INF/resources/static/");
	}

}
