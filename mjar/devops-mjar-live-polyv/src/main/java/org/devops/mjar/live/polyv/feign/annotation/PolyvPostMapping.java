package org.devops.mjar.live.polyv.feign.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gensen.Lee
 * @date 2020/10/10 10:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@RequestMapping(method = RequestMethod.POST)
public @interface PolyvPostMapping {
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {MediaType.APPLICATION_FORM_URLENCODED_VALUE};

    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {MediaType.APPLICATION_JSON_VALUE};
}
