package org.devops.iweb.operationlog.configuration;

import org.devops.iweb.operationlog.constant.IwebOperationLogConstant;
import org.devops.iweb.operationlog.enums.SubTableMode;
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
@Import({IWebOperationLogConfiguration.class, IWebOperationLogEnvironmentConfiguration.class})
public @interface EnableIWebOperationLog {

	/**
	 * 数据源
	 * @return
	 */
	String datasource();

	/**
	 * 用户基础信息获取表达式，默认为获取iweb的userInfo
	 * 返回对象必须实现 <code>org.devops.core.utils.interfaces.BaseUserInfo</code> 接口
	 * @return
	 */
	String defaultUserInfoDefine() default IwebOperationLogConstant.DEFAULT_USER_INFO_DEFINE;

	/**
	 * 是否开启接口层
	 * @return
	 */
	boolean enableApi() default false;

	/**
	 * 分表模式，默认不分表
	 * @return
	 */
	SubTableMode subTableMode() default SubTableMode.NONE;
}
