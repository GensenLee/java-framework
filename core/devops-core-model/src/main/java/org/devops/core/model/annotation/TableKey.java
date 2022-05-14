package org.devops.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface TableKey {

	public final static String TYPE_UNIQUE_KEY = "UNIQUE_KEY";
	
	/**
	 * 类型
	 * @return
	 */
	String type() default TYPE_UNIQUE_KEY;
	
	/**
	 * 键
	 * @return
	 */
	@AliasFor("value")
	String[] column() default {};
	
	/**
	 * 键
	 * @return
	 */
	@AliasFor("column")
	String[] value()  default {};
}
