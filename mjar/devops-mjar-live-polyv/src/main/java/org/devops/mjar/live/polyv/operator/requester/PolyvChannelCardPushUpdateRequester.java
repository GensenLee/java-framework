package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCardPushUpdateParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：修改频道卡片推送
 */
public class PolyvChannelCardPushUpdateRequester extends FunctionOperator<PolyvChannelCardPushUpdateParameter,
        PolyvChannelCardPushUpdateParameter.PolyvChannelCardPushUpdateParameterBuilder, Object> {
    public PolyvChannelCardPushUpdateRequester(Function<PolyvChannelCardPushUpdateParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelCardPushUpdateParameter.PolyvChannelCardPushUpdateParameterBuilder.aPolyvChannelCardPushUpdateParameter(), function);
    }
}
