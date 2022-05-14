package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateMaxViewerParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道观看人数限制
 */
public class PolyvChannelUpdateMaxViewerRequester extends FunctionOperator<PolyvChannelUpdateMaxViewerParameter,
        PolyvChannelUpdateMaxViewerParameter.PolyvChannelUpdateMaxViewerParameterBuilder,String> {
    public PolyvChannelUpdateMaxViewerRequester(BiFunction<PolyvChannelUpdateMaxViewerParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateMaxViewerParameter.PolyvChannelUpdateMaxViewerParameterBuilder.aPolyvChannelUpdateMaxViewerParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
