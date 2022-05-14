package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDeleteDiskVideoParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelDeleteDiskVideoRequester extends FunctionOperator<PolyvChannelDeleteDiskVideoParameter,
        PolyvChannelDeleteDiskVideoParameter.PolyvChannelDeleteDiskVideoParameterBuilder,String> {
    public PolyvChannelDeleteDiskVideoRequester( Function<PolyvChannelDeleteDiskVideoParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelDeleteDiskVideoParameter.PolyvChannelDeleteDiskVideoParameterBuilder.aPolyvChannelDeleteDiskVideoParameter(), function);
    }
}
