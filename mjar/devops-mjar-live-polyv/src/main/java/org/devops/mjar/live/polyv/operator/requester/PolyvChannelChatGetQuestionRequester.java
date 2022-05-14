package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelChatGetQuestion;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatGetQuestionParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/21 14:00
 * @description：查询频道提问记录
 */
public class PolyvChannelChatGetQuestionRequester extends FunctionOperator<PolyvChannelChatGetQuestionParameter,
        PolyvChannelChatGetQuestionParameter.PolyvChannelChatGetQuestionBuilder, List<PolyvChannelChatGetQuestion>> {
    public PolyvChannelChatGetQuestionRequester(BiFunction<PolyvChannelChatGetQuestionParameter, String, PolyvApiResult<List<PolyvChannelChatGetQuestion>>> biFunction) {
        super(PolyvChannelChatGetQuestionParameter.PolyvChannelChatGetQuestionBuilder.aPolyvChannelChatGetQuestion(),biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
