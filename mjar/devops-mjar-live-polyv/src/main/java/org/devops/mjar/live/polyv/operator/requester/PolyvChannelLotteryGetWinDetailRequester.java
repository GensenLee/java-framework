package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelLotteryListDetail;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLotteryGetWinDetailParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/17/22 9：00
 * @description：查询频道中奖记录
 */
public class PolyvChannelLotteryGetWinDetailRequester extends FunctionOperator<PolyvChannelLotteryGetWinDetailParameter,
        PolyvChannelLotteryGetWinDetailParameter.PolyvChannelLotteryGetWinDetailParameterBuilder, PolyvPaginator<PolyvChannelLotteryListDetail> > {
    public PolyvChannelLotteryGetWinDetailRequester(Function<PolyvChannelLotteryGetWinDetailParameter, PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>>> function) {
        super( PolyvChannelLotteryGetWinDetailParameter.PolyvChannelLotteryGetWinDetailParameterBuilder.aPolyvChannelLotteryGetWinDetailParameter(),function);
    }
}
