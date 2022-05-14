package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelMenuList;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchImageMenuListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelSearchImageMenuListRequester extends FunctionOperator<PolyvChannelSearchImageMenuListParameter,
        PolyvChannelSearchImageMenuListParameter.PolyvChannelSearchImageMenuListParameterBuilder, PolyvChannelMenuList> {
    public PolyvChannelSearchImageMenuListRequester(Function<PolyvChannelSearchImageMenuListParameter, PolyvApiResult<PolyvChannelMenuList>> function) {
        super(PolyvChannelSearchImageMenuListParameter.PolyvChannelSearchImageMenuListParameterBuilder.aPolyvChannelSearchImageMenuListParameter(), function);
    }
}
