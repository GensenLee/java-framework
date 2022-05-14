package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDocumentDeleteParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：删除文档请求
 */
public class PolyvChannelDocumentDeleteRequester extends FunctionOperator<PolyvChannelDocumentDeleteParameter,
        PolyvChannelDocumentDeleteParameter.PolyvChannelDocumentDeleteParameterBuilder,String> {
    public PolyvChannelDocumentDeleteRequester(Function<PolyvChannelDocumentDeleteParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelDocumentDeleteParameter.PolyvChannelDocumentDeleteParameterBuilder.aPolyvChannelDocumentDeleteParameter(), function);
    }
}
