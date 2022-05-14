package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchQuestionaireResultParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * 查询频道答题卡结果
 */
public class PolyvChannelSearchQuestionaireResultRequester extends FunctionOperator<PolyvChannelSearchQuestionaireResultParameter,
        PolyvChannelSearchQuestionaireResultParameter.PolyvChannelSearchQuestionaireResultParameterBuilder, List<PolyvQuestionResult>> {


    public PolyvChannelSearchQuestionaireResultRequester(Function<PolyvChannelSearchQuestionaireResultParameter, PolyvApiResult<List<PolyvQuestionResult>>> function) {
        super(PolyvChannelSearchQuestionaireResultParameter.PolyvChannelSearchQuestionaireResultParameterBuilder.aPolyvChannelSearchQuestionaireResultParameter(), function);
    }
}
