package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthExternal;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAuthExternalParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author bigboss
 * @date 2021/7/27 14:11
 * @description：修改频道外部授权设置
 */
public class PolyvChannelUpdateAuthExternalRequester extends FunctionOperator<PolyvChannelUpdateAuthExternalParameter,
        PolyvChannelUpdateAuthExternalParameter.PolyvChannelUpdateAuthExternalParameterBuilder, List<PolyvChannelAuthExternal>> {
    public PolyvChannelUpdateAuthExternalRequester(BiFunction<PolyvChannelUpdateAuthExternalParameter, String, PolyvApiResult<List<PolyvChannelAuthExternal>>> biFunction) {
        super(PolyvChannelUpdateAuthExternalParameter.PolyvChannelUpdateAuthExternalParameterBuilder.aPolyvChannelUpdateAuthExternalParameter(), biFunction, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
