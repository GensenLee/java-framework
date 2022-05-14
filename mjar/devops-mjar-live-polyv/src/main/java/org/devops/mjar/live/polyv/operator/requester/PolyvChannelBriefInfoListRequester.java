package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelBriefInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBriefInfoListParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：查询账号下所有频道缩略信息请求
 */
public class PolyvChannelBriefInfoListRequester extends FunctionOperator<PolyvChannelBriefInfoListParameter,
        PolyvChannelBriefInfoListParameter.PolyvChannelBriefInfoListParameterBuilder, PolyvPaginator<PolyvChannelBriefInfo>> {

    public PolyvChannelBriefInfoListRequester(Function<PolyvChannelBriefInfoListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelBriefInfo>>> function) {
        super(PolyvChannelBriefInfoListParameter.PolyvChannelBriefInfoListParameterBuilder.aPolyvChannelBriefInfoListParameter(), function);
    }
}
