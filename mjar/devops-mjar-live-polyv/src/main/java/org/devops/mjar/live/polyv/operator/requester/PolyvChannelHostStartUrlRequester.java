package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStartUrlParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/07/13 16:13
 * @description：主持人链接
 */
public class PolyvChannelHostStartUrlRequester extends FunctionOperator<PolyvChannelStartUrlParameter,
        PolyvChannelStartUrlParameter.PolyvChannelStartUrlParameterBuilder, PolyvUrl> {

    public PolyvChannelHostStartUrlRequester(Function<PolyvChannelStartUrlParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelStartUrlParameter.PolyvChannelStartUrlParameterBuilder.aPolyvChannelStartUrlParameter(), function);
    }
}
