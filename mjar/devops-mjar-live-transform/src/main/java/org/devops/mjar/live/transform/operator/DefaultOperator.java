package org.devops.mjar.live.transform.operator;

import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.PostingBodyProvider;
import lombok.Getter;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.core.operator.Executor;
import org.devops.mjar.live.core.operator.LiveApiLogger;
import org.devops.mjar.live.core.operator.OperateResult;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.operator.RequestHandler;
import org.devops.mjar.live.core.sign.RequestHandleBean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author GENSEN
 * @date 2021/6/24 18:13
 * @description：默认请求器
 */
public class DefaultOperator<P extends RequestHandleBean, B> extends RequestHandler<P, B> implements Executor<PolyvApiResult<Object>> {

    public DefaultOperator(ParameterBuilder<P> builder, String targetUri, HttpMethod httpMethod) {
        super(builder);
        this.targetUri = targetUri;
        this.httpMethod = httpMethod;
    }


    /**
     * 目标uri
     */
    private String targetUri;

    private HttpMethod httpMethod;


    /**
     * 请求结果
     */
    @Getter
    OperateResult<PolyvApiResult<Object>> operateResult;


    @Override
    public PolyvApiResult<Object> execute() {
        if (done) {
            return operateResult.getResult();
        }
        operateResult = new OperateResult<>(this);
        PolyvApiResult<Object> applyResult = null;
        P param = builder.build();
        feignParameter = param;
        try {
            VerifyUtil.verify(param);
            param.sign(profile);
            Map<String, Object> map = param.toMap();
            operateResult.start();
            String resultString;
            LiveApiLogger.log(DefaultOperator.class, String.format("url [%s]", this.targetUri));
            LiveApiLogger.log(DefaultOperator.class, String.format("send query [%s]", FastJsonUtils.toJsonString(map)));
            switch (httpMethod) {
                case GET:
                    resultString = HttpUtils.doGet(this.targetUri, map);
                    break;
                case POST:
                    Object body = ((PostingBodyProvider) param).body();
                    if (body == null) {
                        resultString = HttpUtils.doPost(this.targetUri, map);
                    } else {
                        LiveApiLogger.log(DefaultOperator.class, String.format("send body [%s]", FastJsonUtils.toJsonString(body)));
                        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
                        headerMap.add("Content-Type", "application/json");
                        resultString = HttpUtils.doHttpPost(this.targetUri,headerMap, map, body).readResponseAsString();
                    }
                    break;
                default:
                    throw new LiveApiRuntimeException("unsupported method");
            }
            applyResult = FastJsonUtils.getBean(resultString, PolyvApiResult.class);
            operateResult.end();
            success = true;
            done = true;
        } catch (Exception exception) {
            operateResult.end();
            success = false;
            exception.printStackTrace();
            applyResult = new PolyvApiResult<>(PolyvApiResult.Status.ERROR);
            Throwable cause = exception;
            while (cause != null && cause.getCause() != null) {
                if (cause.getCause() instanceof TimeoutException) {
                    break;
                }
                cause = cause.getCause();
            }
            log("polyv transform exception [{}]", cause.getMessage());
            applyResult.setMessage(cause.getMessage());
            if (CommonException.class.isAssignableFrom(cause.getClass())) {
                applyResult.setCode(((CommonException) cause).getCode());
            }
            done = false;
        } finally {
            super.finish(operateResult, applyResult, param);
        }
        return operateResult.getResult();
    }
}
