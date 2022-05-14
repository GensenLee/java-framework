package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPlayBackItem;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：获取账号下回放视频
 */
public class PolyvChannelPlayBackSpecialListRequester extends FunctionOperator<PolyvChannelPlayBackListParameter,
        PolyvChannelPlayBackListParameter.PolyvChannelPlayBackListParameterBuilder, PolyvPaginator<PolyvChannelPlayBackItem>> {

    public PolyvChannelPlayBackSpecialListRequester(Function<PolyvChannelPlayBackListParameter, PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>>> function) {
        super(PolyvChannelPlayBackListParameter.PolyvChannelPlayBackListParameterBuilder.aPolyvChannelPlayBackListParameter(), function);
    }
}
