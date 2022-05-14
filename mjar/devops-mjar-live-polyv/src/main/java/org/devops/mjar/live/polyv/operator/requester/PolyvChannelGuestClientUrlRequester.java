package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelClientStartUrlParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/7/14 16:06
 * @description：嘉宾客户端链接
 */
public class PolyvChannelGuestClientUrlRequester extends FunctionOperator<PolyvChannelClientStartUrlParameter,
        PolyvChannelClientStartUrlParameter.PolyvChannelClientGuestStartUrlParameterBuilder, PolyvUrl> {
    public PolyvChannelGuestClientUrlRequester(Function<PolyvChannelClientStartUrlParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelClientStartUrlParameter.PolyvChannelClientGuestStartUrlParameterBuilder.aPolyvChannelClientStartUrlParameter(), function);
    }
}
