package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelSimpleInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppSearchChannelParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/1 15:25
 * @description：频道搜索
 */
public class PolyvAppSearchChannelRequester extends FunctionOperator<PolyvAppSearchChannelParameter,
        PolyvAppSearchChannelParameter.PolyvAppSearchChannelParameterBuilder, PolyvPaginator<PolyvChannelSimpleInfo>> {
    public PolyvAppSearchChannelRequester(Function<PolyvAppSearchChannelParameter,
            PolyvApiResult<PolyvPaginator<PolyvChannelSimpleInfo>>> function) {
        super(PolyvAppSearchChannelParameter.PolyvAppSearchChannelParameterBuilder.aPolyvAppSearchChannelParameter(), function);
    }
}
