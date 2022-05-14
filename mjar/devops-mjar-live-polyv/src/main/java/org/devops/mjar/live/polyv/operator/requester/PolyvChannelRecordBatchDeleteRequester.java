package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordBatchDeleteParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量删除暂存列表视频
 */
public class PolyvChannelRecordBatchDeleteRequester extends FunctionOperator<PolyvChannelRecordBatchDeleteParameter,
        PolyvChannelRecordBatchDeleteParameter.PolyvChannelRecordBatchDeleteParameterBuilder,String> {
    public PolyvChannelRecordBatchDeleteRequester(Function<PolyvChannelRecordBatchDeleteParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelRecordBatchDeleteParameter.PolyvChannelRecordBatchDeleteParameterBuilder.aPolyvChannelRecordBatchDeleteParameter(), function);
    }
}
