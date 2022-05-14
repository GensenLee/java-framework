package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelMenuDetail;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchMenuListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/23 15:56
 * @description：查询频道页面菜单信息
 */
public class PolyvChannelSearchMenuListRequester extends FunctionOperator<PolyvChannelSearchMenuListParameter,
        PolyvChannelSearchMenuListParameter.PolyvChannelSearchMenuListParameterBuilder, List<PolyvChannelMenuDetail>> {

    public PolyvChannelSearchMenuListRequester(Function<PolyvChannelSearchMenuListParameter, PolyvApiResult<List<PolyvChannelMenuDetail>>> function) {
        super(PolyvChannelSearchMenuListParameter.PolyvChannelSearchMenuListParameterBuilder.aPolyvChannelSearchMenuListParameter(), function);
    }
}
