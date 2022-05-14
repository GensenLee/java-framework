package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCheckinStats;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCheckinListPageParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：查询频道签到结果
 */
public class PolyvChannelCheckinListPageRequester extends FunctionOperator<PolyvChannelCheckinListPageParameter,
        PolyvChannelCheckinListPageParameter.PolyvChannelCheckinListPageParameterBuilder, PolyvPaginator<PolyvCheckinStats>> {
    public PolyvChannelCheckinListPageRequester(Function<PolyvChannelCheckinListPageParameter, PolyvApiResult<PolyvPaginator<PolyvCheckinStats>>> function) {
        super(PolyvChannelCheckinListPageParameter.PolyvChannelCheckinListPageParameterBuilder.aPolyvChannelCheckinListPageParameter(), function);
    }
}
