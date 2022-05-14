package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppNoneParamParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询账号禁言词
 */
public class PolyvAppBadWordListRequester extends FunctionOperator<PolyvAppNoneParamParameter,
        PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder, List<String>> {
    public PolyvAppBadWordListRequester(Function<PolyvAppNoneParamParameter, PolyvApiResult<List<String>>> function) {
        super(PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder.aPolyvAppNoneParamParameter(), function);
    }
}
