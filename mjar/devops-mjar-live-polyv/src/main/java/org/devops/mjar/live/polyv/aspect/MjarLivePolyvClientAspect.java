package org.devops.mjar.live.polyv.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.devops.mjar.live.core.processor.Processor;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author GENSEN
 * @date 2021/3/12 18:03
 * @description：client执行切面
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
@Slf4j
@Lazy(false)
public class MjarLivePolyvClientAspect {

    /**
     * 定义环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.devops.mjar.live.polyv.client..*Request(..)) && this(org.devops.mjar.live.core.processor.Processor)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MjarLiveContext.getContext().getMjarLiveProfileHandler().set(((Processor) pjp.getTarget()).profile());
        Object proceed = pjp.proceed();
        MjarLiveContext.getContext().getMjarLiveProfileHandler().remove();
        return proceed;
    }
}
