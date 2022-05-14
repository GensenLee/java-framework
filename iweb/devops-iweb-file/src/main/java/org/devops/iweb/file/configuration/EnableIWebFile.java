package org.devops.iweb.file.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @author GENSEN
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({IWebFileConfiguration.class, IWebFileEnvironmentConfiguration.class})
public @interface EnableIWebFile {

	/**
	 * 上传目录
	 * @return
	 */
	String value() default "/data/upload";

	/**
	 * @return
	 */
	String path() default "/data/upload";
	
	String domain() default "";
	
	String datasource();

	/**
	 * 是否开启接口层
	 * @return
	 */
	boolean enableApi() default false;
}
