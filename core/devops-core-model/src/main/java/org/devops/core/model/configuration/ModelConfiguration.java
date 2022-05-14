package org.devops.core.model.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.core.model"
	})
public class ModelConfiguration {
   
}
