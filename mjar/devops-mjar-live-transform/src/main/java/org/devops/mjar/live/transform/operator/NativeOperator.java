package org.devops.mjar.live.transform.operator;

import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.PostingBodyProvider;
import lombok.Getter;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.util.StreamUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.HttpResult;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.operator.Executor;
import org.devops.mjar.live.core.operator.OperateResult;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.operator.RequestHandler;
import org.devops.mjar.live.core.sign.RequestHandleBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author GENSEN
 * @date 2021/6/24 18:14
 * @description：原生请求器
 */
public class NativeOperator<P extends RequestHandleBean, B> extends RequestHandler<P, B> implements Executor<ResponseEntity<byte[]>> {

    protected NativeOperator(ParameterBuilder<P> builder, String targetUri, HttpMethod httpMethod) {
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
    OperateResult<ResponseEntity<byte[]>> operateResult;


    @Override
    public ResponseEntity<byte[]> execute() {
        if (done) {
            return operateResult.getResult();
        }
        operateResult = new OperateResult<>(this);
        ResponseEntity<byte[]> applyResult = null;
        P param = builder.build();
        feignParameter = param;
        try {
            VerifyUtil.verify(param);
            param.sign(profile);
            Map<String, Object> map = param.toMap();
            HttpResult httpResult;
            operateResult.start();
            switch (httpMethod) {
                case GET:
                    httpResult = HttpUtils.doHttpGet(this.targetUri, map);
                    break;
                case POST:
                    Object body = ((PostingBodyProvider) param).body();
                    if (body == null) {
                        httpResult = HttpUtils.doHttpPost(this.targetUri, map);
                    }else {
                        httpResult = HttpUtils.doHttpPost(this.targetUri, map, body);
                    }
                    break;
                default:
                    throw new LiveApiRuntimeException("unsupported method");
            }
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(StreamUtil.copyToByteArray(httpResult.getResponse()), HttpStatus.OK);
            operateResult.setResult(responseEntity);
            operateResult.end();
            success = true;
            done = true;
        } catch (Exception exception) {
            operateResult.end();
            success = false;
            applyResult = ResponseEntity.badRequest().build();
            Throwable cause = exception;
            while (cause != null && cause.getCause() != null) {
                if (cause.getCause() instanceof TimeoutException) {
                    break;
                }
                cause = cause.getCause();
            }
            log("polyv transform exception [{}]", cause.getMessage());
            done = false;
        } finally {
            super.finish(operateResult, applyResult, param);
        }
        return operateResult.getResult();
    }
}
