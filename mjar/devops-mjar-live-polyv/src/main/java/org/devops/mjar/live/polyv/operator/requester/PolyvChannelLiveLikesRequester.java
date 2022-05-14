package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvLiveLikes;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLiveLikesParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道点赞数和观看次数
 */
public class PolyvChannelLiveLikesRequester extends FunctionOperator<PolyvChannelLiveLikesParameter,
        PolyvChannelLiveLikesParameter.PolyvChannelLiveLikesParameterBuilder, List<PolyvLiveLikes>> {
    public PolyvChannelLiveLikesRequester(Function<PolyvChannelLiveLikesParameter, PolyvApiResult<List<PolyvLiveLikes>>> function) {
        super(PolyvChannelLiveLikesParameter.PolyvChannelLiveLikesParameterBuilder.aPolyvChannelLiveLikesParameter(), function);
    }
}
