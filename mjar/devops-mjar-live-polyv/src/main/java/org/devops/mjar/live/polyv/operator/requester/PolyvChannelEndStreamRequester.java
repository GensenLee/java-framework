package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道直播状态为无直播
 */
public class PolyvChannelEndStreamRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, Object> {
    public PolyvChannelEndStreamRequester(BiFunction<PolyvChannelNoneParamParameter, String, PolyvApiResult<Object>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
