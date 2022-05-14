package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdatePasswdParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道密码
 */
public class PolyvChannelUpdatePasswdRequester extends FunctionOperator<PolyvChannelUpdatePasswdParameter,
        PolyvChannelUpdatePasswdParameter.PolyvChannelUpdatePasswdParameterBuilder,Boolean> {
    public PolyvChannelUpdatePasswdRequester(BiFunction<PolyvChannelUpdatePasswdParameter, String, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelUpdatePasswdParameter.PolyvChannelUpdatePasswdParameterBuilder.aPolyvChannelUpdatePasswdParameter(), function, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
