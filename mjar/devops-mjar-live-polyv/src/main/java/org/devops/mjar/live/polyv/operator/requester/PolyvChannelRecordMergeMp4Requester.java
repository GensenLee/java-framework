package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvMergeMp4;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordMergeMp4Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：合并直播录制文件并回调mp4下载地址
 */
public class PolyvChannelRecordMergeMp4Requester extends FunctionOperator<PolyvChannelRecordMergeMp4Parameter,
        PolyvChannelRecordMergeMp4Parameter.PolyvChannelRecordMergeMp4ParameterBuilder, PolyvMergeMp4> {
    public PolyvChannelRecordMergeMp4Requester(Function<PolyvChannelRecordMergeMp4Parameter, PolyvApiResult<PolyvMergeMp4>> function) {
        super(PolyvChannelRecordMergeMp4Parameter.PolyvChannelRecordMergeMp4ParameterBuilder.aPolyvChannelRecordMergeMp4Parameter(), function);
    }
}
