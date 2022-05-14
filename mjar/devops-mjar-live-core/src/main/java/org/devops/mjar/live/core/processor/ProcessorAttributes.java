package org.devops.mjar.live.core.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解于 <link>com.sinmn.polyv.core.client.Processor</link> 子类
 * @author GENSEN
 * @date 2021/3/19 15:12
 * @description：配置属性
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessorAttributes {

    ProcessorType clientType();

}
