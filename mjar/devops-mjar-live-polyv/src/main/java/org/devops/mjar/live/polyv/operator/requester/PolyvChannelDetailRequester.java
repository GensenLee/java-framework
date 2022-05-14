package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/10/27 16:13
 * @description：独立授权播放请求
 */
public class PolyvChannelDetailRequester extends FunctionOperator<PolyvChannelParameter,
        PolyvChannelParameter.PolyvChannelReqParameterBuilder, PolyvChannelInfo> {

    public PolyvChannelDetailRequester(BiFunction<PolyvChannelParameter, String, PolyvApiResult<PolyvChannelInfo>> function) {
        super(PolyvChannelParameter.PolyvChannelReqParameterBuilder.aPolyvChannelParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
