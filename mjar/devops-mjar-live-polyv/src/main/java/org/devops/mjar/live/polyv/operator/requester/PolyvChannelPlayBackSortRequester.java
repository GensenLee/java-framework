package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackSortParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：修改视频库的视频排序
 */
public class PolyvChannelPlayBackSortRequester extends FunctionOperator<PolyvChannelPlayBackSortParameter,
        PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBuilder,String> {
    public PolyvChannelPlayBackSortRequester(Function<PolyvChannelPlayBackSortParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackSortParameter.PolyvChannelPlayBackSortParameterBuilder.aPolyvChannelPlayBackSortParameter(), function);
    }
}
