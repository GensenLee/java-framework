package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvDonatePointSettingModel;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppChannelDonatePointSettingParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2021/3/5 11:50
 * @description：打赏设置查询
 */
public class PolyvAppChannelDonatePointSettingRequester extends FunctionOperator<PolyvAppChannelDonatePointSettingParameter,
        PolyvAppChannelDonatePointSettingParameter.PolyvAppChannelDonatePointSettingParameterBuilder, PolyvDonatePointSettingModel> {
    public PolyvAppChannelDonatePointSettingRequester(Function<PolyvAppChannelDonatePointSettingParameter, PolyvApiResult<PolyvDonatePointSettingModel>> function) {
        super(PolyvAppChannelDonatePointSettingParameter.PolyvAppChannelDonatePointSettingParameterBuilder.aPolyvAppChannelDonatePointSettingParameter(), function);
    }
}
