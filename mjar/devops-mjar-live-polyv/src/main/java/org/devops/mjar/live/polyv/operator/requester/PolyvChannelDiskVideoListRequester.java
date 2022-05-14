package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvDiskVideo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDiskVideoListParameter;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：分页获取伪直播信息
 */
public class PolyvChannelDiskVideoListRequester extends FunctionOperator<PolyvChannelDiskVideoListParameter,
        PolyvChannelDiskVideoListParameter.PolyvChannelDiskVideoListParameterBuilder, PolyvPaginator<PolyvDiskVideo>> {
    public PolyvChannelDiskVideoListRequester(Function<PolyvChannelDiskVideoListParameter, PolyvApiResult<PolyvPaginator<PolyvDiskVideo>>> function) {
        super(PolyvChannelDiskVideoListParameter.PolyvChannelDiskVideoListParameterBuilder.aPolyvChannelDiskVideoListParameter(), function);
    }
}
