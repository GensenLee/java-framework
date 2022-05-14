package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvMonitorInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：获取直播监控信息接口
 */
public class PolyvChannelGetMonitorInfoRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, PolyvMonitorInfo> {
    public PolyvChannelGetMonitorInfoRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<PolyvMonitorInfo>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
