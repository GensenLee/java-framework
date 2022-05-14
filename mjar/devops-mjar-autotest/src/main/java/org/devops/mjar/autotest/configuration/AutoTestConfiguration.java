package org.devops.mjar.autotest.configuration;

import org.devops.mjar.autotest.core.TestRestCore;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.mjar.autotest"
	})
public class AutoTestConfiguration implements ApplicationContextAware {
	
	private static final HttpClientOption[] DEFAULT_OPTIONS = {};

	private static final HttpClientOption[] SSL_OPTIONS = { HttpClientOption.SSL };

	private TestRestCore object;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		RestTemplateBuilder builder = getRestTemplateBuilder(applicationContext);
		boolean sslEnabled = isSslEnabled(applicationContext);
		TestRestCore core = new TestRestCore(builder.build(), null, null,
				sslEnabled ? SSL_OPTIONS : DEFAULT_OPTIONS);
		LocalHostUriTemplateHandler handler = new LocalHostUriTemplateHandler(
				applicationContext.getEnvironment(), sslEnabled ? "https" : "http");
		core.setUriTemplateHandler(handler);
		this.object = core;
	}
	
	private boolean isSslEnabled(ApplicationContext context) {
//		try {
//			AbstractConfigurableEmbeddedServletContainer container = context
//					.getBean(AbstractConfigurableEmbeddedServletContainer.class);
//			return container.getSsl() != null && container.getSsl().isEnabled();
//		}
//		catch (NoSuchBeanDefinitionException ex) {
//			return false;
//		}
		return false;
	}

	private RestTemplateBuilder getRestTemplateBuilder(
			ApplicationContext applicationContext) {
		try {
			return applicationContext.getBean(RestTemplateBuilder.class);
		}
		catch (NoSuchBeanDefinitionException ex) {
			return new RestTemplateBuilder();
		}
	}
	
	@Bean
	public TestRestCore testRestCore(){
		return object;
	}
}
