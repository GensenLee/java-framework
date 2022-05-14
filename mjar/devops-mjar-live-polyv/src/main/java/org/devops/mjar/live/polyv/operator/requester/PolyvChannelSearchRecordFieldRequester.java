package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthSetting;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelSearchRecordFieldParameter;
import org.devops.mjar.live.core.model.PolyvApiResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author bigboss
 * @date 2021/7/26 16:20
 * @description：查询频道登记观看设置的字段信息
 */
public class PolyvChannelSearchRecordFieldRequester extends FunctionOperator<PolyvChannelSearchRecordFieldParameter,
        PolyvChannelSearchRecordFieldParameter.PolyvChannelSearchRecordFieldParameterBuilder, List<PolyvChannelAuthSetting.InfoField> > {
    public PolyvChannelSearchRecordFieldRequester( Function<PolyvChannelSearchRecordFieldParameter, PolyvApiResult<List<PolyvChannelAuthSetting.InfoField>>> function) {
        super(PolyvChannelSearchRecordFieldParameter.PolyvChannelSearchRecordFieldParameterBuilder.aPolyvChannelSearchRecordFieldParameter(), function);
    }
}
