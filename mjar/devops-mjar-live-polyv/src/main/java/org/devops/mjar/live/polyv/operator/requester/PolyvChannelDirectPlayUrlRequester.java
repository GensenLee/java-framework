package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDirectPlayUrlParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 16:13
 * @description：独立授权播放请求
 */
public class PolyvChannelDirectPlayUrlRequester extends FunctionOperator<PolyvChannelDirectPlayUrlParameter,
        PolyvChannelDirectPlayUrlParameter.PolyvChannelDirectPlayUrlParameterBuilder, PolyvUrl> {

    public PolyvChannelDirectPlayUrlRequester(Function<PolyvChannelDirectPlayUrlParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvChannelDirectPlayUrlParameter.PolyvChannelDirectPlayUrlParameterBuilder.aPolyvChannelDirectPlayUrlParameter(), function);
    }
}
