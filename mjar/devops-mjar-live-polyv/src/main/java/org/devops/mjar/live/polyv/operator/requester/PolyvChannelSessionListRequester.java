package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelSession;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSessionListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/23 15:19
 * @description：直播场次列表
 */
public class PolyvChannelSessionListRequester extends FunctionOperator<PolyvChannelSessionListParameter,
        PolyvChannelSessionListParameter.PolyvChannelSessionListParameterBuilder, PolyvPaginator<PolyvChannelSession>> {
    public PolyvChannelSessionListRequester(Function<PolyvChannelSessionListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelSession>>> function) {
        super(PolyvChannelSessionListParameter.PolyvChannelSessionListParameterBuilder.aPolyvChannelSessionListParameter(), function);
    }
}
