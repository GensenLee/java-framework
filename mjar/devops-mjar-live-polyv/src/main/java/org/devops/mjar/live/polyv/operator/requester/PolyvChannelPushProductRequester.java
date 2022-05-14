package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPushProductParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author wsl
 * @description：推送频道商品库商品
 */
public class PolyvChannelPushProductRequester extends FunctionOperator<PolyvChannelPushProductParameter,
        PolyvChannelPushProductParameter.PolyvChannelPushProductParameterBuilder,String > {
    public PolyvChannelPushProductRequester(Function<PolyvChannelPushProductParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPushProductParameter.PolyvChannelPushProductParameterBuilder.aPolyvChannelPushProductParameter(), function);
    }
}
