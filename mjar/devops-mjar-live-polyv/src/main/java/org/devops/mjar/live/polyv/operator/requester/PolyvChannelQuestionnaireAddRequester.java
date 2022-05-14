package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelQuestionBasic;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireAddParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：新增或修改频道问卷
 */
public class PolyvChannelQuestionnaireAddRequester extends FunctionOperator<PolyvChannelQuestionnaireAddParameter,
        PolyvChannelQuestionnaireAddParameter.PolyvChannelQuestionnaireAddParameterBuilder, PolyvChannelQuestionBasic> {
    public PolyvChannelQuestionnaireAddRequester(Function<PolyvChannelQuestionnaireAddParameter, PolyvApiResult<PolyvChannelQuestionBasic>> function) {
        super(PolyvChannelQuestionnaireAddParameter.PolyvChannelQuestionnaireAddParameterBuilder.aPolyvChannelQuestionnaireAddParameter(), function);
    }
}
