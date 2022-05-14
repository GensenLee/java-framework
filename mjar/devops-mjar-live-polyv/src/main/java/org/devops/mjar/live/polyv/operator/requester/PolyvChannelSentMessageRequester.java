package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSentMessageParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：发送图文信息
 */
public class PolyvChannelSentMessageRequester extends FunctionOperator<PolyvChannelSentMessageParameter,
        PolyvChannelSentMessageParameter.PolyvChannelSentMessageParameterBuilder,String> {
    public PolyvChannelSentMessageRequester(Function<PolyvChannelSentMessageParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelSentMessageParameter.PolyvChannelSentMessageParameterBuilder.aPolyvChannelSentMessageParameter(), function);
    }
}
