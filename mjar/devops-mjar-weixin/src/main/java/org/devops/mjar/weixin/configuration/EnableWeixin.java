package org.devops.mjar.weixin.configuration;

import org.devops.mjar.weixin.profileloader.WeixinProfilesLoader;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author GENSEN
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({WxDeclarationBeans.class, MjarWeixinEnvironmentConfiguration.class, MjarWeixinConfiguration.class})
public @interface EnableWeixin {

    /**
     * 数据源
     * @return
     */
    String datasource() default "";

    /**
     * 初始化配置模式
     * @return
     */
    ProfileLoadMode initialMode() default ProfileLoadMode.PropertiesLoader;

    /**
     * mode == CustomLoader 时需要实现一个loader
     * @see WeixinProfilesLoader
     * @return
     */
    Class<?> loader() default Void.class;

}
