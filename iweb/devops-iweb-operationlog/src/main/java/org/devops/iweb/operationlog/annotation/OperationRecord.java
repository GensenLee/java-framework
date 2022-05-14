package org.devops.iweb.operationlog.annotation;

import org.devops.iweb.operationlog.enums.CacheScope;
import org.devops.iweb.operationlog.enums.OperationType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author GENSEN
 * @date 2021/9/26 17:57
 * @description：操作标记
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.METHOD })
@Documented
public @interface OperationRecord {

    /**
     * 操作类型
     * @return
     */
    OperationType type() default OperationType.other;

    /**
     * 模块
     * @return
     */
    String module() default "";

    /**
     * 操作堆栈，功能项
     * @return
     */
    String[] stack() default {};

    /**
     * 操作描述生成表达式, SpEl 表达式
     * #input 入参，数组类型，一个一般引用使用 #input[0] 引用入参vo
     * #output 出参，ApiResult 类型
     * #outputObject 出参中的object对象 ApiResult.object
     * #throwable 方法抛出的异常
     * #recordTime 记录时间
     *
     * @return
     */
    String opDescDefine() default "";

    /**
     * 自定义参数定义
     * @return
     */
    CustomParamDefine[] customParams() default {};

    /**
     * 用户基础信息获取表达式，默认为获取iweb的userInfo
     * 返回对象必须实现 <code>org.devops.core.utils.interfaces.BaseUserInfo</code> 接口
     * @return
     */
    String userInfoDefine() default "";


    /**
     * 数据保留范围
     * @return
     */
    CacheScope[] cacheScope() default {CacheScope.input, CacheScope.output};

    /**
     * 日志
     * @return
     */
    Resolver resolver() default @Resolver;


}
