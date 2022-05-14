package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStream;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/03/2 15:23
 * @description：频道下所有子频道
 */
public class PolyvChannelStreamInfoRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, PolyvChannelStream> {

    public PolyvChannelStreamInfoRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<PolyvChannelStream>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
