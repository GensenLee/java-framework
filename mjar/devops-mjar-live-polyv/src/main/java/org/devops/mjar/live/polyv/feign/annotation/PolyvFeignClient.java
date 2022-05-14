package org.devops.mjar.live.polyv.feign.annotation;

import org.devops.mjar.live.polyv.feign.configuration.MjarLivePolyvFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author GENSEN
 * @date 2020/10/16 18:14
 * @description：polyv专用
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@FeignClient(url = "${live.host.api}",
        contextId = "PolyvFeignClient",
        configuration = MjarLivePolyvFeignClientConfiguration.class)
@Component
public @interface PolyvFeignClient {

    @AliasFor(annotation = FeignClient.class)
    Class<?>[] configuration() default MjarLivePolyvFeignClientConfiguration.class;

    @AliasFor(annotation = FeignClient.class)
    String name();

}
