package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/10/30 11:13
 * @description：删除频道
 */
public class PolyvChannelDeleteRequester extends FunctionOperator<PolyvChannelDeleteParameter,
        PolyvChannelDeleteParameter.PolyvChannelDeleteReqParameterBuilder, Boolean> {

    public PolyvChannelDeleteRequester(BiFunction<PolyvChannelDeleteParameter, String, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelDeleteParameter.PolyvChannelDeleteReqParameterBuilder.aPolyvChannelDeleteParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
