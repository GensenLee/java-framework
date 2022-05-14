package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvMicLog;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelMicLogListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/10 18:36
 * @description：连麦日志请求
 */
public class PolyvChannelMicLogListRequester extends FunctionOperator<PolyvChannelMicLogListParameter,
        PolyvChannelMicLogListParameter.PolyvChannelMicLogListParameterBuilder, PolyvPaginator<PolyvMicLog>> {

    public PolyvChannelMicLogListRequester(Function<PolyvChannelMicLogListParameter, PolyvApiResult<PolyvPaginator<PolyvMicLog>>> function) {
        super(PolyvChannelMicLogListParameter.PolyvChannelMicLogListParameterBuilder.aPolyvChannelMicLogListParameter(), function);
    }
}
