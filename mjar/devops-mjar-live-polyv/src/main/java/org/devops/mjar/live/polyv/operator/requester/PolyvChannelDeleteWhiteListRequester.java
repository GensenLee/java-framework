package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteWhiteListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 18:20
 * @description：删除频道白名单
 */
public class PolyvChannelDeleteWhiteListRequester extends FunctionOperator<PolyvChannelDeleteWhiteListParameter,
        PolyvChannelDeleteWhiteListParameter.PolyvChannelDeleteWhiteListParameterBuilder,String> {
    public PolyvChannelDeleteWhiteListRequester(Function<PolyvChannelDeleteWhiteListParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelDeleteWhiteListParameter.PolyvChannelDeleteWhiteListParameterBuilder.aPolyvChannelDeleteWhiteListParameter(), function);
    }
}
