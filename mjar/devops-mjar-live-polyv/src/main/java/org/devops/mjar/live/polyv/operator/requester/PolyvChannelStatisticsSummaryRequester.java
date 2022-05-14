package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStatisticsSummary;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStatisticsSummaryParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;


import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/21 15:50
 * @description：查询频道某段时间的直播观看详情数据
 */
public class PolyvChannelStatisticsSummaryRequester extends NativeFunctionOperator<PolyvChannelStatisticsSummaryParameter,
        PolyvChannelStatisticsSummaryParameter.PolyvChannelStatisticsSummaryParamterBuilder, PolyvChannelStatisticsSummary> {
    public PolyvChannelStatisticsSummaryRequester( BiFunction<PolyvChannelStatisticsSummaryParameter, String, PolyvChannelStatisticsSummary > biFunction) {
        super(PolyvChannelStatisticsSummaryParameter.PolyvChannelStatisticsSummaryParamterBuilder.aPolyvChannelStatisticsSummaryParamter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
