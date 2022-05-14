package org.devops.iweb.operationlog.annotation;

import org.devops.iweb.operationlog.resolver.CustomParamResolver;
import org.devops.iweb.operationlog.resolver.OperationDescriptionResolver;
import org.devops.iweb.operationlog.resolver.PrepostResolver;

/**
 * @author GENSEN
 */
public @interface Resolver {

    /**
     * 操作描述解析器
     *
     * @return
     */
    Class<? extends OperationDescriptionResolver> opDescDefineResolver() default OperationDescriptionResolver.DefaultOperationDescriptionResolver.class;


    /**
     * 自定义参数解析器
     *
     * @return
     */
    Class<? extends CustomParamResolver> customParamsResolver() default CustomParamResolver.DefaultCustomParamsResolver.class;

    /**
     * 后置处理器
     *
     * @return
     */
    Class<? extends PrepostResolver> prepostResolver() default PrepostResolver.DefaultPrepostResolver.class;

    /**
     * 是否跳过日志保存
     * SpEl 表达式，表达式必须返回 布尔 类型
     *
     * @return
     */
    String isSkpDefine() default "";

}