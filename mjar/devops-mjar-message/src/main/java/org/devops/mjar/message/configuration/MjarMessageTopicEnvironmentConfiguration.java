package org.devops.mjar.message.configuration;

import java.util.Map;
import java.util.UUID;

import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.message.datasource.MessageDynamicDataSource;
import org.devops.mjar.message.model.IMessageModel;
import org.devops.mjar.message.topic.constant.MjarMessageTopicConstant;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

@Configuration
public class MjarMessageTopicEnvironmentConfiguration implements ImportBeanDefinitionRegistrar,EnvironmentAware{

	private Environment environment;
	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata,
			BeanDefinitionRegistry registry) {
		Map<String, Object> defaultAttrs = metadata
				.getAnnotationAttributes(EnableMjarMessageTopic.class.getName(), true);

		if (defaultAttrs != null) {
			String[] topic = (String[])defaultAttrs.get("value");
			for(int i =0; i<topic.length; i++) {
				topic[i] = resolve(topic[i]);
			}
			MjarMessageTopicConstant.topic = topic;
			
			String key = resolve(defaultAttrs.get("key").toString());
			
			if(StringUtil.isEmpty(key)) {
				key = UUID.randomUUID().toString();
			}
			
			MjarMessageTopicConstant.key = key;
			
			String dataSource = resolve(defaultAttrs.get("datasource").toString());
			DataSourceMapper.setPackage(IMessageModel.class.getPackage().getName(), dataSource);
			MessageDynamicDataSource.setDataSource(dataSource);
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
