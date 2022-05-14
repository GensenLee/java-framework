package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateDonateGoodParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：设置道具打赏
 */
public class PolyvChannelUpdateDonateGoodRequester extends FunctionOperator<PolyvChannelUpdateDonateGoodParameter,
        PolyvChannelUpdateDonateGoodParameter.PolyvChannelUpdateDonateGoodParameterBuilder,Object> {
    public PolyvChannelUpdateDonateGoodRequester(Function<PolyvChannelUpdateDonateGoodParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdateDonateGoodParameter.PolyvChannelUpdateDonateGoodParameterBuilder.aPolyvChannelUpdateDonateGoodParameter(), function);
    }
}
