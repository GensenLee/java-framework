package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppBatchDeleteChannelParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/3 11:40
 * @description：批量删除频道
 */
public class PolyvAppBatchDeleteChannelRequester extends FunctionOperator<PolyvAppBatchDeleteChannelParameter,
        PolyvAppBatchDeleteChannelParameter.PolyvAppBatchDeleteChannelParameterBuilder, Object> {
    public PolyvAppBatchDeleteChannelRequester(Function<PolyvAppBatchDeleteChannelParameter, PolyvApiResult<Object>> function) {
        super(PolyvAppBatchDeleteChannelParameter.PolyvAppBatchDeleteChannelParameterBuilder.aPolyvAppBatchDeleteChannelParameter(), function);
    }
}
