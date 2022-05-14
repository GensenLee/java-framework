package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteMenuParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 11:46
 * @description：删除频道菜单
 */
public class PolyvChannelDeleteMenuRequester extends FunctionOperator<PolyvChannelDeleteMenuParameter,
        PolyvChannelDeleteMenuParameter.PolyvChannelDeletrMenuParameterBuilder,String> {

    public PolyvChannelDeleteMenuRequester(Function<PolyvChannelDeleteMenuParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelDeleteMenuParameter.PolyvChannelDeletrMenuParameterBuilder.aPolyvChannelDeletrMenuParameter(), function);
    }
}
