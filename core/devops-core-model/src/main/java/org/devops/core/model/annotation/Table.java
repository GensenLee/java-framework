package org.devops.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface Table {
	/**
	 * @Description 表名
	 * @author xhz
	 * @date 2018年8月27日 下午8:45:04
	 * @return
	 * @lastModifier
	 */
	@AliasFor("table")
	String value() default "";
	
	/**
	 * @Description 表名
	 * @author xhz
	 * @date 2018年8月27日 下午8:45:04
	 * @return
	 * @lastModifier
	 */
	@AliasFor("value")
	String table() default "";
	/**
	 * @Description 前缀
	 * @author xhz
	 * @date 2019年8月27日 下午8:45:22
	 * @return
	 * @lastModifier
	 */
	String prefix() default "";
	
	/**
	 * @Description 参数化
	 * @author xhz
	 * @date 2019年07月09日
	 * @return
	 * @lastModifier
	 */
	boolean parametric() default true;
	/**
	 * @Description 是否自动创建
	 * @author xhz
	 * @date 2018年8月27日 下午8:45:44
	 * @return
	 * @lastModifier
	 */
	boolean create() default false;
	
	/**
	 * @Description 注释
	 * @author xhz
	 * @date 2018年8月27日 下午8:46:54
	 * @return
	 * @lastModifier
	 */
	String comment() default "";
	
	/**
	 * @Description 字符集
	 * @author xhz
	 * @date 2018年8月27日 下午8:46:59
	 * @return
	 * @lastModifier
	 */
	String charset() default "utf8";
	
	/**
	 * @Description 引擎
	 * @author xhz
	 * @date 2018年8月27日 下午8:47:06
	 * @return
	 * @lastModifier
	 */
	String engine() default "InnoDB";
}
