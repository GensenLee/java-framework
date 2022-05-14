package org.devops.iweb.xxl.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({IWebXxlConfiguration.class,IWebXxlEnvironmentConfiguration.class})
public @interface EnableIWebXxl {
	
	String datasource();
}
