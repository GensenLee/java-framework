package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordConvertParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量转存录制视频
 */
public class PolyvChannelRecordConvertRequester extends FunctionOperator<PolyvChannelRecordConvertParameter,
        PolyvChannelRecordConvertParameter.PolyvChannelRecordConvertParameterBuilder, String> {
    public PolyvChannelRecordConvertRequester(Function<PolyvChannelRecordConvertParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelRecordConvertParameter.PolyvChannelRecordConvertParameterBuilder.aPolyvChannelRecordConvertParameter(), function);
    }
}
