package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelStatistics;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppStatisticsSummaryParameter;
import org.devops.mjar.live.core.constant.MjarLiveConstant;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author GENSEN
 * @date 2020/10/30 11:13
 * @description：查询多个频道汇总的统计数据
 */
public class PolyvAppStatisticsSummaryRequester extends FunctionOperator<PolyvAppStatisticsSummaryParameter,
        PolyvAppStatisticsSummaryParameter.PolypAppStatisticsSummaryParameterBuilder, List<PolyvChannelStatistics>> {

    public PolyvAppStatisticsSummaryRequester(BiFunction<PolyvAppStatisticsSummaryParameter, String, PolyvApiResult<List<PolyvChannelStatistics>>> function) {
        super(PolyvAppStatisticsSummaryParameter.PolypAppStatisticsSummaryParameterBuilder.aPolypAppStatisticsSummaryParameter(), function, MjarLiveConstant.Key.USER_ID_KEY);
    }
}
