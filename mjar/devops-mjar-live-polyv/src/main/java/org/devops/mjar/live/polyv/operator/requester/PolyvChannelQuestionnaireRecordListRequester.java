package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionnaireRecord;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireRecordListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/22 10:01
 * @description：分页查询频道问卷结果
 */
public class PolyvChannelQuestionnaireRecordListRequester extends FunctionOperator<PolyvChannelQuestionnaireRecordListParameter,
        PolyvChannelQuestionnaireRecordListParameter.PolyvChannelQuestionnaireRecordParameterListBuilder, PolyvPaginator<PolyvQuestionnaireRecord>> {

    public PolyvChannelQuestionnaireRecordListRequester( Function<PolyvChannelQuestionnaireRecordListParameter, PolyvApiResult<PolyvPaginator<PolyvQuestionnaireRecord>>> function) {
        super(PolyvChannelQuestionnaireRecordListParameter.PolyvChannelQuestionnaireRecordParameterListBuilder.aPolyvChannelQuestionnaireRecordParameter(), function);
    }
}
