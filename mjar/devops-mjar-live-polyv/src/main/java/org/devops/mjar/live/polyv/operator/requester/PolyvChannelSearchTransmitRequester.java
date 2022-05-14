package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelTransmit;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchTransmitParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

public class PolyvChannelSearchTransmitRequester extends FunctionOperator<PolyvChannelSearchTransmitParameter,
        PolyvChannelSearchTransmitParameter.PolyvChannelSearchTransmitParameterBuilder, List<PolyvChannelTransmit>> {
    public PolyvChannelSearchTransmitRequester(Function<PolyvChannelSearchTransmitParameter, PolyvApiResult<List<PolyvChannelTransmit>>> function) {
        super(PolyvChannelSearchTransmitParameter.PolyvChannelSearchTransmitParameterBuilder.aPolyvChannelSearchTransmitParameter(), function);
    }
}
