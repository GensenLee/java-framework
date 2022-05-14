package org.devops.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.devops.core.model.emun.ModelAutoIdType;
import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ColumnAutoId {

	/**
	 * @Description 自动填充ID
	 * @author xhz
	 * @date 2018年8月27日 下午8:42:57
	 * @return
	 * @lastModifier
	 */
	@AliasFor("modelAutoIdType")
	ModelAutoIdType value() default ModelAutoIdType.SnowflakeTo36;
	
	@AliasFor("value")
	ModelAutoIdType modelAutoIdType() default ModelAutoIdType.SnowflakeTo36;
}
