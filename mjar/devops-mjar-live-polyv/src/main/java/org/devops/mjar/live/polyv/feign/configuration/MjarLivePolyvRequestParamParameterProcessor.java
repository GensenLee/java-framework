package org.devops.mjar.live.polyv.feign.configuration;

import org.devops.mjar.live.polyv.feign.annotation.PolyvGetMapping;
import org.devops.mjar.live.polyv.feign.annotation.PolyvPostMapping;
import feign.MethodMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.annotation.RequestParamParameterProcessor;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * feignclient的Get请求方法参数必须添加 {@link RequestParam} 注解，否则参数对象会被解析请求Body 导致请求被重置为Post请求
 * 标记parameterIndex后{@link MjarLivePolyvQueryMapEncoder#encode(Object)} 才会被调用
 *
 * @author GENSEN
 * @date 2021/3/4 11:57
 * @description：参数解析器
 */
@Slf4j
public class MjarLivePolyvRequestParamParameterProcessor extends RequestParamParameterProcessor {

    @Override
    public boolean processArgument(AnnotatedParameterProcessor.AnnotatedParameterContext context, Annotation annotation, Method method) {
        int parameterIndex = context.getParameterIndex();
        Class<?> parameterType = method.getParameterTypes()[parameterIndex];
        MethodMetadata data = context.getMethodMetadata();

        if (!Map.class.isAssignableFrom(parameterType)) {
            for (Annotation methodAnnotation : method.getAnnotations()) {
                //标记query参数位置，否则参数会被认为body
                if (methodAnnotation.annotationType().isAssignableFrom(PolyvGetMapping.class) ||
                        methodAnnotation.annotationType().isAssignableFrom(PolyvPostMapping.class)) {
                    data.queryMapIndex(parameterIndex);
                    log.debug("参数解析器 - 入参类型[{}] 标记parameterIndex", parameterType);
                    return true;
                }
            }
        }
        return super.processArgument(context, annotation, method);
    }
}
