package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateMenuConsultParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;
import io.swagger.annotations.ApiModel;

import java.util.function.BiFunction;

/**
 * @author bigboss
 * @description：修改提问功能开关
 */
@ApiModel("修改提问功能开关")
public class PolyvChannelUpdateMenuConsultRequester extends FunctionOperator<PolyvChannelUpdateMenuConsultParameter,
        PolyvChannelUpdateMenuConsultParameter.PolyvChannelUpdateMenuConsultParameterBuilder,Object> {
    public PolyvChannelUpdateMenuConsultRequester(BiFunction<PolyvChannelUpdateMenuConsultParameter, String, PolyvApiResult<Object>> biFunction) {
        super(PolyvChannelUpdateMenuConsultParameter.PolyvChannelUpdateMenuConsultParameterBuilder.aPolyvChannelUpdateMenuConsultParameter(), biFunction, MjarLiveConstant.Key.CHANNEL_ID_KEY);
    }
}
