package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppNoneParamParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/3 11:13
 * @description：观看日志入库回调地址查询
 */
public class PolyvAppViewLogReadyCallbackRequester extends FunctionOperator<PolyvAppNoneParamParameter,
        PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder, String> {

    public PolyvAppViewLogReadyCallbackRequester(Function<PolyvAppNoneParamParameter, PolyvApiResult<String>> function) {
        super(PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder.aPolyvAppNoneParamParameter(), function);
    }
}
