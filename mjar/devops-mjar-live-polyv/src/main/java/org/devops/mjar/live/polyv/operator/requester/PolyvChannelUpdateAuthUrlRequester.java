package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAuthUrlParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 9:48
 * @description：修改设置授权认证URL
 */
public class PolyvChannelUpdateAuthUrlRequester extends FunctionOperator<PolyvChannelUpdateAuthUrlParameter,
        PolyvChannelUpdateAuthUrlParameter.PolyvChannelUpdateAuthUrlParameterBuilder,String> {
    public PolyvChannelUpdateAuthUrlRequester(Function<PolyvChannelUpdateAuthUrlParameter, PolyvApiResult<String>> function) {
        super( PolyvChannelUpdateAuthUrlParameter.PolyvChannelUpdateAuthUrlParameterBuilder.aPolyvChannelUpdateAuthUrlParameter() , function);
    }
}
