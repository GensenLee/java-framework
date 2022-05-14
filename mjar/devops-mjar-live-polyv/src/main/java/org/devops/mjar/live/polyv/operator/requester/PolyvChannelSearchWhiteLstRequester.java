package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelWhiteInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchWhiteListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 14:37
 * @description：查询频道白名单
 */
public class PolyvChannelSearchWhiteLstRequester extends FunctionOperator<PolyvChannelSearchWhiteListParameter,
        PolyvChannelSearchWhiteListParameter.PolyvChannelSearchWhiteLstParameterBuilder, PolyvPaginator<PolyvChannelWhiteInfo> > {
    public PolyvChannelSearchWhiteLstRequester(Function<PolyvChannelSearchWhiteListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelWhiteInfo>>> function) {
        super(PolyvChannelSearchWhiteListParameter.PolyvChannelSearchWhiteLstParameterBuilder.aPolyvChannelSearchWhiteLstParameter(), function);
    }
}
