package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateMenuRankParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;
import io.swagger.annotations.ApiModel;

import java.util.function.Function;

/**
 * @author bigboss
 * @description：修改页面菜单排序
 */
@ApiModel("修改页面菜单排序")
public class PolyvChannelUpdateMenuRankRequester extends FunctionOperator<PolyvChannelUpdateMenuRankParameter,
        PolyvChannelUpdateMenuRankParameter.PolyvChannelUpdateMenuRankParameterBuilder,String> {
    public PolyvChannelUpdateMenuRankRequester( Function<PolyvChannelUpdateMenuRankParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelUpdateMenuRankParameter.PolyvChannelUpdateMenuRankParameterBuilder.aPolyvChannelUpdateMenuRankParameter(), function);
    }
}
