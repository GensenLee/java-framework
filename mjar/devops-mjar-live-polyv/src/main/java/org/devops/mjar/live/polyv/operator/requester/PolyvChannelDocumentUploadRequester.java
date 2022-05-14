package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentUpload;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDocumentUploadParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：上传文档到某个频道请求
 */
public class PolyvChannelDocumentUploadRequester extends FunctionOperator<PolyvChannelDocumentUploadParameter,
        PolyvChannelDocumentUploadParameter.PolyvChannelDocumentUploadParameterBuilder, PolyvDocumentUpload> {
    public PolyvChannelDocumentUploadRequester(Function<PolyvChannelDocumentUploadParameter, PolyvApiResult<PolyvDocumentUpload>> function) {
        super(PolyvChannelDocumentUploadParameter.PolyvChannelDocumentUploadParameterBuilder.aPolyvChannelDocumentUploadParameter(), function);
    }
}
