package org.devops.web.test.bean.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

	private  String host;
	
	private  int port;
	
	private  String password;
	
	private  int timeout;
	
	private  boolean testOnBorrow;
	
	private  boolean blockWhenExhausted;
	
	private String[] nodes;
}
