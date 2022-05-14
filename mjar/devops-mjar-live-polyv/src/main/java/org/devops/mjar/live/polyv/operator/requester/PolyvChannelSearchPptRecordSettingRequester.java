package org.devops.mjar.live.polyv.operator.requester;


import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvPptRecordSetting;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchPptRecordSettingParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.function.Function;

public class PolyvChannelSearchPptRecordSettingRequester extends FunctionOperator<PolyvChannelSearchPptRecordSettingParameter,
        PolyvChannelSearchPptRecordSettingParameter.PolyvChannelSearchPptRecordSettingParameterBuilder, PolyvPptRecordSetting> {
    public PolyvChannelSearchPptRecordSettingRequester(Function<PolyvChannelSearchPptRecordSettingParameter, PolyvApiResult<PolyvPptRecordSetting>> function) {
        super(PolyvChannelSearchPptRecordSettingParameter.PolyvChannelSearchPptRecordSettingParameterBuilder.aPolyvChannelSearchPptRecordSettingParameter(), function);
    }
}
