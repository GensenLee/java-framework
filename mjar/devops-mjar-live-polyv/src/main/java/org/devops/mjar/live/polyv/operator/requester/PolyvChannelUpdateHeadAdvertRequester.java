package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateHeadAdvertParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/28 11:20
 * @description：修改频道播放器片头广告
 */

public class PolyvChannelUpdateHeadAdvertRequester extends FunctionOperator<PolyvChannelUpdateHeadAdvertParameter,
        PolyvChannelUpdateHeadAdvertParameter.PolyvChannelUpdateHeadAdvertParameterBuilder,String> {
    public PolyvChannelUpdateHeadAdvertRequester( BiFunction<PolyvChannelUpdateHeadAdvertParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelUpdateHeadAdvertParameter.PolyvChannelUpdateHeadAdvertParameterBuilder.aPolyvChannelUpdateHeadAdvertParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
