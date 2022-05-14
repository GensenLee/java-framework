package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAccountTokenParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2021/3/5 14:11
 * @description：设置子频道单点登陆token
 */
public class PolyvChannelUpdateAccountTokenRequester extends FunctionOperator<PolyvChannelUpdateAccountTokenParameter,
        PolyvChannelUpdateAccountTokenParameter.PolyvChannelUpdateAccountTokenParameterBuilder, Object> {
    public PolyvChannelUpdateAccountTokenRequester(BiFunction<PolyvChannelUpdateAccountTokenParameter, String, PolyvApiResult<Object>> biFunction) {
        super(PolyvChannelUpdateAccountTokenParameter.PolyvChannelUpdateAccountTokenParameterBuilder.aPolyvChannelAccountTokenSetParameter(), biFunction, MjarLiveConstant.Key.ACCOUNT_ID_KEY);
    }
}
