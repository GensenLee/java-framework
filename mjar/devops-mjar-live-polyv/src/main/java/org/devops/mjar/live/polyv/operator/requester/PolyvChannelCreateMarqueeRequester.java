package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateMarqueeParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

public class PolyvChannelCreateMarqueeRequester extends FunctionOperator<PolyvChannelCreateMarqueeParameter,
        PolyvChannelCreateMarqueeParameter.PolyvChannelCreateMarqueeBuilder,String> {
    public PolyvChannelCreateMarqueeRequester(BiFunction<PolyvChannelCreateMarqueeParameter, String, PolyvApiResult<String>> biFunction) {
        super( PolyvChannelCreateMarqueeParameter.PolyvChannelCreateMarqueeBuilder.aPolyvChannelCreateMarquee(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY );
    }
}
