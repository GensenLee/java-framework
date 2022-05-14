package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPlayBackItem;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackListParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/12/23 14:38
 * @description：查询视频库列表
 */
public class PolyvChannelPlayBackListRequester extends FunctionOperator<PolyvChannelPlayBackListParameter,
        PolyvChannelPlayBackListParameter.PolyvChannelPlayBackListParameterBuilder, PolyvPaginator<PolyvChannelPlayBackItem>> {
    public PolyvChannelPlayBackListRequester(BiFunction<PolyvChannelPlayBackListParameter, String, PolyvApiResult<PolyvPaginator<PolyvChannelPlayBackItem>>> function) {
        super(PolyvChannelPlayBackListParameter.PolyvChannelPlayBackListParameterBuilder.aPolyvChannelPlayBackListParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
