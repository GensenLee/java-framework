package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelDetail;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDetailListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：频道详情列表请求
 */
public class PolyvChannelDetailListRequester extends FunctionOperator<PolyvChannelDetailListParameter,
        PolyvChannelDetailListParameter.PolyvChannelDetailListParameterBuilder, PolyvPaginator<PolyvChannelDetail>> {

    public PolyvChannelDetailListRequester(Function<PolyvChannelDetailListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelDetail>>> function) {
        super(PolyvChannelDetailListParameter.PolyvChannelDetailListParameterBuilder.aPolyvChannelBriefInfoListParameter(), function);
    }
}
