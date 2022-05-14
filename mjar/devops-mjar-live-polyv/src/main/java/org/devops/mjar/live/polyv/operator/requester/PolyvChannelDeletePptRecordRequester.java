package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPptRecordResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeletePptRecordParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * 删除重制课件
 */
public class PolyvChannelDeletePptRecordRequester extends FunctionOperator<PolyvChannelDeletePptRecordParameter,
        PolyvChannelDeletePptRecordParameter.PolyvChannelDeletePptRecordParameterBuilder, PolyvChannelPptRecordResult> {
    public PolyvChannelDeletePptRecordRequester(Function<PolyvChannelDeletePptRecordParameter, PolyvApiResult<PolyvChannelPptRecordResult>> function) {
        super( PolyvChannelDeletePptRecordParameter.PolyvChannelDeletePptRecordParameterBuilder.aPolyvChannelDeletePptRecordParameter() , function);
    }
}
