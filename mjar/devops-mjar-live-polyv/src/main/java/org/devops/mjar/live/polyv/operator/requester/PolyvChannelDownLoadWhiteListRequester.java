package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDownLoadWhiteListParameter;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：下载频道白名单
 */
public class PolyvChannelDownLoadWhiteListRequester extends NativeFunctionOperator<PolyvChannelDownLoadWhiteListParameter,
        PolyvChannelDownLoadWhiteListParameter.PolyvChannelDownLoadWhiteListParameterBuilder, ResponseEntity<byte[]>> {
    public PolyvChannelDownLoadWhiteListRequester( Function<PolyvChannelDownLoadWhiteListParameter, ResponseEntity<byte[]>> function) {
        super(PolyvChannelDownLoadWhiteListParameter.PolyvChannelDownLoadWhiteListParameterBuilder.aPolyvChannelBatchUploadWhiteListParameter(), function);
    }
}
