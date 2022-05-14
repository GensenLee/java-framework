package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordClipParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：裁剪录制视频
 */
public class PolyvChannelRecordClipRequester extends FunctionOperator<PolyvChannelRecordClipParameter,
        PolyvChannelRecordClipParameter.PolyvChannelRecordClipParameterBuilder,String> {
    public PolyvChannelRecordClipRequester(Function<PolyvChannelRecordClipParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelRecordClipParameter.PolyvChannelRecordClipParameterBuilder.aPolyvChannelRecordClipParameter(), function);
    }
}
