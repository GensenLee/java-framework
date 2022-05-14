package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelToken;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateTokenParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelCreateTokenRequester extends FunctionOperator<PolyvChannelCreateTokenParameter,
        PolyvChannelCreateTokenParameter.PolyvChannelCreateTokenParameterBuilder, PolyvChannelToken> {
    public PolyvChannelCreateTokenRequester( Function<PolyvChannelCreateTokenParameter, PolyvApiResult<PolyvChannelToken>> function) {
        super(PolyvChannelCreateTokenParameter.PolyvChannelCreateTokenParameterBuilder.aPolyvChannelCreateTokenParameter(), function);
    }
}
