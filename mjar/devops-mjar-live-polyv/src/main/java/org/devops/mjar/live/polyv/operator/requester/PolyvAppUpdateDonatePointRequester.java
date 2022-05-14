package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateDonatePointParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/5 11:21
 * @description：打赏设置更新
 */
public class PolyvAppUpdateDonatePointRequester extends FunctionOperator<PolyvAppUpdateDonatePointParameter,
        PolyvAppUpdateDonatePointParameter.PolyvAppUpdateDonatePointParameterBuilder, Object> {
    public PolyvAppUpdateDonatePointRequester(Function<PolyvAppUpdateDonatePointParameter, PolyvApiResult<Object>> function) {
        super(PolyvAppUpdateDonatePointParameter.PolyvAppUpdateDonatePointParameterBuilder.aPolyvAppUpdateDonatePointParameter(), function);
    }
}
