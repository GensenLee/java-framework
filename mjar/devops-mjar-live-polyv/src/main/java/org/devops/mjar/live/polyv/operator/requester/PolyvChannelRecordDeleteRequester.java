package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordDeleteParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：删除直播暂存录制文件
 */
public class PolyvChannelRecordDeleteRequester extends FunctionOperator<PolyvChannelRecordDeleteParameter,
        PolyvChannelRecordDeleteParameter.PolyvChannelRecordDeleteParameterBuilder, String> {

    public PolyvChannelRecordDeleteRequester(BiFunction<PolyvChannelRecordDeleteParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelRecordDeleteParameter.PolyvChannelRecordDeleteParameterBuilder.aPolyvChannelRecordDeleteParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
