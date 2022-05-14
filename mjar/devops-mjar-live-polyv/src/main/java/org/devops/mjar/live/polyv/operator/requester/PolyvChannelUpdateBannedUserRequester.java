package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateBannedUserParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：禁言/解禁用户
 */
public class PolyvChannelUpdateBannedUserRequester extends FunctionOperator<PolyvChannelUpdateBannedUserParameter,
        PolyvChannelUpdateBannedUserParameter.PolyvChannelUpdateBannedUserParameterBuilder,String> {
    public PolyvChannelUpdateBannedUserRequester(Function<PolyvChannelUpdateBannedUserParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateBannedUserParameter.PolyvChannelUpdateBannedUserParameterBuilder.aPolyvChannelUpdateBannedUserParameter(), function);
    }
}
