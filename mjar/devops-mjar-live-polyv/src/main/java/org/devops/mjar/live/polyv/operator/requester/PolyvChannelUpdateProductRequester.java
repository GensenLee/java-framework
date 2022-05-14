package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateProductParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 9:30
 * @description：修改频道商品
 */
public class PolyvChannelUpdateProductRequester extends FunctionOperator<PolyvChannelUpdateProductParameter,
        PolyvChannelUpdateProductParameter.PolyvChannelUpdateProductParameterBuilder, String> {

    public PolyvChannelUpdateProductRequester(Function<PolyvChannelUpdateProductParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateProductParameter.PolyvChannelUpdateProductParameterBuilder.aPolyvChannelUpdateProductParameter(), function);
    }
}
