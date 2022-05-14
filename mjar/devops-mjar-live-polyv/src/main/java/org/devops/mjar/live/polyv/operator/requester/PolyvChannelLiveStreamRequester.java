package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道状态为直播中
 */
public class PolyvChannelLiveStreamRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, Object> {
    public PolyvChannelLiveStreamRequester(BiFunction<PolyvChannelNoneParamParameter, String, PolyvApiResult<Object>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
