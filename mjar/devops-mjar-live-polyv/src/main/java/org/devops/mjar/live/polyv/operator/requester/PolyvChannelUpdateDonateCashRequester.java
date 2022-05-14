package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateDonateCashParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：设置现金打赏
 */
public class PolyvChannelUpdateDonateCashRequester extends FunctionOperator<PolyvChannelUpdateDonateCashParameter,
        PolyvChannelUpdateDonateCashParameter.PolyvChannelUpdateDonateCashParameterBuilder,Object> {
    public PolyvChannelUpdateDonateCashRequester(Function<PolyvChannelUpdateDonateCashParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdateDonateCashParameter.PolyvChannelUpdateDonateCashParameterBuilder.aPolyvChannelUpdateDonateCashParameter(), function);
    }
}
