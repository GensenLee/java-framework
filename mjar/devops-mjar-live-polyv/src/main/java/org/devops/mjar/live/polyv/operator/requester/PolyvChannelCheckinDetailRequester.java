package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCheckinStats;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCheckinDetailParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：获取签到明细请求
 */
public class PolyvChannelCheckinDetailRequester extends FunctionOperator<PolyvChannelCheckinDetailParameter,
        PolyvChannelCheckinDetailParameter.PolyvChannelCheckinDetailParameterBuilder, List<PolyvCheckinStats>> {
    public PolyvChannelCheckinDetailRequester(Function<PolyvChannelCheckinDetailParameter, PolyvApiResult<List<PolyvCheckinStats>>> function) {
        super(PolyvChannelCheckinDetailParameter.PolyvChannelCheckinDetailParameterBuilder.aPolyvChannelCheckinDetailParameter(), function);
    }
}
