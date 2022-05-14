package org.devops.mjar.ext.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.mjar.ext"
	})
public class EnableMjarExtConfiguration{
  
}
