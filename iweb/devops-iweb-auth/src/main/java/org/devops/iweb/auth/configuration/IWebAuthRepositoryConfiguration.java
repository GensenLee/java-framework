package org.devops.iweb.auth.configuration;

import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
	    "org.devops.iweb.auth.repository",
	    "org.devops.iweb.auth.core",
	    "org.devops.iweb.auth.redis"
	})
@EnableRedis
public class IWebAuthRepositoryConfiguration{
  
}
