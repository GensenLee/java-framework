package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDecorateUpdateV4Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/9/6 23:20
 * @description：观看页引导设置
 */
public class PolyvChannelDecorateUpdateV4Requester extends FunctionOperator<PolyvChannelDecorateUpdateV4Parameter,
        PolyvChannelDecorateUpdateV4Parameter.PolyvChannelDecorateUpdateV4ParameterBuilder, Object> {
    public PolyvChannelDecorateUpdateV4Requester(Function<PolyvChannelDecorateUpdateV4Parameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelDecorateUpdateV4Parameter.PolyvChannelDecorateUpdateV4ParameterBuilder.aPolyvChannelDecorateUpdateV4Parameter(), function);
    }
}
