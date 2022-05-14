package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdatePptRecordSettingParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

/**
 * 更新频道重制课件设置
 */
public class PolyvChannelUpdatePptRecordSettingRequester extends FunctionOperator<PolyvChannelUpdatePptRecordSettingParameter,
        PolyvChannelUpdatePptRecordSettingParameter.PolyvChannelUpdatePptRecordSettingParameterBuilder,Object> {

    public PolyvChannelUpdatePptRecordSettingRequester( Function<PolyvChannelUpdatePptRecordSettingParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdatePptRecordSettingParameter.PolyvChannelUpdatePptRecordSettingParameterBuilder.aPolyvChannelUpdatePptRecordSettingParameter(), function);
    }
}
