package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAccountDetailParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/10/30 11:13
 * @description：子频道详情
 */
public class PolyvChannelAccountDetailRequester extends FunctionOperator<PolyvChannelAccountDetailParameter,
        PolyvChannelAccountDetailParameter.PolyvChannelAccountDetailParameterBuilder, PolyvChannelAccount> {

    public PolyvChannelAccountDetailRequester(BiFunction<PolyvChannelAccountDetailParameter, String, PolyvApiResult<PolyvChannelAccount>> function) {

        super(PolyvChannelAccountDetailParameter.PolyvChannelAccountDetailParameterBuilder.aPolyvChannelAccountDetailParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
