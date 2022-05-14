package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStatisticsConcurrence;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStatisticsConcurrenceParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/21 10:41
 * @description：查询频道历史并发数据
 */
public class PolyvChannelStatisticsConcurrenceRequester extends FunctionOperator<PolyvChannelStatisticsConcurrenceParameter,
        PolyvChannelStatisticsConcurrenceParameter.PolyvChannelStatisticsConcurrenceParameterBuilder, List<PolyvChannelStatisticsConcurrence>>
{
    public PolyvChannelStatisticsConcurrenceRequester(Function<PolyvChannelStatisticsConcurrenceParameter, PolyvApiResult<List<PolyvChannelStatisticsConcurrence>>> function) {
        super(PolyvChannelStatisticsConcurrenceParameter.PolyvChannelStatisticsConcurrenceParameterBuilder.aPolyvChannelStatisticsConcurrenceParameter(), function);
    }
}
