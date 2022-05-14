package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentStatus;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDocumentStatusParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道文档转码状态请求
 */
public class PolyvChannelDocumentStatusRequester extends FunctionOperator<PolyvChannelDocumentStatusParameter,
        PolyvChannelDocumentStatusParameter.PolyvChannelDocumentStatusParameterBuilder, List<PolyvDocumentStatus>> {
    public PolyvChannelDocumentStatusRequester(Function<PolyvChannelDocumentStatusParameter, PolyvApiResult<List<PolyvDocumentStatus>>> function) {
        super(PolyvChannelDocumentStatusParameter.PolyvChannelDocumentStatusParameterBuilder.aPolyvChannelDocumentStatusParameter(), function);
    }
}
