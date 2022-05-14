package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateViewConditionParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 11:03
 * @description：修改频道观看条件
 */
public class PolyvChannelUpdateViewConditionRequester extends FunctionOperator<PolyvChannelUpdateViewConditionParameter,
        PolyvChannelUpdateViewConditionParameter.PolyvChannelUpdateViewConditionParameterBuilder,String> {
    public PolyvChannelUpdateViewConditionRequester(Function<PolyvChannelUpdateViewConditionParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateViewConditionParameter.PolyvChannelUpdateViewConditionParameterBuilder.aPolyvChannelUpdateViewConditionParameter(), function);
    }
}
