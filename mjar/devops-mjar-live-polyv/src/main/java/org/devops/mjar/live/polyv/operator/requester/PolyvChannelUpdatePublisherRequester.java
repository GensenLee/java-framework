package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdatePublisherParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道主持人姓名
 */
public class PolyvChannelUpdatePublisherRequester extends FunctionOperator<PolyvChannelUpdatePublisherParameter,
        PolyvChannelUpdatePublisherParameter.PolyvChannelUpdatePublisherParameterBuilder,String> {
    public PolyvChannelUpdatePublisherRequester(BiFunction<PolyvChannelUpdatePublisherParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdatePublisherParameter.PolyvChannelUpdatePublisherParameterBuilder.aPolyvChannelUpdatePublisherParameter(), function, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
