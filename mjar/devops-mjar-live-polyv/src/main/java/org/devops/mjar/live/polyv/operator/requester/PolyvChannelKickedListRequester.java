package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelKicked;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道踢人列表
 */
public class PolyvChannelKickedListRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, List<PolyvChannelKicked>> {
    public PolyvChannelKickedListRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<List<PolyvChannelKicked>>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
