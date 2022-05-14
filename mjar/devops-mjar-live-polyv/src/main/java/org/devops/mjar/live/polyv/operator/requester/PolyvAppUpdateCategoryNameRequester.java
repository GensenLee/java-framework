package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateCategoryNameParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2020/07/22 18:13
 * @description：修改直播分类名称
 */
public class PolyvAppUpdateCategoryNameRequester extends FunctionOperator<PolyvAppUpdateCategoryNameParameter,
        PolyvAppUpdateCategoryNameParameter.PolyvAppUpdateCategoryNameParameterBuilder,String> {
    public PolyvAppUpdateCategoryNameRequester( Function<PolyvAppUpdateCategoryNameParameter, PolyvApiResult<String>> function) {
        super( PolyvAppUpdateCategoryNameParameter.PolyvAppUpdateCategoryNameParameterBuilder.aPolyvAppUpdateCategoryNameParameter() , function);
    }
}
