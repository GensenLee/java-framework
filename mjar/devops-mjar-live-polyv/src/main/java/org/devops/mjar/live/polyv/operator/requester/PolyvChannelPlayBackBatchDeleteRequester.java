package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackBatchDeleteParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量删除回放列表的回放视频
 */
public class PolyvChannelPlayBackBatchDeleteRequester extends FunctionOperator<PolyvChannelPlayBackBatchDeleteParameter,
        PolyvChannelPlayBackBatchDeleteParameter.PolyvChannelPlayBackBatchDeleteParameterBuilder,String> {
    public PolyvChannelPlayBackBatchDeleteRequester(Function<PolyvChannelPlayBackBatchDeleteParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackBatchDeleteParameter.PolyvChannelPlayBackBatchDeleteParameterBuilder.aPolyvChannelPlayBackBatchDeleteParameter(), function);
    }
}
