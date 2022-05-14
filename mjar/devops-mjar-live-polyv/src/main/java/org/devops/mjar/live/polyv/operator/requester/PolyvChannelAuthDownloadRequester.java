package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAuthDownloadRecordParameter;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/22 12:00
 * @description：下载频道登记观看记录
 */
public class PolyvChannelAuthDownloadRequester extends NativeFunctionOperator<PolyvChannelAuthDownloadRecordParameter,
        PolyvChannelAuthDownloadRecordParameter.PolyvChannelAuthDownloadRecordParameterBuilder, ResponseEntity<byte[]>> {
    public PolyvChannelAuthDownloadRequester(Function<PolyvChannelAuthDownloadRecordParameter, ResponseEntity<byte[]>> function) {
        super(PolyvChannelAuthDownloadRecordParameter.PolyvChannelAuthDownloadRecordParameterBuilder.aPolyvChannelAuthDownloadRecordParameter(), function);
    }
}
