package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdatePlayBackEnabledParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/13 21:18
 * @description：频道回放设置请求
 */
public class PolyvChannelUpdatePlayBackEnabledRequester extends FunctionOperator<PolyvChannelUpdatePlayBackEnabledParameter,
        PolyvChannelUpdatePlayBackEnabledParameter.PolyvChannelUpdatePlayBackEnabledParameterBuilder, Boolean> {
    public PolyvChannelUpdatePlayBackEnabledRequester(Function<PolyvChannelUpdatePlayBackEnabledParameter, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelUpdatePlayBackEnabledParameter.PolyvChannelUpdatePlayBackEnabledParameterBuilder.aPolyvChannelUpdatePlayBackEnabledParameter(), function);
    }
}
