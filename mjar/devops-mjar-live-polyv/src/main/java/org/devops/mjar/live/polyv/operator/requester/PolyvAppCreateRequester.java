package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvApp;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppCreateParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/12/16 17:15
 * @description：创建应用请求
 */
public class PolyvAppCreateRequester extends FunctionOperator<PolyvAppCreateParameter,
        PolyvAppCreateParameter.PolyvAppCreateParameterBuilder, PolyvApp> {
    public PolyvAppCreateRequester(Function<PolyvAppCreateParameter, PolyvApiResult<PolyvApp>> function) {
        super(PolyvAppCreateParameter.PolyvAppCreateParameterBuilder.aPolyvAppCreateParameter(), function);
    }
}
