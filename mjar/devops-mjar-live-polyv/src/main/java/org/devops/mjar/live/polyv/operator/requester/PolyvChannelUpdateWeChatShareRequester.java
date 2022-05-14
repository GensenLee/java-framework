package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateWeChatShareParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/07/23 11:55
 * @description:修改频道微信分享信息
 */
public class PolyvChannelUpdateWeChatShareRequester extends FunctionOperator<PolyvChannelUpdateWeChatShareParameter,
        PolyvChannelUpdateWeChatShareParameter.PolyvChannelUpdateWeChatShareParameterBuilder,String> {
    public PolyvChannelUpdateWeChatShareRequester(Function<PolyvChannelUpdateWeChatShareParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateWeChatShareParameter.PolyvChannelUpdateWeChatShareParameterBuilder.aPolyvChannelUpdateWeChatShareParameter(), function);
    }
}
