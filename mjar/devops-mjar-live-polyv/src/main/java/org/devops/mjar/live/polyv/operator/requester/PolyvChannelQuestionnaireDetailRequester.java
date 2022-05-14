package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionnaire;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireDetailParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/9 10:57
 * @description：问卷详情请求
 */
public class PolyvChannelQuestionnaireDetailRequester extends FunctionOperator<PolyvChannelQuestionnaireDetailParameter,
        PolyvChannelQuestionnaireDetailParameter.PolyvChannelQuestionnaireDetailParameterBuilder, PolyvQuestionnaire> {

    public PolyvChannelQuestionnaireDetailRequester(Function<PolyvChannelQuestionnaireDetailParameter,PolyvApiResult<PolyvQuestionnaire>> function) {
        super(PolyvChannelQuestionnaireDetailParameter.PolyvChannelQuestionnaireDetailParameterBuilder.aPolyvChannelQuestionnaireDetailParameter(), function);
    }
}
