package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvAdverties;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchAdvertListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/28 10:20
 * @description：查询频道广告列表
 */

public class PolyvChannelSearchAdvertListRequester extends FunctionOperator<PolyvChannelSearchAdvertListParameter,
        PolyvChannelSearchAdvertListParameter.PolyvChannelSearchAdvertListParameterBuilder, List<PolyvAdverties>> {
    public PolyvChannelSearchAdvertListRequester(Function<PolyvChannelSearchAdvertListParameter, PolyvApiResult<List<PolyvAdverties>>> function) {
        super(PolyvChannelSearchAdvertListParameter.PolyvChannelSearchAdvertListParameterBuilder.aPolyvChannelSearchAdvertListParameter(), function);
    }
}
