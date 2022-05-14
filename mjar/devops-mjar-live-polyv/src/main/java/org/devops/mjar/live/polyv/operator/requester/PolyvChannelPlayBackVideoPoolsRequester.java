package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPlayBackVideoPoolItem;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackVideoPoolsParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：获取可添加到回放列表的视频列表
 */
public class PolyvChannelPlayBackVideoPoolsRequester extends FunctionOperator<PolyvChannelPlayBackVideoPoolsParameter,
        PolyvChannelPlayBackVideoPoolsParameter.PolyvChannelPlayBackVideoPoolsParameterBuilder, PolyvPaginator<PolyvChannelPlayBackVideoPoolItem>> {
    public PolyvChannelPlayBackVideoPoolsRequester(Function<PolyvChannelPlayBackVideoPoolsParameter, PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackVideoPoolItem>>> function) {
        super(PolyvChannelPlayBackVideoPoolsParameter.PolyvChannelPlayBackVideoPoolsParameterBuilder.aPolyvChannelPlayBackVideoPoolsParameter(), function);
    }
}
