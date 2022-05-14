package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBadWordDeleteParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：删除频道严禁词/禁言ip
 */
public class PolyvChannelBadWordDeleteRequester extends FunctionOperator<PolyvChannelBadWordDeleteParameter,
        PolyvChannelBadWordDeleteParameter.PolyvChannelBadWordDeleteParameterBuilder,String> {
    public PolyvChannelBadWordDeleteRequester(BiFunction<PolyvChannelBadWordDeleteParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelBadWordDeleteParameter.PolyvChannelBadWordDeleteParameterBuilder.aPolyvChannelBadWordDeleteParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
