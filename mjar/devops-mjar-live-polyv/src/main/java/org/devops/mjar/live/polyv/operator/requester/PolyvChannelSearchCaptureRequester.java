package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchCaptureParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/23 10:20
 * @description：查询频道直播截图
 */
public class PolyvChannelSearchCaptureRequester extends FunctionOperator<PolyvChannelSearchCaptureParameter,
        PolyvChannelSearchCaptureParameter.PolyvChannelSearchCaptureParameterBuilder,String> {
    public PolyvChannelSearchCaptureRequester(BiFunction<PolyvChannelSearchCaptureParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelSearchCaptureParameter.PolyvChannelSearchCaptureParameterBuilder.aPolyvChannelSearchCaptureParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
