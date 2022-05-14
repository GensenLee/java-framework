package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchSingleAccountParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;


/**
 * @author bigboss
 * @date 2021/7/23 10:20
 * @description：查询单个子频道信息
 */
public class PolyvChannelSearchSingleAccountRequester extends FunctionOperator<PolyvChannelSearchSingleAccountParameter,
        PolyvChannelSearchSingleAccountParameter.PolyvChannelSearchSingleAccountParameterBuilder, PolyvChannelAccount> {
    public PolyvChannelSearchSingleAccountRequester(BiFunction<PolyvChannelSearchSingleAccountParameter, String, PolyvApiResult<PolyvChannelAccount>> biFunction) {
        super(PolyvChannelSearchSingleAccountParameter.PolyvChannelSearchSingleAccountParameterBuilder.aPolyvAppSearchSingleAccountParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
