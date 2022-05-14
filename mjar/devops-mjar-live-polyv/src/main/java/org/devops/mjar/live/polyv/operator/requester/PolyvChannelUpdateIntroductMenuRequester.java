package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateIntroduceMenuParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 *
 */
public class PolyvChannelUpdateIntroductMenuRequester extends FunctionOperator<PolyvChannelUpdateIntroduceMenuParameter,
        PolyvChannelUpdateIntroduceMenuParameter.PolypChannelUpdateIntroduceMenuParameterBuilder, String> {


    public PolyvChannelUpdateIntroductMenuRequester(Function<PolyvChannelUpdateIntroduceMenuParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateIntroduceMenuParameter.PolypChannelUpdateIntroduceMenuParameterBuilder.aPolyvChannelUpdateIntroductMenuParameter(), function);
    }
}
