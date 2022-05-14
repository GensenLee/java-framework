package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStaticistGetSessionStats;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStaticistGetSessionStatsParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/21 10:41
 * @description：查询频道多场次概览统计数据
 */
public class PolyvChannelStaticistGetSessionStstsRequester extends FunctionOperator<PolyvChannelStaticistGetSessionStatsParameter,
        PolyvChannelStaticistGetSessionStatsParameter.PolyvChannelStaticistGetSessionStatsBuilder, PolyvChannelStaticistGetSessionStats> {
    public PolyvChannelStaticistGetSessionStstsRequester( Function<PolyvChannelStaticistGetSessionStatsParameter, PolyvApiResult<PolyvChannelStaticistGetSessionStats>> function) {
        super(PolyvChannelStaticistGetSessionStatsParameter.PolyvChannelStaticistGetSessionStatsBuilder.aPolyvChannelStaticistGetSessionStats(), function);
    }
}
