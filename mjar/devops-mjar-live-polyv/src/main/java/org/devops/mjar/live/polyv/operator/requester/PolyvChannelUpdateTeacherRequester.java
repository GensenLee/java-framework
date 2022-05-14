package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateTeacherParameter;

import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/11/20 23:20
 * @description：讲师更新请求
 */
public class PolyvChannelUpdateTeacherRequester extends FunctionOperator<PolyvChannelUpdateTeacherParameter,
        PolyvChannelUpdateTeacherParameter.PolyvChannelUpdateTeacherParameterBuilder, Object> {

    public PolyvChannelUpdateTeacherRequester(Function<PolyvChannelUpdateTeacherParameter, PolyvApiResult<Object>> function) {
        super(PolyvChannelUpdateTeacherParameter.PolyvChannelUpdateTeacherParameterBuilder.aPolyvChannelUpdateTeacherParameter(), function);
    }
}
