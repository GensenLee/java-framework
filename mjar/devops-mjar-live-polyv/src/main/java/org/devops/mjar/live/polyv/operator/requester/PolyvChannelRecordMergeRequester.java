package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordMergeParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：合并直播录制
 */
public class PolyvChannelRecordMergeRequester extends FunctionOperator<PolyvChannelRecordMergeParameter,
        PolyvChannelRecordMergeParameter.PolyvChannelRecordMergeParameterBuilder, String> {
    public PolyvChannelRecordMergeRequester(Function<PolyvChannelRecordMergeParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelRecordMergeParameter.PolyvChannelRecordMergeParameterBuilder.aPolyvChannelRecordMergeParameter(), function);
    }
}
