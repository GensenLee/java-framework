package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCardPushDeleteParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：删除频道卡片推送
 */
public class PolyvChannelCardPushDeleteRequester extends FunctionOperator<PolyvChannelCardPushDeleteParameter,
        PolyvChannelCardPushDeleteParameter.PolyvChannelCardPushDeleteParameterBuilder, Object> {
    public PolyvChannelCardPushDeleteRequester(Function<PolyvChannelCardPushDeleteParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelCardPushDeleteParameter.PolyvChannelCardPushDeleteParameterBuilder.aPolyvChannelCardPushDeleteParameter(), function);
    }
}
