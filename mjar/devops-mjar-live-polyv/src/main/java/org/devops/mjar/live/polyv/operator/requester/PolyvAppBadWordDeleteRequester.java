package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppBadWordDeleteParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：删除账号禁言词
 */
public class PolyvAppBadWordDeleteRequester extends FunctionOperator<PolyvAppBadWordDeleteParameter,
        PolyvAppBadWordDeleteParameter.PolyvAppBadWordDeleteParameterBuilder, List<String>> {
    public PolyvAppBadWordDeleteRequester(Function<PolyvAppBadWordDeleteParameter, PolyvApiResult<List<String>>> function) {
        super(PolyvAppBadWordDeleteParameter.PolyvAppBadWordDeleteParameterBuilder.aPolyvAppBadWordDeleteParameter(), function);
    }
}
