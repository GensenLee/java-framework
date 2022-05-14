package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelLotteryListDetail;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelLotteryListLotteryParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/17/21 17：30
 * @description：查询频道中奖记录
 */
public class PolyvChannelLotteryListLotteryRequester extends FunctionOperator<PolyvChannelLotteryListLotteryParameter,
        PolyvChannelLotteryListLotteryParameter.PolyvChannelLotteryListLotteryParameterBuilder, PolyvPaginator<PolyvChannelLotteryListDetail>> {
    public PolyvChannelLotteryListLotteryRequester( Function<PolyvChannelLotteryListLotteryParameter, PolyvApiResult<PolyvPaginator<PolyvChannelLotteryListDetail>>> function) {
        super(PolyvChannelLotteryListLotteryParameter.PolyvChannelLotteryListLotteryParameterBuilder.aPolyvChannelLotteryListLotteryParameter() , function);
    }
}
