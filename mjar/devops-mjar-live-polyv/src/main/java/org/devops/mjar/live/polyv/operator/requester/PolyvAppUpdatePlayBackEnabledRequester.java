package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdatePlayBackEnabledParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：图片上传请求
 */
public class PolyvAppUpdatePlayBackEnabledRequester extends FunctionOperator<PolyvAppUpdatePlayBackEnabledParameter,
        PolyvAppUpdatePlayBackEnabledParameter.PolyvAppUpdatePlayBackEnabledParameterBuilder, Object> {

    public PolyvAppUpdatePlayBackEnabledRequester(BiFunction<PolyvAppUpdatePlayBackEnabledParameter, String, PolyvApiResult<Object>> function) {
        super(PolyvAppUpdatePlayBackEnabledParameter.PolyvAppUpdatePlayBackEnabledParameterBuilder.aPolyvAppUpdatePlayBackEnabledParameter(), function, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
