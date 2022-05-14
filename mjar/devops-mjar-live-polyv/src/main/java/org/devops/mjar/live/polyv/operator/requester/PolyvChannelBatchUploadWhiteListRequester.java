package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBatchUploadWhiteListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：批量导入频道白名单
 */
public class  PolyvChannelBatchUploadWhiteListRequester extends FunctionOperator<PolyvChannelBatchUploadWhiteListParameter,
        PolyvChannelBatchUploadWhiteListParameter.PolyvChannelBatchUploadWhiteListParameterBuilder,String > {
    public PolyvChannelBatchUploadWhiteListRequester(Function<PolyvChannelBatchUploadWhiteListParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelBatchUploadWhiteListParameter.PolyvChannelBatchUploadWhiteListParameterBuilder.aPolyvChannelBatchUploadWhiteListParameter(), function);
    }
}
