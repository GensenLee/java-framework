package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelViewLogV3;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStatisticsViewLogParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/21 14:00
 * @description：查询频道直播观看详情数据
 */
public class PolyvChannelStatisticsViewLogRequester extends NativeFunctionOperator<PolyvChannelStatisticsViewLogParameter,
        PolyvChannelStatisticsViewLogParameter.PolyvChannelStatisticsViewLogParamterBuilder , PolyvChannelViewLogV3> {


    public PolyvChannelStatisticsViewLogRequester(BiFunction<PolyvChannelStatisticsViewLogParameter, String, PolyvChannelViewLogV3> biFunction) {
        super(PolyvChannelStatisticsViewLogParameter.PolyvChannelStatisticsViewLogParamterBuilder.aPolyvChannelStatisticsViewLogParamter(), biFunction,
                MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
