package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatBatchDeleteParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量删除频道多条聊天记录
 */
public class PolyvChannelChatBatchDeleteRequester extends FunctionOperator<PolyvChannelChatBatchDeleteParameter,
        PolyvChannelChatBatchDeleteParameter.PolyvChannelChatBatchDeleteParameterBuilder,String> {
    public PolyvChannelChatBatchDeleteRequester(Function<PolyvChannelChatBatchDeleteParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelChatBatchDeleteParameter.PolyvChannelChatBatchDeleteParameterBuilder.aPolyvChannelChatBatchDeleteParameter(), function);
    }
}
