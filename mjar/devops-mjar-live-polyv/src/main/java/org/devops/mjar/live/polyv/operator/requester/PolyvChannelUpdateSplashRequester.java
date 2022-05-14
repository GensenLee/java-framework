package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateSplashParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改引导图
 */
public class PolyvChannelUpdateSplashRequester extends FunctionOperator<PolyvChannelUpdateSplashParameter,
        PolyvChannelUpdateSplashParameter.PolyvChannelUpdateSplashParameterBuilder,String> {
    public PolyvChannelUpdateSplashRequester(BiFunction<PolyvChannelUpdateSplashParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateSplashParameter.PolyvChannelUpdateSplashParameterBuilder.aPolyvChannelUpdateSplashParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
