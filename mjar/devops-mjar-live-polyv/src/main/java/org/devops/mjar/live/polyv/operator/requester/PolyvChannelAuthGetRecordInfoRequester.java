package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthGetRecordInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAuthGetRecordInfoParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/21 17:00
 * @description：查询频道登记观看记录
 */
public class PolyvChannelAuthGetRecordInfoRequester extends FunctionOperator<PolyvChannelAuthGetRecordInfoParameter,
        PolyvChannelAuthGetRecordInfoParameter.PolyvChannelAuthGetRecordInfoParameterBuilder, PolyvPaginator<PolyvChannelAuthGetRecordInfo>> {
    public PolyvChannelAuthGetRecordInfoRequester(Function<PolyvChannelAuthGetRecordInfoParameter, PolyvApiResult<PolyvPaginator<PolyvChannelAuthGetRecordInfo>>> function) {
        super(PolyvChannelAuthGetRecordInfoParameter.PolyvChannelAuthGetRecordInfoParameterBuilder.aPolyvChannelAuthGetRecordInfoParameter(), function);
    }
}
