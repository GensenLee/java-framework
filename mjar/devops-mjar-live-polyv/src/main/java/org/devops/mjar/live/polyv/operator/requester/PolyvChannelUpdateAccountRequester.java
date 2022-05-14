package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAccountParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2021/3/1 14:53
 * @description：子频道更新请求
 */
public class PolyvChannelUpdateAccountRequester extends FunctionOperator<PolyvChannelUpdateAccountParameter,
        PolyvChannelUpdateAccountParameter.PolyvChannelUpdateAccountParameterBuilder, String> {
    public PolyvChannelUpdateAccountRequester(BiFunction<PolyvChannelUpdateAccountParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateAccountParameter.PolyvChannelUpdateAccountParameterBuilder.aPolyvChannelUpdateAccountParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
