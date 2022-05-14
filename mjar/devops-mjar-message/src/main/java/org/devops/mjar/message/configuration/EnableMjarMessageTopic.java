package org.devops.mjar.message.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.devops.core.utils.modules.redis.configuration.EnableRedis;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({MjarMessageTopicConfiguration.class,MjarMessageTopicEnvironmentConfiguration.class})
@EnableRedis
public @interface EnableMjarMessageTopic {
	
	String datasource();
	
	// topic
	@AliasFor("topic")
	String[] value() default {};
	
	@AliasFor("value")
	String[] topic() default {};
	
	String key() default "";
	
}
