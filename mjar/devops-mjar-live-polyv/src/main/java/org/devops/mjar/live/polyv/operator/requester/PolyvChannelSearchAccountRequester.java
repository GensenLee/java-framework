package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchAccountParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/23 10:20
 * @description：查询频道号下所有子频道信息
 */
public class PolyvChannelSearchAccountRequester extends FunctionOperator<PolyvChannelSearchAccountParameter,
        PolyvChannelSearchAccountParameter.PolyvChannelSearchAccountParameterBuilder, List<PolyvChannelAccount>> {
    public PolyvChannelSearchAccountRequester(BiFunction<PolyvChannelSearchAccountParameter, String, PolyvApiResult<List<PolyvChannelAccount>>> biFunction) {
        super(PolyvChannelSearchAccountParameter.PolyvChannelSearchAccountParameterBuilder.aPolyvChannelSearchAccountParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
