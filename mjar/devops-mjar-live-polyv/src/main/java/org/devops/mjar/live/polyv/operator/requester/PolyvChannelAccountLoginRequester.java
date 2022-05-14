package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAccountTokenParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：
 */
public class PolyvChannelAccountLoginRequester extends FunctionOperator<PolyvChannelUpdateAccountTokenParameter,
        PolyvChannelUpdateAccountTokenParameter.PolyvChannelUpdateAccountTokenParameterBuilder, PolyvUrl> {
    public PolyvChannelAccountLoginRequester(Function<PolyvChannelUpdateAccountTokenParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelUpdateAccountTokenParameter.PolyvChannelUpdateAccountTokenParameterBuilder.aPolyvChannelAccountTokenSetParameter(), function);
    }
}
