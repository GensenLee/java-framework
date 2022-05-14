package org.devops.core.ie.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Inherited
@Documented
public @interface ExcelRow {

	
	/**
	 * 位置 +0${R} A${R}
	 * @return
	 */
	String position();
	
	/**
	 * 是否插入
	 * @return
	 */
	boolean insert() default false;
}
