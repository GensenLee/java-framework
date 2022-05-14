package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvPlayBackInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackGetParameter;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量查询频道单个回放信息
 */
public class PolyvChannelPlayBackGetRequester extends FunctionOperator<PolyvChannelPlayBackGetParameter,
        PolyvChannelPlayBackGetParameter.PolyvChannelPlayBackGetParameterBuilder, List<PolyvPlayBackInfo>> {
    public PolyvChannelPlayBackGetRequester(Function<PolyvChannelPlayBackGetParameter, PolyvApiResult<List<PolyvPlayBackInfo>>> function) {
        super(PolyvChannelPlayBackGetParameter.PolyvChannelPlayBackGetParameterBuilder.aPolyvChannelPlayBackGetParameter(), function);
    }
}
