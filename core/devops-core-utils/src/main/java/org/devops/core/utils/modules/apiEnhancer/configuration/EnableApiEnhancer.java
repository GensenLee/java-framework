package org.devops.core.utils.modules.apiEnhancer.configuration;

import org.devops.core.utils.modules.redis.configuration.RedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({ApiEnhancerConfiguration.class, RedisConfiguration.class})
public @interface EnableApiEnhancer {

}
