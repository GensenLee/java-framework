package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSentFavorParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：发送点赞
 */
public class PolyvChannelSentFavorRequester extends FunctionOperator<PolyvChannelSentFavorParameter,
        PolyvChannelSentFavorParameter.PolyvChannelSentFavorParameterBuilder,String> {
    public PolyvChannelSentFavorRequester(BiFunction<PolyvChannelSentFavorParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelSentFavorParameter.PolyvChannelSentFavorParameterBuilder.aPolyvChannelSentFavorParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
