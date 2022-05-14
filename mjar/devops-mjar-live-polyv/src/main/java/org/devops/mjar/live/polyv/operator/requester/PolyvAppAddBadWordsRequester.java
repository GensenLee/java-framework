package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvBadWords;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppAddBadWordsParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author fangzy
 * @description：批量导入禁言词
 */
public class PolyvAppAddBadWordsRequester extends FunctionOperator<PolyvAppAddBadWordsParameter,
        PolyvAppAddBadWordsParameter.PolyvAppAddBadWordsParameterBuilder, List<PolyvBadWords>> {
    public PolyvAppAddBadWordsRequester(BiFunction<PolyvAppAddBadWordsParameter, String, PolyvApiResult<List<PolyvBadWords>>> function) {
        super(PolyvAppAddBadWordsParameter.PolyvAppAddBadWordsParameterBuilder.aPolyvAppAddBadWordsParameter(), function, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
