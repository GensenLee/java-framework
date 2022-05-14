package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelList;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppBatchCreateChannelParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/3 14:14
 * @description：批量创建频道
 */
public class PolyvAppBatchCreateChannelRequester extends FunctionOperator<PolyvAppBatchCreateChannelParameter,
        PolyvAppBatchCreateChannelParameter.PolyvAppBatchCreateChannelParameterBuilder, PolyvChannelList> {
    public PolyvAppBatchCreateChannelRequester(Function<PolyvAppBatchCreateChannelParameter, PolyvApiResult<PolyvChannelList>> function) {
        super(PolyvAppBatchCreateChannelParameter.PolyvAppBatchCreateChannelParameterBuilder.aPolyvAppBatchCreateChannelParameter(), function);
    }
}
