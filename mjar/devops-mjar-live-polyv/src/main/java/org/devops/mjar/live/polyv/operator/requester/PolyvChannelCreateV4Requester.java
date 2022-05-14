package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelV4;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateV4Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：创建频道V4
 */
public class PolyvChannelCreateV4Requester extends FunctionOperator<PolyvChannelCreateV4Parameter,
        PolyvChannelCreateV4Parameter.PolyvChannelCreateV4ParameterBuilder, PolyvChannelV4> {
    public PolyvChannelCreateV4Requester(Function<PolyvChannelCreateV4Parameter, PolyvApiResult<PolyvChannelV4>> function) {
        super(PolyvChannelCreateV4Parameter.PolyvChannelCreateV4ParameterBuilder.aPolyvChannelCreateV4Parameter(), function);
    }
}
