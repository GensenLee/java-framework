package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelPlayBackItem;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackAddVideosParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：将点播视频添加到视频库
 */
public class PolyvChannelPlayBackAddVideosRequester extends FunctionOperator<PolyvChannelPlayBackAddVideosParameter,
        PolyvChannelPlayBackAddVideosParameter.PolyvChannelPlayBackAddVideosParameterBuilder, PolyvChannelPlayBackItem> {

    public PolyvChannelPlayBackAddVideosRequester(Function<PolyvChannelPlayBackAddVideosParameter, PolyvApiResult<PolyvChannelPlayBackItem>> function) {
        super(PolyvChannelPlayBackAddVideosParameter.PolyvChannelPlayBackAddVideosParameterBuilder.aPolyvChannelPlayBackAddVideosParameter(), function);
    }
}
