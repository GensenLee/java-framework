package org.devops.iweb.operationlog.configuration;

import org.devops.iweb.operationlog.constant.IwebOperationLogConstant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.expression.Expression;

/**
 * @author GENSEN
 */
@Configuration
@ComponentScan(
        basePackages = {
                "org.devops.iweb.operationlog.configuration",
                "org.devops.iweb.operationlog.repository",
                "org.devops.iweb.operationlog.record"
        }
)
public class IWebOperationLogConfiguration {

    /**
     * 全局默认获取userInfo表达式
     */
    private static String DEFAULT_USER_INFO_DEFINE_STRING;

    private static Expression DEFAULT_USER_INFO_DEFINE_EXPRESSION;

    static void setDefaultUserInfoDefine(String exp) {
        DEFAULT_USER_INFO_DEFINE_STRING = exp;
        DEFAULT_USER_INFO_DEFINE_EXPRESSION = IwebOperationLogConstant.EXPRESSION_PARSER.parseExpression(DEFAULT_USER_INFO_DEFINE_STRING);
    }

    public static Expression getDefaultUserInfoDefineExpression() {
        return DEFAULT_USER_INFO_DEFINE_EXPRESSION;
    }

}
