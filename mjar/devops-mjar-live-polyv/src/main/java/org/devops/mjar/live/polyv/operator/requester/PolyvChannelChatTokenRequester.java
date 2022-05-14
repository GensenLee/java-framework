package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelChatToken;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatTokenParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/2 17:54
 * @description：查询授权和连麦的token
 */
public class PolyvChannelChatTokenRequester extends FunctionOperator<PolyvChannelChatTokenParameter,
        PolyvChannelChatTokenParameter.PolyvChannelChatTokenParameterBuilder, PolyvChannelChatToken> {
    public PolyvChannelChatTokenRequester(Function<PolyvChannelChatTokenParameter, PolyvApiResult<PolyvChannelChatToken>> function) {
        super(PolyvChannelChatTokenParameter.PolyvChannelChatTokenParameterBuilder.aPolyvChannelChatTokenParameter(), function);
    }
}
