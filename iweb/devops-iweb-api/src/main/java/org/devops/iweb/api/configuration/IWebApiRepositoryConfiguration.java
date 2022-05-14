package org.devops.iweb.api.configuration;

import org.devops.core.utils.modules.apiEnhancer.configuration.EnableApiEnhancer;
import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.iweb.api.repository",
	    "org.devops.iweb.api.core",
	    "org.devops.iweb.api.redis"
	})
@EnableRedis
@EnableApiEnhancer
public class IWebApiRepositoryConfiguration{
  
}
