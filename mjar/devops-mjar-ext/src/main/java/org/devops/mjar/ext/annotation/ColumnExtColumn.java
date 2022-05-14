package org.devops.mjar.ext.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ColumnExtColumn {

	@AliasFor("name")
	String value() default "";
	
	@AliasFor("value")
	String name() default "";
	
	/**
	 * 备注，这个字段暂时没有意义
	 */
	String description() default "";
}
