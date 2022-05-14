package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannel;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelSearchRequester extends FunctionOperator<PolyvChannelSearchParameter,
        PolyvChannelSearchParameter.PolyvChannelSearchParameterBuilder, PolyvChannel> {
    public PolyvChannelSearchRequester( Function<PolyvChannelSearchParameter, PolyvApiResult<PolyvChannel>> function) {
        super(PolyvChannelSearchParameter.PolyvChannelSearchParameterBuilder.aPolyvChannelSearchParameter(), function);
    }
}
