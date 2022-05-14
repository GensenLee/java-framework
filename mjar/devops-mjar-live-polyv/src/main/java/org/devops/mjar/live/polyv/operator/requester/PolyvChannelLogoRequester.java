package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLogoParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * 修改频道播放器中显示的logo
 */
public class PolyvChannelLogoRequester extends FunctionOperator<PolyvChannelLogoParameter,
        PolyvChannelLogoParameter.PolyvChannelLogoParameterBuilder,String> {
    public PolyvChannelLogoRequester( BiFunction<PolyvChannelLogoParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelLogoParameter.PolyvChannelLogoParameterBuilder.aPolyvChannelLogoParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
