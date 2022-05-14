package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCardPushCancelParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：取消推送卡片
 */
public class PolyvChannelCardPushCancelRequester extends FunctionOperator<PolyvChannelCardPushCancelParameter,
        PolyvChannelCardPushCancelParameter.PolyvChannelCardPushCancelParameterBuilder, Object> {
    public PolyvChannelCardPushCancelRequester(Function<PolyvChannelCardPushCancelParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelCardPushCancelParameter.PolyvChannelCardPushCancelParameterBuilder.aPolyvChannelCardPushCancelParameter(), function);
    }
}
