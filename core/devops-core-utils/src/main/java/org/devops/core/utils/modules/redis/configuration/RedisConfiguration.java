package org.devops.core.utils.modules.redis.configuration;

import org.devops.core.utils.modules.redis.aspect.RedisAspect;
import org.devops.core.utils.modules.redis.transaction.RedisSimulatedTransactionAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.devops.core.utils.modules.redis"
	})
public class RedisConfiguration {
   
	@Bean
	public RedisAspect redisAspect(){
		return new RedisAspect();
	}

	@Bean
	public RedisSimulatedTransactionAspect redisTransactionAspect(){
		return new RedisSimulatedTransactionAspect();
	}

	private static String PREFIX;

	public static String getPrefix() {
		return PREFIX;
	}

	static void setPrefix(String prefix) {
		RedisConfiguration.PREFIX = prefix;
	}
}
