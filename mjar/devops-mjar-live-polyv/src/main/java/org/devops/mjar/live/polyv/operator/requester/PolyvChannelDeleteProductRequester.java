package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteProductParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/7/26 18:23
 * @description：删除频道商品
 */
public class PolyvChannelDeleteProductRequester extends FunctionOperator<PolyvChannelDeleteProductParameter,
        PolyvChannelDeleteProductParameter.PolyvChannelDeleteProductParameterBuilder,String> {
    public PolyvChannelDeleteProductRequester(Function<PolyvChannelDeleteProductParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelDeleteProductParameter.PolyvChannelDeleteProductParameterBuilder.aPolyvChannelDeleteProductParameter(), function);
    }
}
