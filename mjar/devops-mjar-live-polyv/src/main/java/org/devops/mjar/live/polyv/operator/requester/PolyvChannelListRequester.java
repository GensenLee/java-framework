package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelIdList;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppNoneParamParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：查询账号下所有频道请求
 */
public class PolyvChannelListRequester extends FunctionOperator<PolyvAppNoneParamParameter,
        PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder, PolyvChannelIdList> {

    public PolyvChannelListRequester(Function<PolyvAppNoneParamParameter, PolyvApiResult<PolyvChannelIdList>> function) {
        super(PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder.aPolyvAppNoneParamParameter(), function);
    }
}
