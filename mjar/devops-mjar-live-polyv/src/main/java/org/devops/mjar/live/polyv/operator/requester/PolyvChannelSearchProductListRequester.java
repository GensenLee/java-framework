package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelProduct;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchProductListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 17:23
 * @description：查询频道商品列表
 */
public class PolyvChannelSearchProductListRequester extends FunctionOperator<PolyvChannelSearchProductListParameter,
        PolyvChannelSearchProductListParameter.PolyvChannelSearchProductListParameterBuilder, PolyvPaginator<PolyvChannelProduct> > {
    public PolyvChannelSearchProductListRequester(Function<PolyvChannelSearchProductListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelProduct>>> function) {
        super(PolyvChannelSearchProductListParameter.PolyvChannelSearchProductListParameterBuilder.aPolyvChannelSearchProductListParameter(), function);
    }
}
