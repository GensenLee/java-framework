package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 10:32
 * @description：查询频道直播观看详情数据
 */
@Data
public class PolyvChannelStatisticsViewLogParameter extends ChannelSignBean {

    /**
     * 查询日期，格式：yyyy-MM-dd
     */
    private String currentDay;

    /**
     * 直播账号id
     */
    private String userId;

    /**
     * 观看用户ID，默认与直播账号ID一致
     */
    private String param1;


    public static final class PolyvChannelStatisticsViewLogParamterBuilder extends ParameterBuilder<PolyvChannelStatisticsViewLogParameter> {
        private PolyvChannelStatisticsViewLogParameter polyvChannelStatisticsViewLogParameter;

        private PolyvChannelStatisticsViewLogParamterBuilder() {
            polyvChannelStatisticsViewLogParameter = new PolyvChannelStatisticsViewLogParameter();
        }

        public static PolyvChannelStatisticsViewLogParamterBuilder aPolyvChannelStatisticsViewLogParamter() {
            return new PolyvChannelStatisticsViewLogParamterBuilder();
        }

        public PolyvChannelStatisticsViewLogParamterBuilder withCurrentDay(String currentDay) {
            polyvChannelStatisticsViewLogParameter.setCurrentDay(currentDay);
            return this;
        }

        public PolyvChannelStatisticsViewLogParamterBuilder withUserId(String userId) {
            polyvChannelStatisticsViewLogParameter.setUserId(userId);
            return this;
        }

        public PolyvChannelStatisticsViewLogParamterBuilder withParam1(String param1) {
            polyvChannelStatisticsViewLogParameter.setParam1(param1);
            return this;
        }

        @Override
        public PolyvChannelStatisticsViewLogParameter build() {
            return polyvChannelStatisticsViewLogParameter;
        }
    }
}
