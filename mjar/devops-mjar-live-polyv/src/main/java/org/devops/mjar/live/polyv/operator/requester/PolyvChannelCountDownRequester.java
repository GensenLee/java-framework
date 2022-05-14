package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCountDown;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：查询频道直播开始时间
 */
public class PolyvChannelCountDownRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, PolyvCountDown> {
    public PolyvChannelCountDownRequester(BiFunction<PolyvChannelNoneParamParameter, String, PolyvApiResult<PolyvCountDown>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
