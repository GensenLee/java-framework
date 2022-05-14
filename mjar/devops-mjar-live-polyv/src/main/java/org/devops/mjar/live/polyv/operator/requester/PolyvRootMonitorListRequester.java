package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvMonitor;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvRootMonitorListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/3 11:04
 * @description：直播监控请求
 */
public class PolyvRootMonitorListRequester extends FunctionOperator<PolyvRootMonitorListParameter,
        PolyvRootMonitorListParameter.PolyvRootMonitorListParameterBuilder, PolyvPaginator<PolyvMonitor>> {
    public PolyvRootMonitorListRequester(Function<PolyvRootMonitorListParameter, PolyvApiResult<PolyvPaginator<PolyvMonitor>>> function) {
        super(PolyvRootMonitorListParameter.PolyvRootMonitorListParameterBuilder.aPolyvRootMonitorListParameter(), function);
    }
}
