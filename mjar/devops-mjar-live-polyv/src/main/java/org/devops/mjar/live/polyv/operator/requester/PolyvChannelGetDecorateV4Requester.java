package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvDecorateConfigV4;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelGetDecorateV4Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelGetDecorateV4Requester extends FunctionOperator<PolyvChannelGetDecorateV4Parameter,
        PolyvChannelGetDecorateV4Parameter.PolyvChannelGetDecorateV4ParameterBuilder, PolyvDecorateConfigV4> {
    public PolyvChannelGetDecorateV4Requester(Function<PolyvChannelGetDecorateV4Parameter, PolyvApiResult<PolyvDecorateConfigV4>> function) {
        super(PolyvChannelGetDecorateV4Parameter.PolyvChannelGetDecorateV4ParameterBuilder.aPolyvChannelGetDecorateV4Parameter(), function);
    }
}
