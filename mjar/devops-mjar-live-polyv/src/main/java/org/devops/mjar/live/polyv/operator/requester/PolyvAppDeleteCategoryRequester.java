package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppDeleteCategoryParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/22 17:47
 * @description：删除直播分类
 */
public class PolyvAppDeleteCategoryRequester extends FunctionOperator<PolyvAppDeleteCategoryParameter,
        PolyvAppDeleteCategoryParameter.PolyvAppDeleteCategoryParameterBuilder,String> {
    public PolyvAppDeleteCategoryRequester(Function<PolyvAppDeleteCategoryParameter, PolyvApiResult<String>> function) {
        super(PolyvAppDeleteCategoryParameter.PolyvAppDeleteCategoryParameterBuilder.aPolyvAppDeleteCategoryParameter(), function);
    }
}
