package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackSetDefaultParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改视频库的默认视频
 */
public class PolyvChannelPlayBackSetDefaultRequester extends FunctionOperator<PolyvChannelPlayBackSetDefaultParameter,
        PolyvChannelPlayBackSetDefaultParameter.PolyvChannelPlayBackSetDefaultParameterBuilder,String> {
    public PolyvChannelPlayBackSetDefaultRequester(BiFunction<PolyvChannelPlayBackSetDefaultParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackSetDefaultParameter.PolyvChannelPlayBackSetDefaultParameterBuilder.aPolyvChannelPlayBackSetDefaultParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
