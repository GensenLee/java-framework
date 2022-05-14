package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannel;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：频道创建请求
 */
public class PolyvChannelCreateRequester extends FunctionOperator<PolyvChannelCreateParameter,
        PolyvChannelCreateParameter.PolyvChannelCreateParameterBuilder, PolyvChannel> {

    public PolyvChannelCreateRequester(Function<PolyvChannelCreateParameter,
            PolyvApiResult<PolyvChannel>> function) {
        super(PolyvChannelCreateParameter.PolyvChannelCreateParameterBuilder.aPolyvChannelCreateReqParameter(), function);
    }
}
