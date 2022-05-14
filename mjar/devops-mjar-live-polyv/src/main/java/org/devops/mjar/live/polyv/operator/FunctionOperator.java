package org.devops.mjar.live.polyv.operator;

import com.google.common.base.Throwables;
import lombok.Getter;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.mjar.live.core.exception.LiveApiException;
import org.devops.mjar.live.core.exception.UncompletedRequestException;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.core.operator.Executor;
import org.devops.mjar.live.core.operator.OperateResult;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.operator.RequestHandler;
import org.devops.mjar.live.core.sign.RequestHandleBean;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @param <F> 入参类型
 * @param <B> 参数构造器类型
 * @param <D> 出参data类型
 * @author GENSEN
 * @date 2020/10/26 17:06
 * @description：操作器
 */
public class FunctionOperator<F extends RequestHandleBean, B, D> extends RequestHandler<F, B>
        implements Executor<PolyvApiResult<D>> {

    public FunctionOperator(ParameterBuilder<F> builder, Function<F, PolyvApiResult<D>> function) {
        super(builder);
        this.function = function;
    }

    public FunctionOperator(ParameterBuilder<F> builder, BiFunction<F, String, PolyvApiResult<D>> biFunction, String pathVariableFieldKey) {
        super(builder);
        this.biFunction = biFunction;
        if (StringUtil.isEmpty(pathVariableFieldKey)) {
            throw new IllegalArgumentException("pathVariableFieldKey must not be null");
        }
        this.pathVariableFieldKey = pathVariableFieldKey;
    }

    /**
     * 请求功能
     */
    private Function<F, PolyvApiResult<D>> function;

    /**
     * 请求功能，带PathVariable注解参数
     */
    private BiFunction<F, String, PolyvApiResult<D>> biFunction;

    /**
     * 请求结果
     */
    @Getter
    OperateResult<PolyvApiResult<D>> operateResult;

    /**
     * 发起请求
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public PolyvApiResult<D> execute() {
        if (done) {
            return operateResult.getResult();
        }
        operateResult = new OperateResult<>(this);
        PolyvApiResult<D> applyResult = null;
        F param = builder.build();
        try {
            feignParameter = (F)param.deepClone();
        } catch (Exception e) {
            feignParameter = param;
        }
        try {
            VerifyUtil.verify(param);
            param.sign(profile);
            operateResult.start();
            applyResult = function == null ? biFunction.apply(param, getPathVariable(param)) : function.apply(param);
            operateResult.end();
            success = PolyvApiResult.Status.SUCCESS.getCode().equals(applyResult.getCode());
            done = true;
        } catch (Exception exception) {
            operateResult.end();
            success = false;
            applyResult = new PolyvApiResult<>(PolyvApiResult.Status.ERROR);
            Throwable cause = Throwables.getRootCause(exception);
            log("组件异常 [{}]", cause.getMessage());
            handleException(exception);
            operateResult.setThrowable(exception);
            applyResult.setMessage(cause.getMessage());
            if (StringUtil.isEmpty(applyResult.getMessage())) {
                applyResult.setMessage(cause.getClass().getSimpleName());
            }
            if (CommonException.class.isAssignableFrom(cause.getClass())) {
                applyResult.setCode(((CommonException) cause).getCode());
            }
            if (cause instanceof LiveApiException) {
                PolyvApiResult<?> errorBean = ((LiveApiException) cause).getErrorBean();
                if (errorBean != null) {
                    applyResult.setError(errorBean.getError());
                    applyResult.setStatus(errorBean.getStatus());
                }
            }
            done = false;
        } finally {
            finish(operateResult, applyResult, feignParameter);
        }
        return operateResult.getResult();
    }

    /**
     * @return
     */
    public D getData() {
        if (!this.done) {
            log("请求未完成");
            throw new UncompletedRequestException("execute request before get data");
        }
        if (operateResult.getResult() == null) {
            return null;
        }
        return operateResult.getResult().getData();
    }

}