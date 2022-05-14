package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateProductSortParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/7/26 18:18
 * @description：修改频道商品库列表顺序
 */
public class PolyvChannelUpdateProductSortRequester extends FunctionOperator<PolyvChannelUpdateProductSortParameter,
        PolyvChannelUpdateProductSortParameter.PolyvChannelUpdateProductSortParameterBuilder,String> {
    public PolyvChannelUpdateProductSortRequester(Function<PolyvChannelUpdateProductSortParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateProductSortParameter.PolyvChannelUpdateProductSortParameterBuilder.aPolyvChannelUpdateProductSortParameter(), function);
    }
}
