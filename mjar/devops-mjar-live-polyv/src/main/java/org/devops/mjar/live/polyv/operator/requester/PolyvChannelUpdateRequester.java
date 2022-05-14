package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/13 14:17
 * @description：频道信息更新请求
 */
public class PolyvChannelUpdateRequester extends FunctionOperator<PolyvChannelUpdateParameter,
        PolyvChannelUpdateParameter.PolyvChannelUpdateParameterBuilder, Boolean> {
    public PolyvChannelUpdateRequester(Function<PolyvChannelUpdateParameter, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelUpdateParameter.PolyvChannelUpdateParameterBuilder.aPolyvChannelUpdateParameter(), function);
    }
}
