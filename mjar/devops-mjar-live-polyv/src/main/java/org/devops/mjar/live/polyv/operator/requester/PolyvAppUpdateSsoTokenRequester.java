package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateSsoTokenParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：设置账号单点登录token
 */
public class PolyvAppUpdateSsoTokenRequester extends FunctionOperator<PolyvAppUpdateSsoTokenParameter,
        PolyvAppUpdateSsoTokenParameter.PolyvAppUpdateSsoTokenParameterBuilder,Object> {
    public PolyvAppUpdateSsoTokenRequester(Function<PolyvAppUpdateSsoTokenParameter, PolyvApiResult<Object>> function) {
        super(PolyvAppUpdateSsoTokenParameter.PolyvAppUpdateSsoTokenParameterBuilder.aPolyvAppUpdateSsoTokenParameter(), function);
    }
}
