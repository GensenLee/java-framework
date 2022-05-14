package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateCountDownParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改直播倒计时设置
 */
public class PolyvChannelUpdateCountDownRequester extends FunctionOperator<PolyvChannelUpdateCountDownParameter,
        PolyvChannelUpdateCountDownParameter.PolyvChannelUpdateCountDownParameterBuilder,String> {
    public PolyvChannelUpdateCountDownRequester(BiFunction<PolyvChannelUpdateCountDownParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateCountDownParameter.PolyvChannelUpdateCountDownParameterBuilder.aPolyvChannelUpdateCountDownParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
