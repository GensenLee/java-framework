package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCardPushInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道卡片推送
 */
public class PolyvChannelCardPushListRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, List<PolyvCardPushInfo>> {
    public PolyvChannelCardPushListRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<List<PolyvCardPushInfo>>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
