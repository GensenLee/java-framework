package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelSwitchState;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchSwitchParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/29 9:46
 * @description：查询频道的功能开关状态
 */
public class PolyvChannelSearchSwitchRequester extends FunctionOperator<PolyvChannelSearchSwitchParameter,
        PolyvChannelSearchSwitchParameter.PolyvChannelSearchSwitchParameterBuilder, List<PolyvChannelSwitchState>> {
    public PolyvChannelSearchSwitchRequester(Function<PolyvChannelSearchSwitchParameter, PolyvApiResult<List<PolyvChannelSwitchState>>> function) {
        super(PolyvChannelSearchSwitchParameter.PolyvChannelSearchSwitchParameterBuilder.aPolyvChannelSearchSwitchParameter(), function);
    }
}
