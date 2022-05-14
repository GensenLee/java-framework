package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateGlobalEnabledParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/5 13:55
 * @description：设置频道是否应用通用设置开关
 */
public class PolyvChannelUpdateGlobalEnabledRequester extends FunctionOperator<PolyvChannelUpdateGlobalEnabledParameter,
        PolyvChannelUpdateGlobalEnabledParameter.PolyvChannelUpdateGlobalEnabledParameterBuilder, Boolean> {
    public PolyvChannelUpdateGlobalEnabledRequester(Function<PolyvChannelUpdateGlobalEnabledParameter, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelUpdateGlobalEnabledParameter.PolyvChannelUpdateGlobalEnabledParameterBuilder.aPolyvChannelUpdateGlobalEnabledParameter(), function);
    }
}
