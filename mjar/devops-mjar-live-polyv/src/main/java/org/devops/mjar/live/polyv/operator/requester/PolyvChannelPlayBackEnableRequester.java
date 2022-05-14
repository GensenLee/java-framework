package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelNoneParamParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/3 15:25
 * @description：查询频道的回放开关状态
 */
public class PolyvChannelPlayBackEnableRequester extends FunctionOperator<PolyvChannelNoneParamParameter,
        PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder, EnableSetting> {
    public PolyvChannelPlayBackEnableRequester(Function<PolyvChannelNoneParamParameter, PolyvApiResult<EnableSetting>> function) {
        super(PolyvChannelNoneParamParameter.PolyvChannelNoneParamReqParameterBuilder.aPolyvChannelNoneParamParameter(), function);
    }
}
