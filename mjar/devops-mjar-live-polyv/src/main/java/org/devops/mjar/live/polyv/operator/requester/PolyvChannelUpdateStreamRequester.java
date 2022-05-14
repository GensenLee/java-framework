package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateStreamParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：修改频道推流方式
 */
public class PolyvChannelUpdateStreamRequester extends FunctionOperator<PolyvChannelUpdateStreamParameter,
        PolyvChannelUpdateStreamParameter.PolyvChannelUpdateStreamParameterBuilder,String> {
    public PolyvChannelUpdateStreamRequester(Function<PolyvChannelUpdateStreamParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateStreamParameter.PolyvChannelUpdateStreamParameterBuilder.aPolyvChannelUpdateStreamParameter(), function);
    }
}
