package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppStatisticsChannelSummaryParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/21 10:41
 * @description：查询多个频道概览统计数据
 */
public class PolyvAppStatisticsChannelSummaryRequester extends FunctionOperator<PolyvAppStatisticsChannelSummaryParameter,
        PolyvAppStatisticsChannelSummaryParameter.PolyvChannelStatisticsChannelSummaryBuilder, Object> {
    public PolyvAppStatisticsChannelSummaryRequester(BiFunction<PolyvAppStatisticsChannelSummaryParameter, String, PolyvApiResult<Object>> biFunction) {
        super(PolyvAppStatisticsChannelSummaryParameter.PolyvChannelStatisticsChannelSummaryBuilder.aPolyvChannelStatisticsChannelSummary(), biFunction, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
