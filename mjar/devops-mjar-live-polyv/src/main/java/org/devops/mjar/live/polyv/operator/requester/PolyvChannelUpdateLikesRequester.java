package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateLikesParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道点赞数和观看次数
 */
public class PolyvChannelUpdateLikesRequester extends FunctionOperator<PolyvChannelUpdateLikesParameter,
        PolyvChannelUpdateLikesParameter.PolyvChannelUpdateLikesParameterBuilder,String> {
    public PolyvChannelUpdateLikesRequester(BiFunction<PolyvChannelUpdateLikesParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateLikesParameter.PolyvChannelUpdateLikesParameterBuilder.aPolyvChannelUpdateLikesParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
