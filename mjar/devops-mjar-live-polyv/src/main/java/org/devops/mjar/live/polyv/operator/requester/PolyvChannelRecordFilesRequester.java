package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelRecordInfo;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordFilesParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：查询频道录制视频信息
 */
public class PolyvChannelRecordFilesRequester extends FunctionOperator<PolyvChannelRecordFilesParameter,
        PolyvChannelRecordFilesParameter.PolyvChannelRecordFilesParameterBuilder, List<PolyvChannelRecordInfo>> {
    public PolyvChannelRecordFilesRequester(BiFunction<PolyvChannelRecordFilesParameter, String, PolyvApiResult<List<PolyvChannelRecordInfo>>> function) {
        super(PolyvChannelRecordFilesParameter.PolyvChannelRecordFilesParameterBuilder.aPolyvChannelRecordFilesParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
