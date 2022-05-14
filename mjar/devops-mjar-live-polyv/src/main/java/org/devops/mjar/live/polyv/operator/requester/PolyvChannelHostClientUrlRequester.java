package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelClientStartUrlParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/7/14 16:06
 * @description：主持人客户端链接
 */
public class PolyvChannelHostClientUrlRequester extends FunctionOperator<PolyvChannelClientStartUrlParameter,
        PolyvChannelClientStartUrlParameter.PolyvChannelClientHostStartUrlParameterBuilder, PolyvUrl> {
    public PolyvChannelHostClientUrlRequester(Function<PolyvChannelClientStartUrlParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelClientStartUrlParameter.PolyvChannelClientHostStartUrlParameterBuilder.aPolyvChannelClientStartUrlParameter(), function);
    }
}
