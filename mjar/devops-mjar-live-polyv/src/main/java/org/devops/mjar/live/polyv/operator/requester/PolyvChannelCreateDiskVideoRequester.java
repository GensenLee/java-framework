package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelCreateDiskVideoParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 14:50
 * @description：设置伪直播
 */
public class PolyvChannelCreateDiskVideoRequester extends FunctionOperator<PolyvChannelCreateDiskVideoParameter,
        PolyvChannelCreateDiskVideoParameter.PolyvChannelCreateDiskVideoParameterBuilder,String> {
    public PolyvChannelCreateDiskVideoRequester( Function<PolyvChannelCreateDiskVideoParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelCreateDiskVideoParameter.PolyvChannelCreateDiskVideoParameterBuilder.aPolyvChannelCreateDiskVideoParameter(), function);
    }
}
