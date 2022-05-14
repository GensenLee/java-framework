package org.devops.mjar.message.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({MjarMessageConfiguration.class,MjarMessageEnvironmentConfiguration.class})
public @interface EnableMjarMessage {

	String datasource();
	
	String token() default "";
}
