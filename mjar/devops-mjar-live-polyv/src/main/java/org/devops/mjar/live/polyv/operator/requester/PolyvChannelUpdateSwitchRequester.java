package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateSwitchParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：修改频道的功能开关状态
 */
public class PolyvChannelUpdateSwitchRequester extends FunctionOperator<PolyvChannelUpdateSwitchParameter,
        PolyvChannelUpdateSwitchParameter.PolyvChannelUpdateSwitchParameterBuilder,Object> {
    public PolyvChannelUpdateSwitchRequester(Function<PolyvChannelUpdateSwitchParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdateSwitchParameter.PolyvChannelUpdateSwitchParameterBuilder.aPolyvChannelUpdateSwitchParameter(), function);
    }
}
