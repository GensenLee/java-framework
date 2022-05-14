package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDocumentListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道已上传文档列表请求
 */
public class PolyvChannelDocumentListRequester extends FunctionOperator<PolyvChannelDocumentListParameter,
        PolyvChannelDocumentListParameter.PolyvChannelDocumentListParameterBuilder, PolyvPaginator<PolyvDocumentInfo>> {
    public PolyvChannelDocumentListRequester(Function<PolyvChannelDocumentListParameter, PolyvApiResult<PolyvPaginator<PolyvDocumentInfo>>> function) {
        super(PolyvChannelDocumentListParameter.PolyvChannelDocumentListParameterBuilder.aPolyvChannelDocumentListParameter(), function);
    }
}
