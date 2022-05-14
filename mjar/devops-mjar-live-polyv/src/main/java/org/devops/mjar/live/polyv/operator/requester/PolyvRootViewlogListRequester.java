package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvViewLog;
import org.devops.mjar.live.polyv.pojo.param.PolyvRootViewlogListParameter;

import java.util.List;
import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/16 17:15
 * @description：应用日志请求
 */
public class PolyvRootViewlogListRequester extends FunctionOperator<PolyvRootViewlogListParameter,
        PolyvRootViewlogListParameter.PolyvRootViewlogParameterBuilder, List<PolyvViewLog>> {
    public PolyvRootViewlogListRequester(Function<PolyvRootViewlogListParameter, PolyvApiResult<List<PolyvViewLog>>> function) {
        super(PolyvRootViewlogListParameter.PolyvRootViewlogParameterBuilder.aPolyvRootViewlogListParameter(), function);
    }
}
