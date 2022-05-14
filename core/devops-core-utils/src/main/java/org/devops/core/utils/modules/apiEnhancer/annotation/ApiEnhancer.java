package org.devops.core.utils.modules.apiEnhancer.annotation;

import org.devops.core.utils.modules.apiEnhancer.enums.ApiEnhancerType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xhz
 * 接口增强注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiEnhancer {
	
	
	/**
	 * 0 表示关闭缓存，-1表示永久
	 * @return
	 */
	@AliasFor("value")
	long expire() default 3000;
	
	@AliasFor("expire")
	long value() default 3000;
	
	String[] exclude() default {};
	
	boolean async() default false;
	
	ApiEnhancerType type() default ApiEnhancerType.CACHEABLE;

}
