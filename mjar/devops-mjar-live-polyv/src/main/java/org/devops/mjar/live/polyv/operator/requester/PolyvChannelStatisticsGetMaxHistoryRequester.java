package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStatisticsGetMaxHistoryParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;


/**
 * @author bigboss
 * @date 2021/7/21 14:42
 * @description：查询频道历史最高并发数据
 */
public class PolyvChannelStatisticsGetMaxHistoryRequester extends FunctionOperator<PolyvChannelStatisticsGetMaxHistoryParameter,
        PolyvChannelStatisticsGetMaxHistoryParameter.PolyvChannelStatisticsGetMaxHistoryParameterBuilder,String> {
    public PolyvChannelStatisticsGetMaxHistoryRequester(Function<PolyvChannelStatisticsGetMaxHistoryParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelStatisticsGetMaxHistoryParameter.PolyvChannelStatisticsGetMaxHistoryParameterBuilder.aPolyvChannelStatisticsGetMaxHistoryParameter(),function);
    }
}