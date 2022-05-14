package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackSingleSortParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：回放列表视频单个排序
 */
public class PolyvChannelPlayBackSingleSortRequester extends FunctionOperator<PolyvChannelPlayBackSingleSortParameter,
        PolyvChannelPlayBackSingleSortParameter.PolyvChannelPlayBackSingleSortParameterBuilder, String> {
    public PolyvChannelPlayBackSingleSortRequester(Function<PolyvChannelPlayBackSingleSortParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackSingleSortParameter.PolyvChannelPlayBackSingleSortParameterBuilder.aPolyvChannelPlayBackSingleSortParameter(), function);
    }
}
