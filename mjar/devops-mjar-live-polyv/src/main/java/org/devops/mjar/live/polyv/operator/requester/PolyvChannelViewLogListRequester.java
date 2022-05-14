package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelViewLog;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelViewLogListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/3 18:42
 * @description：分页获取频道观看日志
 */
public class PolyvChannelViewLogListRequester extends FunctionOperator<PolyvChannelViewLogListParameter,
        PolyvChannelViewLogListParameter.PolyvChannelViewLogListParameterBuilder, PolyvPaginator<PolyvChannelViewLog>> {
    public PolyvChannelViewLogListRequester(Function<PolyvChannelViewLogListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelViewLog>>> function) {
        super(PolyvChannelViewLogListParameter.PolyvChannelViewLogListParameterBuilder.aPolyvChannelViewLogListParameter(), function);
    }
}
