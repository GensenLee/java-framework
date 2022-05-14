package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvBanned;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppBannedListParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author fangzy
 * @description：查询账号禁言列表
 */
public class PolyvAppBannedListRequester extends FunctionOperator<PolyvAppBannedListParameter,
        PolyvAppBannedListParameter.PolyvAppBannedListParameterBuilder, List<PolyvBanned>> {
    public PolyvAppBannedListRequester(Function<PolyvAppBannedListParameter, PolyvApiResult<List<PolyvBanned>>> function) {
        super(PolyvAppBannedListParameter.PolyvAppBannedListParameterBuilder.aPolyvAppBannedListParameter(), function);
    }
}
