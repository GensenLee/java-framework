package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelMenuDetail;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateMenuParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelCreateMenuRequester extends FunctionOperator<PolyvChannelCreateMenuParameter,
        PolyvChannelCreateMenuParameter.PolyvChannelCreateMenuParameterBuilder, PolyvChannelMenuDetail> {
    public PolyvChannelCreateMenuRequester(Function<PolyvChannelCreateMenuParameter, PolyvApiResult<PolyvChannelMenuDetail>> function) {
        super(PolyvChannelCreateMenuParameter.PolyvChannelCreateMenuParameterBuilder.aPolyvChannelCreateMenuParameter(), function);
    }
}
