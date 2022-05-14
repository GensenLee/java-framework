package org.devops.mjar.message.configuration;

import org.devops.core.utils.modules.apiEnhancer.configuration.EnableApiEnhancer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableApiEnhancer
@Configuration
@ComponentScan(basePackages = {
		"org.devops.mjar.message.repository",
		"org.devops.mjar.message.main",
		"org.devops.mjar.message.core",
		"org.devops.mjar.message.sms"
	})
public class MjarMessageConfiguration{
  
}
