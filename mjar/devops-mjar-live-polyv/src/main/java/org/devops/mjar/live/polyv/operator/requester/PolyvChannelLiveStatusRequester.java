package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvLiveStatus;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLiveStatusParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询批量频道状态
 */
public class PolyvChannelLiveStatusRequester extends FunctionOperator<PolyvChannelLiveStatusParameter,
        PolyvChannelLiveStatusParameter.PolyvChannelLiveStatusParameterBuilder, List<PolyvLiveStatus>> {
    public PolyvChannelLiveStatusRequester(Function<PolyvChannelLiveStatusParameter, PolyvApiResult<List<PolyvLiveStatus>>> function) {
        super(PolyvChannelLiveStatusParameter.PolyvChannelLiveStatusParameterBuilder.aPolyvChannelLiveStatusParameter(), function);
    }
}
