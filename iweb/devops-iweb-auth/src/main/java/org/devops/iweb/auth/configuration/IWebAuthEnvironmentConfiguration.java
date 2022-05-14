package org.devops.iweb.auth.configuration;

import java.util.Map;

import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.auth.datasource.AuthDynamicDataSource;
import org.devops.iweb.auth.model.AuthApp;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

@Configuration
public class IWebAuthEnvironmentConfiguration implements ImportBeanDefinitionRegistrar,EnvironmentAware{

	private Environment environment;
	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata,
			BeanDefinitionRegistry registry) {
		Map<String, Object> defaultAttrs = metadata
				.getAnnotationAttributes(EnableIWebAuth.class.getName(), true);

		if (defaultAttrs == null) {
			defaultAttrs = metadata
					.getAnnotationAttributes(EnableIWebAuthRepository.class.getName(), true);
		}
		if(defaultAttrs != null) {
			String dataSource = resolve(defaultAttrs.get("datasource").toString());
			DataSourceMapper.setPackage(AuthApp.class.getPackage().getName(), dataSource);
			AuthDynamicDataSource.setDataSource(dataSource);
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		
	}
	
	private String resolve(String value) {
		if (StringUtil.isNotEmpty(value)) {
			return this.environment.resolvePlaceholders(value);
		}
		return value;
	}
   
}
