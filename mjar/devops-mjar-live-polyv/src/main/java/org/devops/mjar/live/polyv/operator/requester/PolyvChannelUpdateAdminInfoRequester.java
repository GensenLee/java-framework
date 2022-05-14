package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAdminInfoParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改管理员身份信息
 */
public class PolyvChannelUpdateAdminInfoRequester extends FunctionOperator<PolyvChannelUpdateAdminInfoParameter,
        PolyvChannelUpdateAdminInfoParameter.PolyvChannelUpdateAdminInfoParameterBuilder,String> {
    public PolyvChannelUpdateAdminInfoRequester(BiFunction<PolyvChannelUpdateAdminInfoParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateAdminInfoParameter.PolyvChannelUpdateAdminInfoParameterBuilder.aPolyvChannelUpdateAdminInfoParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
