package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelRecordDetail;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordDetailParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：根据fileId查询录制视频信息
 */
public class PolyvChannelRecordDetailRequester extends FunctionOperator<PolyvChannelRecordDetailParameter,
        PolyvChannelRecordDetailParameter.PolyvChannelRecordDetailParameterBuilder, PolyvChannelRecordDetail> {
    public PolyvChannelRecordDetailRequester(Function<PolyvChannelRecordDetailParameter, PolyvApiResult<PolyvChannelRecordDetail>> function) {
        super(PolyvChannelRecordDetailParameter.PolyvChannelRecordDetailParameterBuilder.aPolyvChannelRecordDetailParameter(), function);
    }
}
