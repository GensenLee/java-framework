package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackUpdateTitleParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：修改回放视频名称
 */
public class PolyvChannelPlayBackUpdateTitleRequester extends FunctionOperator<PolyvChannelPlayBackUpdateTitleParameter,
        PolyvChannelPlayBackUpdateTitleParameter.PolyvChannelPlayBackUpdateTitleParameterBuilder,String> {
    public PolyvChannelPlayBackUpdateTitleRequester(Function<PolyvChannelPlayBackUpdateTitleParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackUpdateTitleParameter.PolyvChannelPlayBackUpdateTitleParameterBuilder.aPolyvChannelPlayBackUpdateTitleParameter(), function);
    }
}
