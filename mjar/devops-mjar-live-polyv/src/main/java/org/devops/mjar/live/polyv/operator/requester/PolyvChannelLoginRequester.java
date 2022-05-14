package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateTokenParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：
 */
public class PolyvChannelLoginRequester extends FunctionOperator<PolyvChannelUpdateTokenParameter,
        PolyvChannelUpdateTokenParameter.PolyvChannelUpdateTokenParameterBuilder, PolyvUrl> {
    public PolyvChannelLoginRequester(Function<PolyvChannelUpdateTokenParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelUpdateTokenParameter.PolyvChannelUpdateTokenParameterBuilder.aPolyvChannelUpdateTokenParameter(), function);
    }
}
