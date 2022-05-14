package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAddBannedIpParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：禁言ip
 */
public class PolyvChannelAddBannedIpRequester extends FunctionOperator<PolyvChannelAddBannedIpParameter,
        PolyvChannelAddBannedIpParameter.PolyvChannelAddBannedIpParameterBuilder, List<String>> {
    public PolyvChannelAddBannedIpRequester(BiFunction<PolyvChannelAddBannedIpParameter, String, PolyvApiResult<List<String>>> function) {
        super(PolyvChannelAddBannedIpParameter.PolyvChannelAddBannedIpParameterBuilder.aPolyvChannelAddBannedIpParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
