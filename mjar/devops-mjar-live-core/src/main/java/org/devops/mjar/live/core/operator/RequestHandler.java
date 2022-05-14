package org.devops.mjar.live.core.operator;

import org.devops.core.utils.constant.ApiResultCode;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.devops.mjar.live.core.exception.ClosedRequestException;
import org.devops.mjar.live.core.sign.LiveApiProfiles;
import org.devops.mjar.live.core.sign.RequestHandleBean;
import feign.RequestTemplate;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author GENSEN
 * @date 2021/3/6 16:22
 * @description：请求持有
 */
public abstract class RequestHandler<P extends RequestHandleBean, B> {

    protected RequestHandler(ParameterBuilder<P> builder) {
        this.profile = MjarLiveContext.getContext().getMjarLiveProfileHandler().get();
        this.done = false;
        this.builder = builder;
        this.requestId = UUID.randomUUID().toString();
        MjarLiveContext.getContext().getMjarLiveRequestIdHandler().set(requestId);
    }

    /**
     * 请求id
     */
    @Getter
    protected String requestId;

    /**
     * 请求是否已经完成
     */
    protected boolean done;
    /**
     * 请求是否成功
     */
    protected boolean success;

    public boolean isSuccess() {
        return success;
    }

    /**
     * 签名信息
     */
    protected LiveApiProfiles profile;

    /**
     * 构造器
     */
    protected ParameterBuilder<P> builder;

    /**
     * 当前请求参数
     */
    protected P feignParameter;

    /**
     * uri路径参数
     */
    protected String pathVariableFieldKey;

    /**
     * @return
     */
    public B parameter() {
        if (this.done) {
            throw new ClosedRequestException();
        }
        return (B) builder;
    }

    /**
     * 请求后处理
     *
     * @param operateResult
     * @param result
     * @param param
     * @param <R>
     */
    protected <R> void finish(OperateResult<R> operateResult, R result, P param) {
        operateResult.setResult(result);
        RequestTemplate template = MjarLiveContext.getContext().getMjarLiveFeignRequestTemplateHandler().get();
        if (template != null) {
            //非本地请求
            log("URL[{}][{}]", template.method(), template.url());
            operateResult.setResponse(MjarLiveContext.getContext().getMjarLiveFeignResponseHandler().get());
        }
        Map<String, Object> fileMap = new HashMap<>();
        Map<String, Object> map = param.toMap();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() instanceof MultipartFile) {
                fileMap.put(e.getKey(), ((MultipartFile) e.getValue()).getName());
            }else if ( e.getValue() instanceof File){
                fileMap.put(e.getKey(), ((File) e.getValue()).getName());
            }
        }
        map.putAll(fileMap);
        log("入参[{}]", map.isEmpty() ? param.toJsonString() : map);
        log("出参[{}]", FastJsonUtils.toJsonString(result));
        clean();
    }

    private void clean() {
        MjarLiveContext.getContext().clean();
    }


    /**
     * 获取路径参数
     *
     * @param param
     * @return
     */
    protected String getPathVariable(P param) {
        List<Field> fieldList = BeanUtil.getAllField(param.getClass());
        Field afield = null;
        for (Field field : fieldList) {
            if (field.getName().equals(this.pathVariableFieldKey)) {
                afield = field;
                break;
            }
        }
        if (afield == null) {
            throw new IllegalArgumentException(param.getClass() + " 类中没有声明该变量 " + this.pathVariableFieldKey);
        }
        try {
            afield.setAccessible(true);
            return String.valueOf(afield.get(param));
        } catch (IllegalAccessException e) {
            log(e.getMessage());
        } finally {
            afield.setAccessible(false);
        }
        throw new CommonRuntimeException(ApiResultCode.SYSTEM_ERROR);
    }

    /**
     * 打印日志
     *
     * @param appendLogPattern
     * @param appendLogArguments
     */
    protected void log(String appendLogPattern, Object... appendLogArguments) {
        LiveApiLogger.logRequest(this.getClass(), requestId, appendLogPattern, appendLogArguments);
    }

    protected void handleException(Throwable throwable){
        if (LiveApiLogger.isDebugOpen()) {
            LiveApiLogger.log(requestId, throwable);
        }
    }
}
