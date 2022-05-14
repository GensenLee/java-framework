package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelBadWordsListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询频道严禁词/禁言ip
 */
public class PolyvChannelBadWordsListRequester extends FunctionOperator<PolyvChannelBadWordsListParameter,
        PolyvChannelBadWordsListParameter.PolyvChannelBadWordsListParameterBuilder, List<String>> {
    public PolyvChannelBadWordsListRequester(Function<PolyvChannelBadWordsListParameter, PolyvApiResult<List<String>>> function) {
        super(PolyvChannelBadWordsListParameter.PolyvChannelBadWordsListParameterBuilder.aPolyvChannelBadWordsListParameter(), function);
    }
}
