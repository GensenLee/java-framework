package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCardPushInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCardPushCreateParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：创建频道卡片推送
 */
public class PolyvChannelCardPushCreateRequester extends FunctionOperator<PolyvChannelCardPushCreateParameter,
        PolyvChannelCardPushCreateParameter.PolyvChannelCardPushCreateParameterBuilder, PolyvCardPushInfo> {
    public PolyvChannelCardPushCreateRequester(Function<PolyvChannelCardPushCreateParameter, PolyvApiResult<PolyvCardPushInfo>> function) {
        super(PolyvChannelCardPushCreateParameter.PolyvChannelCardPushCreateParameterBuilder.aPolyvChannelCardPushCreateParameter(), function);
    }
}
