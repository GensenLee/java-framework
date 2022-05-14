package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCardPushPushParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：推送卡片
 */
public class PolyvChannelCardPushPushRequester extends FunctionOperator<PolyvChannelCardPushPushParameter,
        PolyvChannelCardPushPushParameter.PolyvChannelCardPushPushParameterBuilder, Object> {
    public PolyvChannelCardPushPushRequester(Function<PolyvChannelCardPushPushParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelCardPushPushParameter.PolyvChannelCardPushPushParameterBuilder.aPolyvChannelCardPushPushParameter(), function);
    }
}
