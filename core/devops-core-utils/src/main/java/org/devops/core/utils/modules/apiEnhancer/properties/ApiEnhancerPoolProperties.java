package org.devops.core.utils.modules.apiEnhancer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "api-enhancer.pool")
public class ApiEnhancerPoolProperties {

	int corePoolSize = 100;
	int maximumPoolSize = 1000;
	long keepAliveTime = 1000;
}
