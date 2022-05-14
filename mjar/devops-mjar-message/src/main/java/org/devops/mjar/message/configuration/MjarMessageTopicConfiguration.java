package org.devops.mjar.message.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"org.devops.mjar.message"
	})
public class MjarMessageTopicConfiguration{
  
}
