package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateProductEnabledParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 18:03
 * @description：修改频道商品库开关状态
 */
public class PolyvChannelUpdateProductEnabledRequester extends FunctionOperator<PolyvChannelUpdateProductEnabledParameter,
        PolyvChannelUpdateProductEnabledParameter.PolyvChannelUpdateProductEnabledParameterBuilder,String> {
    public PolyvChannelUpdateProductEnabledRequester( Function<PolyvChannelUpdateProductEnabledParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateProductEnabledParameter.PolyvChannelUpdateProductEnabledParameterBuilder.aPolyvChannelUpdateProductEnabledParameter(), function);
    }
}
