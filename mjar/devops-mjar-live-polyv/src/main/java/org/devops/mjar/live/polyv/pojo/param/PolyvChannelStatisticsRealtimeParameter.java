package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 10:32
 * @description：查询频道实时并发数据
 */
@Data
public class PolyvChannelStatisticsRealtimeParameter extends ChannelSignBean {

    public static final class PolyvChannelStatisticsRealtimeParameterBuilder extends ParameterBuilder<PolyvChannelStatisticsRealtimeParameter> {
        private PolyvChannelStatisticsRealtimeParameter PolyvChannelStatisticsRealtimeParameter;

        private PolyvChannelStatisticsRealtimeParameterBuilder() {
            PolyvChannelStatisticsRealtimeParameter = new PolyvChannelStatisticsRealtimeParameter();
        }

        public static PolyvChannelStatisticsRealtimeParameterBuilder aPolyvChannelStatisticsRealtimeParameter() {
            return new PolyvChannelStatisticsRealtimeParameterBuilder();
        }

        @Override
        public PolyvChannelStatisticsRealtimeParameter build() {
            return PolyvChannelStatisticsRealtimeParameter;
        }
    }
}
