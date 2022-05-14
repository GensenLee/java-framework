package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateNameParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道名称
 */
public class PolyvChannelUpdateNameRequester extends FunctionOperator<PolyvChannelUpdateNameParameter,
        PolyvChannelUpdateNameParameter.PolyvChannelUpdateNameParameterBuilder,Boolean> {
    public PolyvChannelUpdateNameRequester(BiFunction<PolyvChannelUpdateNameParameter, String, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelUpdateNameParameter.PolyvChannelUpdateNameParameterBuilder.aPolyvChannelUpdateNameParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
