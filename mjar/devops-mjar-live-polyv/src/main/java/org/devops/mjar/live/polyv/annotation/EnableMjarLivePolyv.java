package org.devops.mjar.live.polyv.annotation;

import org.springframework.context.annotation.Import;

import org.devops.mjar.live.polyv.feign.configuration.MjarLivePolyvConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author GENSEN
 * @date 2020/10/21 12:00
 * @description：开启保利威API
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import(MjarLivePolyvConfiguration.class)
public @interface EnableMjarLivePolyv {
}
