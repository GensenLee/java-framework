package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateWarmUpVideoParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/23 11:57
 * @description：修改暖场视频
 */
public class PolyvChannelUpdateWarmUpVideoRequester extends FunctionOperator<PolyvChannelUpdateWarmUpVideoParameter,
        PolyvChannelUpdateWarmUpVideoParameter.PolyvChannelUpdateWarmVideoParameterBuilder,String> {
    public PolyvChannelUpdateWarmUpVideoRequester(BiFunction<PolyvChannelUpdateWarmUpVideoParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelUpdateWarmUpVideoParameter.PolyvChannelUpdateWarmVideoParameterBuilder.aPolyvChannelUpdateWarmVideoParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
