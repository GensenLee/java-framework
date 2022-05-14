package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvWeChatShare;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchWeChatShareParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/28 11:58
 * @description：查询频道微信分享信息
 */

public class PolyvChannelSearchWeChatShareRequester extends FunctionOperator<PolyvChannelSearchWeChatShareParameter,
        PolyvChannelSearchWeChatShareParameter.PolyvChannelSearchWeChatShareParameterBuilder, PolyvWeChatShare> {


    public PolyvChannelSearchWeChatShareRequester(Function<PolyvChannelSearchWeChatShareParameter, PolyvApiResult<PolyvWeChatShare>> function) {
        super(PolyvChannelSearchWeChatShareParameter.PolyvChannelSearchWeChatShareParameterBuilder.aPolyvChannelSearchWeChatShareParameter(), function);
    }
}
