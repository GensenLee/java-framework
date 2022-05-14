package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateTokenParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/11/12 19:30
 * @description：频道token设置请求
 */
public class PolyvChannelUpdateTokenRequester extends FunctionOperator<PolyvChannelUpdateTokenParameter,
        PolyvChannelUpdateTokenParameter.PolyvChannelUpdateTokenParameterBuilder, Object> {
    public PolyvChannelUpdateTokenRequester(BiFunction<PolyvChannelUpdateTokenParameter, String, PolyvApiResult<Object>> biFunction) {
        super(PolyvChannelUpdateTokenParameter.PolyvChannelUpdateTokenParameterBuilder.aPolyvChannelUpdateTokenParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}

