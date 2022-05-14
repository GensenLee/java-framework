package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelViewers;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelsRealtimeViewerListParameter;

import java.util.List;
import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/2 18:30
 * @description：查询多个频道实时在线人数
 */
public class PolyvChannelsRealtimeViewerListRequester extends FunctionOperator<PolyvChannelsRealtimeViewerListParameter,
        PolyvChannelsRealtimeViewerListParameter.PolyvChannelsRealtimeViewerListParameterBuilder, List<PolyvChannelViewers>> {
    public PolyvChannelsRealtimeViewerListRequester(Function<PolyvChannelsRealtimeViewerListParameter, PolyvApiResult<List<PolyvChannelViewers>>> function) {
        super(PolyvChannelsRealtimeViewerListParameter.PolyvChannelsRealtimeViewerListParameterBuilder.aPolyvChannelsRealtimeViewerListParameter(), function);
    }
}
