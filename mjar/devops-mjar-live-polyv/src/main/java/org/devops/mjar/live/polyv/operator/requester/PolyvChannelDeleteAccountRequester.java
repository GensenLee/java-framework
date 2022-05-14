package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteAccountParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2021/3/2 17:34
 * @description：子频道删除请求
 */
public class PolyvChannelDeleteAccountRequester extends FunctionOperator<PolyvChannelDeleteAccountParameter,
        PolyvChannelDeleteAccountParameter.PolyvChannelDeleteAccountParameterBuilder, Object> {
    public PolyvChannelDeleteAccountRequester(BiFunction<PolyvChannelDeleteAccountParameter, String, PolyvApiResult<Object>> function) {
        super(PolyvChannelDeleteAccountParameter.PolyvChannelDeleteAccountParameterBuilder.aPolyvChannelDeleteAccountParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
