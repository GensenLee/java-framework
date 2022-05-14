package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBannedListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道禁言用户Userid/IP
 */
public class PolyvChannelBannedListRequester extends FunctionOperator<PolyvChannelBannedListParameter,
        PolyvChannelBannedListParameter.PolyvChannelBannedListParameterBuilder, List<String>> {
    public PolyvChannelBannedListRequester(Function<PolyvChannelBannedListParameter, PolyvApiResult<List<String>>> function) {
        super(PolyvChannelBannedListParameter.PolyvChannelBannedListParameterBuilder.aPolyvChannelBannedListParameter(), function);
    }
}
