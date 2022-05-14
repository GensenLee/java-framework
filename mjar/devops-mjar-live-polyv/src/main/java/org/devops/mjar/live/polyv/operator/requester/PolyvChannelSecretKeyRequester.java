package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvSecretKey;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/5 14:57
 * @description：查询独立授权secreKey
 */
public class PolyvChannelSecretKeyRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, PolyvSecretKey> {
    public PolyvChannelSecretKeyRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<PolyvSecretKey>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
