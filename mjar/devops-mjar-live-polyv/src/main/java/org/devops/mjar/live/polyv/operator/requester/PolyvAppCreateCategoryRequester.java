package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvCategory;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppCreateCategoryParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：创建直播分类
 */
public class PolyvAppCreateCategoryRequester extends FunctionOperator<PolyvAppCreateCategoryParameter,
        PolyvAppCreateCategoryParameter.PolyvAppCreateCategoryParameterBuilder, PolyvCategory> {
    public PolyvAppCreateCategoryRequester(Function<PolyvAppCreateCategoryParameter, PolyvApiResult<PolyvCategory>> function) {
        super(PolyvAppCreateCategoryParameter.PolyvAppCreateCategoryParameterBuilder.aPolyvAppCreateCategoryParameter(), function);
    }
}
