package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateV3Parameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/07/12 11:13
 * @description：频道创建请求
 */
public class PolyvChannelUpdateV3Requester extends FunctionOperator<PolyvChannelUpdateV3Parameter,
        PolyvChannelUpdateV3Parameter.PolyvChannelUpdateV3ParameterBuilder, Object> {

    public PolyvChannelUpdateV3Requester(Function<PolyvChannelUpdateV3Parameter,PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdateV3Parameter.PolyvChannelUpdateV3ParameterBuilder.aPolyvChannelUpdateV3Parameter(), function);
    }
}
