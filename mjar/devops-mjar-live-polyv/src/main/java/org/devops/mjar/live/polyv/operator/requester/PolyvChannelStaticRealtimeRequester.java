package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStatisticsRealtime;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStatisticsRealtimeParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/21 10:41
 * @description：查询频道实时并发数据
 */
public class PolyvChannelStaticRealtimeRequester extends NativeFunctionOperator<PolyvChannelStatisticsRealtimeParameter,
        PolyvChannelStatisticsRealtimeParameter.PolyvChannelStatisticsRealtimeParameterBuilder, PolyvChannelStatisticsRealtime> {


    public PolyvChannelStaticRealtimeRequester(BiFunction<PolyvChannelStatisticsRealtimeParameter, String, PolyvChannelStatisticsRealtime> biFunction ) {
        super(PolyvChannelStatisticsRealtimeParameter.PolyvChannelStatisticsRealtimeParameterBuilder.aPolyvChannelStatisticsRealtimeParameter(),
                biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
