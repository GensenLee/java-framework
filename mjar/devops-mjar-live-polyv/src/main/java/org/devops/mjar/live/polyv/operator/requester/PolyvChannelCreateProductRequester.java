package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelProduct;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateProductParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 16:13
 * @description：添加频道商品
 */
public class PolyvChannelCreateProductRequester extends FunctionOperator<PolyvChannelCreateProductParameter,
        PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBuilder, PolyvChannelProduct> {

    public PolyvChannelCreateProductRequester( Function<PolyvChannelCreateProductParameter, PolyvApiResult<PolyvChannelProduct>> function) {
        super(PolyvChannelCreateProductParameter.PolyvChannelCreateProductParameterBuilder.aPolyvChannelCreateProductParameter(), function);
    }
}
