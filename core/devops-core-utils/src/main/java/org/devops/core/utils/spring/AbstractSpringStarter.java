package org.devops.core.utils.spring;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.spring.configsupport.SingletonWebMvcConfigurationSupport;
import org.springframework.beans.BeansException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;

/**
 * SpringBootServletInitializer 用于支持war包部署
 * @author GENSEN
 */
@Slf4j
@Import({SingletonWebMvcConfigurationSupport.class, DefaultDevopsModuleConfiguration.class})
public class AbstractSpringStarter extends SpringBootServletInitializer implements ApplicationContextAware{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(getClass());
	}

	/**
	 * 在这里setApplicationContext能有较高的优先级得到context
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.setContext(applicationContext);
	}
}
