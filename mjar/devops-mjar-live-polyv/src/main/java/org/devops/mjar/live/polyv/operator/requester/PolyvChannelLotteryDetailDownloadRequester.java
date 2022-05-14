package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.NativeFunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLotteryDetailDownloadParameter;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/17/22 9：00
 * @description：下载频道场次中奖记录
 */
public class PolyvChannelLotteryDetailDownloadRequester extends NativeFunctionOperator<PolyvChannelLotteryDetailDownloadParameter,
        PolyvChannelLotteryDetailDownloadParameter.PolyvChannelLotteryDetailDownloadParameterBuilder, ResponseEntity<byte[]>> {
    public PolyvChannelLotteryDetailDownloadRequester(Function<PolyvChannelLotteryDetailDownloadParameter, ResponseEntity<byte[]>> function) {
        super(PolyvChannelLotteryDetailDownloadParameter.PolyvChannelLotteryDetailDownloadParameterBuilder.aPolyvChannelLotteryDetailDownloadParameter(), function);
    }
}
