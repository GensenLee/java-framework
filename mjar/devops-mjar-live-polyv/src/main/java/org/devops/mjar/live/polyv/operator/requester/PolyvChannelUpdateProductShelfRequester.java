package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateProductShelfParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;
/**
 * @author GENSEN
 * @date 2021/7/26 18:18
 * @description：修改频道商品库上下架状态
 */
public class PolyvChannelUpdateProductShelfRequester extends FunctionOperator<PolyvChannelUpdateProductShelfParameter,
        PolyvChannelUpdateProductShelfParameter.PolyvChannelUpdateProductShelfParameterBuilder,String> {
    public PolyvChannelUpdateProductShelfRequester( Function<PolyvChannelUpdateProductShelfParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateProductShelfParameter.PolyvChannelUpdateProductShelfParameterBuilder.aPolyvChannelUpdateProductShelfParameter(), function);
    }
}
