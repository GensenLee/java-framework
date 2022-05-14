package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateBannedParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：账号设置禁言/解禁用户
 */
public class PolyvAppUpdateBannedRequester extends FunctionOperator<PolyvAppUpdateBannedParameter,
        PolyvAppUpdateBannedParameter.PolyvAppUpdateBannedParameterBuilder,String> {
    public PolyvAppUpdateBannedRequester(Function<PolyvAppUpdateBannedParameter, PolyvApiResult<String>> function) {
        super(PolyvAppUpdateBannedParameter.PolyvAppUpdateBannedParameterBuilder.aPolyvAppUpdateBannedParameter(), function);
    }
}
