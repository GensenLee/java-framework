package org.devops.core.utils.modules.redis.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({RedisConfiguration.class, RedisEnvironmentConfiguration.class})
public @interface EnableRedis {
    /**
     * 全局key前缀
     * @return
     */
    String prefix() default "";
}
