package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelChatHistoryV3;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatHistoryParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：获取聊天记录请求
 */
public class PolyvChannelChatHistoryRequester extends FunctionOperator<PolyvChannelChatHistoryParameter,
        PolyvChannelChatHistoryParameter.PolyvChannelChatHistoryParameterBuilder, List<PolyvChannelChatHistoryV3>> {
    public PolyvChannelChatHistoryRequester(Function<PolyvChannelChatHistoryParameter, PolyvApiResult<List<PolyvChannelChatHistoryV3>>> function) {
        super(PolyvChannelChatHistoryParameter.PolyvChannelChatHistoryParameterBuilder.aPolyvChannelChatHistoryParameter(), function);
    }
}
