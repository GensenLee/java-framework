package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChatList;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatListParameter;

import java.util.function.Function;

/**
 * 获取聊天室在线列表
 */
public class PolyvChannelChatOnLineListRequester extends NativeFunctionOperator<PolyvChannelChatListParameter,
        PolyvChannelChatListParameter.PolyvChannelChatListParameterBuilder, PolyvChatList > {
    public PolyvChannelChatOnLineListRequester( Function<PolyvChannelChatListParameter, PolyvChatList> function) {
        super(PolyvChannelChatListParameter.PolyvChannelChatListParameterBuilder.aPolyvChannelChatListParameter(), function);
    }
}
