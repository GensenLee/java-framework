package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateMenuInfoParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;
import io.swagger.annotations.ApiModel;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：修改页面菜单信息
 */
@ApiModel("修改页面菜单信息")
public class PolyvChannelUpdateMenuInfoRequester extends FunctionOperator<PolyvChannelUpdateMenuInfoParameter,
        PolyvChannelUpdateMenuInfoParameter.PolyvChannelUpdateMenuInfoParameterBuilder,String> {


    public PolyvChannelUpdateMenuInfoRequester(Function<PolyvChannelUpdateMenuInfoParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateMenuInfoParameter.PolyvChannelUpdateMenuInfoParameterBuilder.aPolyvChannelUpdateMenuInfoParameter(), function);
    }
}
