package org.devops.web.template.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
public class RedisLettucePoolProperties {

	private  int maxIdle;
	
	private  int maxActive;
	
	private  int minIdle;
	
	private  int maxWait;
	
	private  int maxTotal;
}
