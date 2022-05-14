package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelPlayBackBatchAddParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * @author fangzy
 * @description：批量添加点播视频到回放列表
 */
public class PolyvChannelPlayBackBatchAddRequester extends FunctionOperator<PolyvChannelPlayBackBatchAddParameter,
        PolyvChannelPlayBackBatchAddParameter.PolyvChannelPlayBackBatchAddParameterBuilder,String> {
    public PolyvChannelPlayBackBatchAddRequester(Function<PolyvChannelPlayBackBatchAddParameter, PolyvApiResult<String>> function) {
        super(PolyvChannelPlayBackBatchAddParameter.PolyvChannelPlayBackBatchAddParameterBuilder.aPolyvChannelPlayBackBatchAddParameter(), function);
    }
}
