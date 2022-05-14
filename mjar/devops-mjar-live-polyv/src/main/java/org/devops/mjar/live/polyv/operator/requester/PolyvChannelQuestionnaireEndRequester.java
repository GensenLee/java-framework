package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireEndParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：手动结束问卷
 */
public class PolyvChannelQuestionnaireEndRequester extends FunctionOperator<PolyvChannelQuestionnaireEndParameter,
        PolyvChannelQuestionnaireEndParameter.PolyvChannelQuestionnaireEndParameterBuilder,Object> {
    public PolyvChannelQuestionnaireEndRequester(Function<PolyvChannelQuestionnaireEndParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelQuestionnaireEndParameter.PolyvChannelQuestionnaireEndParameterBuilder.aPolyvChannelQuestionnaireEndParameter(), function);
    }
}
