package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionnaireReview;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/9 10:57
 * @description：频道问卷列表请求
 */
public class PolyvChannelQuestionnaireListRequester extends FunctionOperator<PolyvChannelQuestionnaireListParameter,
        PolyvChannelQuestionnaireListParameter.PolyvChannelQuestionnaireListParameterBuilder, PolyvPaginator<PolyvQuestionnaireReview>> {

    public PolyvChannelQuestionnaireListRequester(Function<PolyvChannelQuestionnaireListParameter, PolyvApiResult<PolyvPaginator<PolyvQuestionnaireReview>>> function) {
        super(PolyvChannelQuestionnaireListParameter.PolyvChannelQuestionnaireListParameterBuilder.aPolyvChannelQuestionnaireListParameter(), function);
    }
}
