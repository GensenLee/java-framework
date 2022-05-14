package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAccount;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBatchCreateAccountParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/27 15:36
 * @description：批量创建子频道
 */
public class PolyvChannelBatchCreateAccountRequester extends FunctionOperator<PolyvChannelBatchCreateAccountParameter,
        PolyvChannelBatchCreateAccountParameter.PolyvChannelBatchCreateAccountParameterBuilder, List<PolyvChannelAccount>> {
    public PolyvChannelBatchCreateAccountRequester( Function<PolyvChannelBatchCreateAccountParameter, PolyvApiResult<List<PolyvChannelAccount>>> function) {
        super(PolyvChannelBatchCreateAccountParameter.PolyvChannelBatchCreateAccountParameterBuilder.aPolyvChannelBatchCreateAccountParameterBuilder(), function);
    }
}
