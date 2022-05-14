package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelGuestPlayUrlParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/07/13 16:13
 * @description：嘉宾链接
 */
public class PolyvChannelGuestStartUrlRequester extends FunctionOperator<PolyvChannelGuestPlayUrlParameter,
        PolyvChannelGuestPlayUrlParameter.PolyvChannelGuestPlayUrlParameterBuilder, PolyvUrl> {

    public PolyvChannelGuestStartUrlRequester(Function<PolyvChannelGuestPlayUrlParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelGuestPlayUrlParameter.PolyvChannelGuestPlayUrlParameterBuilder.aPolyvChannelGuestPlayUrlParameter(), function);
    }
}
