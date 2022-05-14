package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateCoverImgParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：修改频道图标
 */
public class PolyvChannelUpdateCoverImgRequester extends FunctionOperator<PolyvChannelUpdateCoverImgParameter,
        PolyvChannelUpdateCoverImgParameter.PolyvChannelUpdateCoverImgParameterBuilder,String> {
    public PolyvChannelUpdateCoverImgRequester(BiFunction<PolyvChannelUpdateCoverImgParameter, String, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateCoverImgParameter.PolyvChannelUpdateCoverImgParameterBuilder.aPolyvChannelUpdateCoverImgParameter(), function, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
