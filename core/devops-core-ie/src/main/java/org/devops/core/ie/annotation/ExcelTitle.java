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
public @interface ExcelTitle {

	/**
	 * 标题
	 * @return
	 */
	String[] value() default {};
	
	/**
	 * 跨度
	 * @return
	 */
	int[] span() default {};
	
	/**
	 * 宽度
	 * @return
	 */
	double[] width() default {};
	
	/**
	 * 位置
	 * @return
	 */
	String position() default "";
}
