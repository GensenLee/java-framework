package org.devops.mjar.live.transform.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author GENSEN
 * @date 2021/6/28 11:31
 * @description：开启api转换器
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({MjarTransformerConfiguration.class, MjarTransformerConfigurationValueFactory.class})
public @interface EnableMjarTransform {

    /**
     * xml 文件路径
     * 例如
     *  <code>classpath*:transformer//*//*.xml</code>
     * @return
     */
    String locationPattern();

}
