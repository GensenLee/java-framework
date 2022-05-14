package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateWarmUpParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/23 11:24
 * @description：修改暖场设置开关
 */
public class PolyvChannelUpdateWarmUpRequester extends FunctionOperator<PolyvChannelUpdateWarmUpParameter,
        PolyvChannelUpdateWarmUpParameter.PolyvChannelUpdateWarmUpParameterBuilder,String> {
    public PolyvChannelUpdateWarmUpRequester(Function<PolyvChannelUpdateWarmUpParameter, PolyvApiResult<String>> function) {
        super( PolyvChannelUpdateWarmUpParameter.PolyvChannelUpdateWarmUpParameterBuilder.aPolyvChannelUpdateWarmUpParameter(),function);
    }
}
