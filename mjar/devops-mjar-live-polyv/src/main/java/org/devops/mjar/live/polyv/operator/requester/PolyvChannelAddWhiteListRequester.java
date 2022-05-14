package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelAddWhiteListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：增加频道单个白名单
 */
public class PolyvChannelAddWhiteListRequester extends FunctionOperator<PolyvChannelAddWhiteListParameter,
        PolyvChannelAddWhiteListParameter.PolyvChannelAddWhiteListParameterBuilder,String> {
    public PolyvChannelAddWhiteListRequester(Function<PolyvChannelAddWhiteListParameter, PolyvApiResult<String>> function) {
        super( PolyvChannelAddWhiteListParameter.PolyvChannelAddWhiteListParameterBuilder.aPolyvChannelAddWhiteListParameter(), function);
    }
}
