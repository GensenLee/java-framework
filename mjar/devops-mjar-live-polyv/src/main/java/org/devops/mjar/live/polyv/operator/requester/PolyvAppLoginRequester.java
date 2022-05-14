package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateSsoTokenParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：
 */
public class PolyvAppLoginRequester extends FunctionOperator<PolyvAppUpdateSsoTokenParameter,
        PolyvAppUpdateSsoTokenParameter.PolyvAppUpdateSsoTokenParameterBuilder, PolyvUrl> {
    public PolyvAppLoginRequester(Function<PolyvAppUpdateSsoTokenParameter, PolyvApiResult<PolyvUrl>> function) {
        super(PolyvAppUpdateSsoTokenParameter.PolyvAppUpdateSsoTokenParameterBuilder.aPolyvAppUpdateSsoTokenParameter(), function);
    }
}
