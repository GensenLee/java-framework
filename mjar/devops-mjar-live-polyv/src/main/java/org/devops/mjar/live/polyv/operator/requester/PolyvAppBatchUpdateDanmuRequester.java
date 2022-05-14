package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppBatchUpdateDanmuParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量修改频道弹幕开关
 */
public class PolyvAppBatchUpdateDanmuRequester extends FunctionOperator<PolyvAppBatchUpdateDanmuParameter,
        PolyvAppBatchUpdateDanmuParameter.PolyvAppBatchUpdateDanmuParameterBuilder,String> {
    public PolyvAppBatchUpdateDanmuRequester(Function<PolyvAppBatchUpdateDanmuParameter, PolyvApiResult<String>> function) {
        super(PolyvAppBatchUpdateDanmuParameter.PolyvAppBatchUpdateDanmuParameterBuilder.aPolyvAppBatchUpdateDanmuParameter(), function);
    }
}
