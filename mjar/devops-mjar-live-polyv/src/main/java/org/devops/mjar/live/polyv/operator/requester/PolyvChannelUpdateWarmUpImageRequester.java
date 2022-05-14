package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateWarmUpImageParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/23 11:24
 * @description：修改暖场图片
 */
public class PolyvChannelUpdateWarmUpImageRequester extends FunctionOperator<PolyvChannelUpdateWarmUpImageParameter,
        PolyvChannelUpdateWarmUpImageParameter.PolyvChannelUpdateWarmUpImageParameterBuilder,String> {

    public PolyvChannelUpdateWarmUpImageRequester(BiFunction<PolyvChannelUpdateWarmUpImageParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelUpdateWarmUpImageParameter.PolyvChannelUpdateWarmUpImageParameterBuilder.aPolyvChannelUpdateWarmUpImageParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
