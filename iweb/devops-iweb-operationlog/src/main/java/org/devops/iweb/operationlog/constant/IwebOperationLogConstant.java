package org.devops.iweb.operationlog.constant;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 *
 * @author GENSEN
 */
public class IwebOperationLogConstant {

    public static final String DEFAULT_USER_INFO_DEFINE = "T(org.devops.iweb.auth.context.AuthContext).getUserInfoInnerVO()";

    public static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
}
