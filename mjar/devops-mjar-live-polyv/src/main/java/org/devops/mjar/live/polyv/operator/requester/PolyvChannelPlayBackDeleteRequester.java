package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackDeleteParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：删除视频库中的视频
 */
public class PolyvChannelPlayBackDeleteRequester extends FunctionOperator<PolyvChannelPlayBackDeleteParameter,
        PolyvChannelPlayBackDeleteParameter.PolyvChannelPlayBackDeleteParameterBuilder,String> {
    public PolyvChannelPlayBackDeleteRequester(BiFunction<PolyvChannelPlayBackDeleteParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackDeleteParameter.PolyvChannelPlayBackDeleteParameterBuilder.aPolyvChannelPlayBackDeleteParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
