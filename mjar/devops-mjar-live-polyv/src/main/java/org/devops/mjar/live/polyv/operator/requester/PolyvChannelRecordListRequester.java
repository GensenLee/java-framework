package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelRecordItem;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelRecordListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：分页获取直播暂存列表
 */
public class PolyvChannelRecordListRequester extends FunctionOperator<PolyvChannelRecordListParameter,
        PolyvChannelRecordListParameter.PolyvChannelRecordListParameterBuilder, PolyvPaginator<PolyvChannelRecordItem>> {
    public PolyvChannelRecordListRequester(Function<PolyvChannelRecordListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelRecordItem>>> function) {
        super(PolyvChannelRecordListParameter.PolyvChannelRecordListParameterBuilder.aPolyvChannelRecordListParameter(), function);
    }
}
