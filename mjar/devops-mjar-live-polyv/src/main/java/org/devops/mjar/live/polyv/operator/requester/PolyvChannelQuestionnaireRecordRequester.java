package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionnaireRecord;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireRecordParameter;

import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/22 10:01
 * @description：查询频道问卷结果
 */
public class PolyvChannelQuestionnaireRecordRequester extends FunctionOperator<PolyvChannelQuestionnaireRecordParameter,
        PolyvChannelQuestionnaireRecordParameter.PolyvChannelQuestionnaireRecordParameterBuilder, List<PolyvQuestionnaireRecord>> {
    public PolyvChannelQuestionnaireRecordRequester( Function<PolyvChannelQuestionnaireRecordParameter, PolyvApiResult<List<PolyvQuestionnaireRecord>>> function) {
        super(PolyvChannelQuestionnaireRecordParameter.PolyvChannelQuestionnaireRecordParameterBuilder.aPolyvChannelQuestionnaireRecordParameter(),function);
    }
}
