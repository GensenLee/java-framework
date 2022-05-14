package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAddReceiveInfoParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：提交中奖信息
 */
public class PolyvChannelAddReceiveInfoRequester extends FunctionOperator<PolyvChannelAddReceiveInfoParameter,
        PolyvChannelAddReceiveInfoParameter.PolyvChannelAddReceiveInfoParameterBuilder,String> {
    public PolyvChannelAddReceiveInfoRequester(Function<PolyvChannelAddReceiveInfoParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelAddReceiveInfoParameter.PolyvChannelAddReceiveInfoParameterBuilder.aPolyvChannelAddReceiveInfoParameter(), function);
    }
}
