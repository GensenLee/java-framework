package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateStopAdvertParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/28 11:40
 * @description：修改频道播放器暂停广告
 */

public class PolyvChannelUpdateStopAdvertRequester extends FunctionOperator<PolyvChannelUpdateStopAdvertParameter,
        PolyvChannelUpdateStopAdvertParameter.PolyvChannelUpdateStopAdvertParameterBuilder,String > {
    public PolyvChannelUpdateStopAdvertRequester(BiFunction<PolyvChannelUpdateStopAdvertParameter, String, PolyvApiResult<String>> biFunction) {
        super(PolyvChannelUpdateStopAdvertParameter.PolyvChannelUpdateStopAdvertParameterBuilder.aPolyvChannelUpdateStopAdvertParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
