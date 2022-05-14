package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelBasic;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBasicListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询所有频道的基础信息
 */
public class PolyvChannelBasicListRequester extends FunctionOperator<PolyvChannelBasicListParameter,
    PolyvChannelBasicListParameter.PolyvChannelBasicListParameterBuilder, PolyvPaginator<PolyvChannelBasic>> {
    public PolyvChannelBasicListRequester(Function<PolyvChannelBasicListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelBasic>>> function) {
        super(PolyvChannelBasicListParameter.PolyvChannelBasicListParameterBuilder.aPolyvChannelBasicListParameter(), function);
    }
}
