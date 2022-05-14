package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 11:15
 * @description：查询频道多场次概览统计数据
 */

@Data
public class PolyvChannelStaticistGetSessionStatsParameter extends ChannelSignBean {


    /**
     *  直播结束时间，13位毫秒级时间戳，场次ID和直播开始结束时间必填一项
     */
    @VerifyField(ignore = true)
    private String startTime;


    /**
     * 直播开始时间，13位毫秒级时间戳，开始时间和结束时间相隔不可以超过30天
     */
    @VerifyField(ignore = true)
    private String endTime;


    /**
     * 场次ID，多个场次使用逗号分隔
     */
    @VerifyField(ignore = true)
    private String sessionIds;


    public static final class PolyvChannelStaticistGetSessionStatsBuilder extends ParameterBuilder<PolyvChannelStaticistGetSessionStatsParameter> {
        private PolyvChannelStaticistGetSessionStatsParameter polyvChannelStaticistGetSessionStats;

        private PolyvChannelStaticistGetSessionStatsBuilder() {
            polyvChannelStaticistGetSessionStats = new PolyvChannelStaticistGetSessionStatsParameter();
        }

        public static PolyvChannelStaticistGetSessionStatsBuilder aPolyvChannelStaticistGetSessionStats() {
            return new PolyvChannelStaticistGetSessionStatsBuilder();
        }

        public PolyvChannelStaticistGetSessionStatsBuilder withStartTime(String startTime) {
            polyvChannelStaticistGetSessionStats.setStartTime(startTime);
            return this;
        }

        public PolyvChannelStaticistGetSessionStatsBuilder withEndTime(String endTime) {
            polyvChannelStaticistGetSessionStats.setEndTime(endTime);
            return this;
        }

        public PolyvChannelStaticistGetSessionStatsBuilder withSessionIds(String sessionIds) {
            polyvChannelStaticistGetSessionStats.setSessionIds(sessionIds);
            return this;
        }

        @Override
        public PolyvChannelStaticistGetSessionStatsParameter build() {
            return polyvChannelStaticistGetSessionStats;
        }
    }
}
