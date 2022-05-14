package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatDeleteParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：删除频道单条聊天记录
 */
public class PolyvChannelChatDeleteRequester extends FunctionOperator<PolyvChannelChatDeleteParameter,
        PolyvChannelChatDeleteParameter.PolyvChannelChatDeleteParameterBuilder,String> {
    public PolyvChannelChatDeleteRequester(BiFunction<PolyvChannelChatDeleteParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelChatDeleteParameter.PolyvChannelChatDeleteParameterBuilder.aPolyvChannelChatDeleteParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
