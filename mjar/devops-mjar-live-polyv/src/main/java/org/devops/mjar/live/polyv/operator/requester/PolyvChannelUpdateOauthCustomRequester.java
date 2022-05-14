package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthExternal;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateOauthCustomParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/27 14:11
 * @description：修改频道外部授权设置
 */
public class PolyvChannelUpdateOauthCustomRequester extends FunctionOperator<PolyvChannelUpdateOauthCustomParameter,
        PolyvChannelUpdateOauthCustomParameter.PolyvChannelUpdateOauthCustomParameterBuilder, List<PolyvChannelAuthExternal>> {
    public PolyvChannelUpdateOauthCustomRequester(BiFunction<PolyvChannelUpdateOauthCustomParameter, String, PolyvApiResult<List<PolyvChannelAuthExternal>>> biFunction) {
        super(PolyvChannelUpdateOauthCustomParameter.PolyvChannelUpdateOauthCustomParameterBuilder.aPolyvChannelUpdateOauthCustomParameter(), biFunction, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
