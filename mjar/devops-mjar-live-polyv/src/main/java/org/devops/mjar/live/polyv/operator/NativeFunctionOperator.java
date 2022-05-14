package org.devops.mjar.live.polyv.operator;

import com.google.common.base.Throwables;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.mjar.live.core.operator.Executor;
import org.devops.mjar.live.core.operator.OperateResult;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import lombok.Getter;
import org.devops.mjar.live.core.operator.RequestHandler;
import org.devops.mjar.live.core.sign.RequestHandleBean;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/26 17:06
 * @description：原生操作器
 */
public class NativeFunctionOperator<F extends RequestHandleBean, B, D> extends RequestHandler<F, B> implements Executor<D> {


    public NativeFunctionOperator(ParameterBuilder<F> builder, Function<F, D> function) {
        super(builder);
        this.function = function;
    }

    public NativeFunctionOperator(ParameterBuilder<F> builder, BiFunction<F, String, D> biFunction, String pathVariableFieldKey ){
        super(builder);
        this.biFunction = biFunction;
        if (StringUtil.isEmpty(pathVariableFieldKey)) {
            throw new IllegalArgumentException("pathVariableFieldKey must not be null");
        }
        this.pathVariableFieldKey = pathVariableFieldKey;
    }


    /**
     * 请求功能，带PathVariable注解参数
     */
    private BiFunction<F, String, D> biFunction;

    /**
     * 请求功能
     */
    private Function<F, D> function;

    /**
     * 请求结果
     */
    @Getter
    OperateResult<D> operateResult;

    /**
     * 发起请求
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public D execute() {
        if (done) {
            return operateResult.getResult();
        }
        operateResult = new OperateResult<>(this);
        D applyResult = null;
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
            applyResult = function == null ? biFunction.apply(param,getPathVariable(param)) : function.apply(param);
            operateResult.end();
            success = true;
            done = true;
        } catch (Exception exception) {
            operateResult.end();
            success = false;
            Throwable cause = Throwables.getRootCause(exception);
            log("组件异常 [{}]", cause.getMessage());
            handleException(exception);
            operateResult.setThrowable(exception);
            done = false;
        } finally {
            finish(operateResult, applyResult, param);
        }
        return operateResult.getResult();
    }

}