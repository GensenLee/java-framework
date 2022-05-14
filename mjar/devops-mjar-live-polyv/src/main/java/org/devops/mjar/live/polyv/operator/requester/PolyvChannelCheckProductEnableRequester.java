package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelProductEnabled;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCheckProductEnableParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 17:25
 * @description：查询频道商品库开关状态
 */
public class PolyvChannelCheckProductEnableRequester extends FunctionOperator<PolyvChannelCheckProductEnableParameter,
        PolyvChannelCheckProductEnableParameter.PolyvChannelCheckProductEnableParameterBuilder, PolyvChannelProductEnabled> {
    public PolyvChannelCheckProductEnableRequester(Function<PolyvChannelCheckProductEnableParameter, PolyvApiResult<PolyvChannelProductEnabled>> function) {
        super(PolyvChannelCheckProductEnableParameter.PolyvChannelCheckProductEnableParameterBuilder.aPolyvChannelCheckProductEnableParameter(), function);
    }
}
