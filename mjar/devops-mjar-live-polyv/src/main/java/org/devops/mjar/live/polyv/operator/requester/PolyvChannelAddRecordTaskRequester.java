package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAddRecordTaskParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：创建课件重制任务
 */
public class PolyvChannelAddRecordTaskRequester extends FunctionOperator<PolyvChannelAddRecordTaskParameter,
        PolyvChannelAddRecordTaskParameter.PolyvChannelAddRecordTaskParameterBuilder, Boolean> {
    public PolyvChannelAddRecordTaskRequester(Function<PolyvChannelAddRecordTaskParameter, PolyvApiResult<Boolean>> function) {
        super(PolyvChannelAddRecordTaskParameter.PolyvChannelAddRecordTaskParameterBuilder.aPolyvChannelAddRecordTaskParameter(), function);
    }
}
