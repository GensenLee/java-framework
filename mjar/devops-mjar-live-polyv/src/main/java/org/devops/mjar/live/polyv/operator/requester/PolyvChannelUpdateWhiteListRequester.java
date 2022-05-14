package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateWhiteListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 15:27
 * @description：修改频道单个白名单
 */

public class PolyvChannelUpdateWhiteListRequester extends FunctionOperator<PolyvChannelUpdateWhiteListParameter,
        PolyvChannelUpdateWhiteListParameter.PolyvChannelUpdateWhiteListParameterBuilder,String> {
    public PolyvChannelUpdateWhiteListRequester(Function<PolyvChannelUpdateWhiteListParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateWhiteListParameter.PolyvChannelUpdateWhiteListParameterBuilder.aPolyvChannelAddWhiteListParameter(), function);
    }
}
