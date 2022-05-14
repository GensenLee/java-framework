package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelViewLogV1;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchViewLogV2Parameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * 分页查询频道直播观看详情数据
 */
public class PolyvChannelSearchViewLogV2Requester extends FunctionOperator<PolyvChannelSearchViewLogV2Parameter,
        PolyvChannelSearchViewLogV2Parameter.PolyvChannelSearchViewLogV2ParameterBuilder, PolyvPaginator<PolyvChannelViewLogV1>> {
    public PolyvChannelSearchViewLogV2Requester(BiFunction<PolyvChannelSearchViewLogV2Parameter, String, PolyvApiResult<PolyvPaginator<PolyvChannelViewLogV1>>> biFunction ) {
        super(PolyvChannelSearchViewLogV2Parameter.PolyvChannelSearchViewLogV2ParameterBuilder.aPolyvChannelSearchViewLogV2Parameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
