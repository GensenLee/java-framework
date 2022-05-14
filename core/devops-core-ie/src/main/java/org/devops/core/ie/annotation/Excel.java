package org.devops.core.ie.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Inherited
@Documented
public @interface Excel {

	/**
	 * @description 表格
	 * @author xhz
	 */
	String sheet() default "Sheet1";
	
	/**
	 * @description 模板
	 * @author xhz
	 */
	String template() default "";
}
