package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelQuestionnaireDetailParameter;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/9 10:57
 * @description：频道问卷导出请求
 */
public class PolyvChannelExportQuestionnaireRequester extends NativeFunctionOperator<PolyvChannelQuestionnaireDetailParameter,
        PolyvChannelQuestionnaireDetailParameter.PolyvChannelQuestionnaireDetailParameterBuilder, ResponseEntity<byte[]>> {

    public PolyvChannelExportQuestionnaireRequester(Function<PolyvChannelQuestionnaireDetailParameter, ResponseEntity<byte[]>> function) {
        super(PolyvChannelQuestionnaireDetailParameter.PolyvChannelQuestionnaireDetailParameterBuilder.aPolyvChannelQuestionnaireDetailParameter(), function);
    }
}
