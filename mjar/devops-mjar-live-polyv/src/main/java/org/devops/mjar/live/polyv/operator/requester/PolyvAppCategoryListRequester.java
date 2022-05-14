package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCategory;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppNoneParamParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询直播分类
 */
public class PolyvAppCategoryListRequester extends FunctionOperator<PolyvAppNoneParamParameter,
        PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder, List<PolyvCategory>> {
    public PolyvAppCategoryListRequester(Function<PolyvAppNoneParamParameter, PolyvApiResult<List<PolyvCategory>>> function) {
        super(PolyvAppNoneParamParameter.PolyvAppNoneParamParameterBuilder.aPolyvAppNoneParamParameter(), function);
    }
}
