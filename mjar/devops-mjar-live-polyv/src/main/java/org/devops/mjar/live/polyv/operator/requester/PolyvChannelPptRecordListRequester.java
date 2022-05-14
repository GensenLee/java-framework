package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPptRecordList;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPptRecordListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author wsl
 * @description：查询重制课件任务列表
 */
public class PolyvChannelPptRecordListRequester extends FunctionOperator<PolyvChannelPptRecordListParameter,
        PolyvChannelPptRecordListParameter.PolyvChannelPptRecordListParameterBuilder, PolyvPaginator<PolyvChannelPptRecordList>> {
    public PolyvChannelPptRecordListRequester(Function<PolyvChannelPptRecordListParameter,  PolyvApiResult<PolyvPaginator<PolyvChannelPptRecordList>>> function) {
        super(PolyvChannelPptRecordListParameter.PolyvChannelPptRecordListParameterBuilder.aPolyvChannelPptRecordListParameter(), function);
    }
}
