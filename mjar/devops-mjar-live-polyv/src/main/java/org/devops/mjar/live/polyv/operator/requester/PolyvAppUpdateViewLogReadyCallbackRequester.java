package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUrlParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/3 11:13
 * @description：观看日志入库回调地址更新
 */
public class PolyvAppUpdateViewLogReadyCallbackRequester extends FunctionOperator<PolyvAppUrlParameter,
        PolyvAppUrlParameter.PolyvAppUrlParameterBuilder, Object> {

    public PolyvAppUpdateViewLogReadyCallbackRequester(Function<PolyvAppUrlParameter, PolyvApiResult<Object>> function) {
        super(PolyvAppUrlParameter.PolyvAppUrlParameterBuilder.aPolyvAppUrlParameter(), function);
    }
}
