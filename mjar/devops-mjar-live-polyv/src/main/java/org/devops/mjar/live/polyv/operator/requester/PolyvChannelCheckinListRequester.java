package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelCheckin;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCheckinListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/7/12 18:25
 * @description：发起签到记录请求
 */
public class PolyvChannelCheckinListRequester extends FunctionOperator<PolyvChannelCheckinListParameter,
        PolyvChannelCheckinListParameter.PolyvChannelCheckinListParameterBuilder, List<PolyvChannelCheckin>> {
    public PolyvChannelCheckinListRequester(Function<PolyvChannelCheckinListParameter, PolyvApiResult<List<PolyvChannelCheckin>>> function) {
        super(PolyvChannelCheckinListParameter.PolyvChannelCheckinListParameterBuilder.aPolyvChannelCheckinListParameter(), function);
    }
}
