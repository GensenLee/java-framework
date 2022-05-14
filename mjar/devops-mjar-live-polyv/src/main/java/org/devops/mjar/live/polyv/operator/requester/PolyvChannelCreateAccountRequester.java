package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateAccountParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/11/3 15:40
 * @description：子频道创建请求
 */
public class PolyvChannelCreateAccountRequester extends FunctionOperator<PolyvChannelCreateAccountParameter,
        PolyvChannelCreateAccountParameter.PolyvChannelCreateAccountParameterBuilder, PolyvChannelAccount> {

    public PolyvChannelCreateAccountRequester(BiFunction<PolyvChannelCreateAccountParameter, String, PolyvApiResult<PolyvChannelAccount>> function) {
        super(PolyvChannelCreateAccountParameter.PolyvChannelCreateAccountParameterBuilder.aPolyvChannelCreateAccountParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
