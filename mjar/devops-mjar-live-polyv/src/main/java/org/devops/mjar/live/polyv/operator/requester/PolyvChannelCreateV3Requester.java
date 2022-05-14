package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelV3;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateV3Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/07/12 11:13
 * @description：频道创建请求
 */
public class PolyvChannelCreateV3Requester extends FunctionOperator<PolyvChannelCreateV3Parameter,
        PolyvChannelCreateV3Parameter.PolyvChannelCreateV3ParameterBuilder, PolyvChannelV3> {

    public PolyvChannelCreateV3Requester(Function<PolyvChannelCreateV3Parameter,PolyvApiResult<PolyvChannelV3>> function) {
        super(PolyvChannelCreateV3Parameter.PolyvChannelCreateV3ParameterBuilder.aPolyvChannelCreateV3Parameter(), function);
    }
}
