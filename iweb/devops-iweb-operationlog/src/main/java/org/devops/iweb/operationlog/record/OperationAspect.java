package org.devops.iweb.operationlog.record;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.BooleanUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.operationlog.annotation.OperationRecord;
import org.devops.core.utils.interfaces.BaseUserInfo;
import org.devops.iweb.operationlog.configuration.IWebOperationLogConfiguration;
import org.devops.iweb.operationlog.constant.IwebOperationLogConstant;
import org.devops.iweb.operationlog.record.userinfo.DefaultBaseUserInfo;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GENSEN
 * @date 2021/9/26 17:54
 * @description：操作日志切面
 */
@Component
@Slf4j
@Aspect
public class OperationAspect {

    private static final ExecutorService STEALING_POOL = Executors.newWorkStealingPool();

    /**
     * @param pjp
     * @param operationRecord
     * @return
     * @throws Throwable
     */
    @Around("execution( * *(..)) && @annotation(operationRecord)")
    public Object around(ProceedingJoinPoint pjp, OperationRecord operationRecord) throws Throwable {
        EvaluationContext evaluationContext = new StandardEvaluationContext(pjp.getTarget());

        // 检查是否跳过保存日志
        if (StringUtil.isNotEmpty(operationRecord.resolver().isSkpDefine())) {
            try {
                Boolean isSkip = IwebOperationLogConstant.EXPRESSION_PARSER.parseExpression(operationRecord.resolver().isSkpDefine()).getValue(evaluationContext, Boolean.class);
                if (BooleanUtil.toBoolean(isSkip)) {
                    return pjp.proceed(pjp.getArgs());
                }
            } catch (EvaluationException | ParseException e) {
                log.error("resolver.isSkpDefine 解析失败", e);
            }
        }


        BaseUserInfo userInfo = getBaseUserInfo(operationRecord, evaluationContext);

        Object[] args = pjp.getArgs();

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            evaluationContext.setVariable(parameterNames[i], args[i]);
        }

        // 复制参数，避免执行过程被修改
        Object[] argsClone = null;

        if (args != null && args.length > 0) {
            try {
                argsClone = (Object[]) BeanUtil.deepClone(args);
            } catch (IOException | ClassNotFoundException e) {
                argsClone = args;
            }
        }

        Throwable t = null;
        Object proceed = null;
        try {
            proceed = pjp.proceed(args);
        } catch (Throwable throwable) {
            t = throwable;
        }
        Object proceedClone = null;
        if (proceed != null) {
            try {
                proceedClone = BeanUtil.deepClone(proceed);
            } catch (IOException | ClassNotFoundException e) {
                proceedClone = BeanUtil.copy(proceed, proceed.getClass());
            }
        }
        STEALING_POOL.execute(new OperationTask(argsClone, proceedClone, t, userInfo, operationRecord, evaluationContext));
        if (t != null) {
            throw t;
        }
        return proceed;
    }

    /**
     * 获取请求基础用户信息
     * @param operationRecord
     * @param evaluationContext
     * @return
     */
    private BaseUserInfo getBaseUserInfo(OperationRecord operationRecord, EvaluationContext evaluationContext) {
        BaseUserInfo userInfo = null;
        if (StringUtil.isNotEmpty(operationRecord.userInfoDefine())) {
            try {
                userInfo = IwebOperationLogConstant.EXPRESSION_PARSER.parseExpression(operationRecord.userInfoDefine()).getValue(evaluationContext, BaseUserInfo.class);
            } catch (EvaluationException | ParseException e) {
                log.error("operation.userInfoDefine() = " + operationRecord.userInfoDefine(), e);
            }
        }
        if (userInfo == null) {
            userInfo = IWebOperationLogConfiguration.getDefaultUserInfoDefineExpression().getValue(evaluationContext, BaseUserInfo.class);
        }
        if (userInfo == null) {
            userInfo = new DefaultBaseUserInfo();
        }
        return userInfo;
    }
}
