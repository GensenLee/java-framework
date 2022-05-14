package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSentRewardMsgParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：发送打赏消息
 */
public class PolyvChannelSentRewardMsgRequester extends FunctionOperator<PolyvChannelSentRewardMsgParameter,
        PolyvChannelSentRewardMsgParameter.PolyvChannelSentRewardMsgParameterBuilder,String> {
    public PolyvChannelSentRewardMsgRequester(Function<PolyvChannelSentRewardMsgParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelSentRewardMsgParameter.PolyvChannelSentRewardMsgParameterBuilder.aPolyvChannelSentRewardMsgParameter(), function);
    }
}
