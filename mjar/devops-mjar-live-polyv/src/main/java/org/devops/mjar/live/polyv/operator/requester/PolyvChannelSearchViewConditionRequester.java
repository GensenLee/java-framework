package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelViewCondition;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchViewConditionParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 14:11
 * @description：查询频道观看条件
 */
public class PolyvChannelSearchViewConditionRequester extends FunctionOperator<PolyvChannelSearchViewConditionParameter,
        PolyvChannelSearchViewConditionParameter.PolyvChannelSearchViewConditionParameterBuilder, List<PolyvChannelViewCondition> > {
    public PolyvChannelSearchViewConditionRequester(Function<PolyvChannelSearchViewConditionParameter, PolyvApiResult<List<PolyvChannelViewCondition>>> function) {
        super(PolyvChannelSearchViewConditionParameter.PolyvChannelSearchViewConditionParameterBuilder.aPolyvChannelSearchViewConditionParameter(),function);
    }
}
