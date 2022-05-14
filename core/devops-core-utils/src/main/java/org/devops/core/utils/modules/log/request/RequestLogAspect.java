package org.devops.core.utils.modules.log.request;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.util.FastJsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2022/3/11 11:34
 * @description 参数切面
 */
@Slf4j
@Aspect
public class RequestLogAspect {


    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution( * *(..)) && (" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)) ||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)) ||" +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        if (signature.getParameterNames().length > 0) {
            Map<String, Object> printMap = new HashMap<>();
            String[] parameterNames = signature.getParameterNames();
            Object[] args = pjp.getArgs();
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                String parameterName = parameterNames[i];
                if (arg instanceof File || arg instanceof MultipartFile || arg instanceof OutputStream || arg instanceof InputStream
                        || arg instanceof ServletRequest || arg instanceof ServletResponse) {
                    printMap.put(parameterName, arg.toString());
                }else {
                    printMap.put(parameterName, arg);
                }
            }
            log.info("method {}, input {}", pjp.getTarget().getClass().getSimpleName() + CommonConstant.POUND_MARK + signature.getName(), FastJsonUtils.toJsonString(printMap));
        }
        Object proceed = pjp.proceed(pjp.getArgs());
        if (proceed != null) {
            log.info("method {}, output {}", pjp.getTarget().getClass().getSimpleName() + CommonConstant.POUND_MARK + signature.getName(), FastJsonUtils.toJsonString(proceed));
        }
        return proceed;
    }


}
