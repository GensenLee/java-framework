package org.devops.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface TableIndex {

	public final static String TYPE_BTREE = "BTREE";
	
	public final static String TYPE_FULL_TEXT = "FULL_TEXT";
	
	public final static String TYPE_HASH = "HASH";
	
	public final static String TYPE_RTREE = "RTREE";
	
	/**
	 * 类型
	 * @return
	 */
	String type() default TYPE_BTREE;
	
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
