package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/11/3 15:23
 * @description：频道下所有子频道
 */
public class PolyvChannelAccountListRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, List<PolyvChannelAccount>> {

    public PolyvChannelAccountListRequester(BiFunction<PolyvChannelNoneParamParameter, String, PolyvApiResult<List<PolyvChannelAccount>>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
