package org.devops.core.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.devops.core.model.emun.ModelRedisType;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface ModelRedis {

	/**
	 * 类型
	 * @return
	 */
	ModelRedisType type() default ModelRedisType.HASH_MAP;
	
	/**
	 * 过期时间，当 type=ModelRedisType.SINGLE 生效
	 * @return
	 */
	int expire() default 0;
	
	/**
	 * 数据隔离的主键
	 * @return
	 */
	String[] redisKey() default {};
	
	/**
	 * 主键
	 * @return
	 */
	String redisPriKey() default "";
}
