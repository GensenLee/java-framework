package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateCategoryRankParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author bigboss
 * @date 2020/07/22 18:13
 * @description：修改直播分类顺序
 */
public class PolyvAppUpdateCategoryRankRequester extends FunctionOperator<PolyvAppUpdateCategoryRankParameter,
        PolyvAppUpdateCategoryRankParameter.PolyvAppUpdateCategoryRankParameterBuilder,String> {
    public PolyvAppUpdateCategoryRankRequester(Function<PolyvAppUpdateCategoryRankParameter, PolyvApiResult<String>> function) {
        super(PolyvAppUpdateCategoryRankParameter.PolyvAppUpdateCategoryRankParameterBuilder.aPolyvAppUpdateCategoryRankParameter(), function);
    }
}
