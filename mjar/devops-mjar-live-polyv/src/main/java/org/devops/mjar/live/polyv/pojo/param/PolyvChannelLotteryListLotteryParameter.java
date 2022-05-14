package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 17:15
 * @description：查询频道中奖记录
 */
@Data
public class PolyvChannelLotteryListLotteryParameter extends ChannelSignBean {

    /**
     * 查询的开始日期的13位时间戳
     */
    @VerifyField(ignore = true)
    private Long startTime;

    /**
     * 查询的结束日期的13位时间戳
     */
    @VerifyField(ignore = true)
    private Long endTime;

    /**
     * 起始下标，从0开始
     */
    @VerifyField(ignore = true)
    private Integer page;
    /**
     * 结束下标，-1表示不分页
     */
    @VerifyField(ignore = true)
    private Integer limit;

    /**
     * 要查询的直播场次ID，默认查询该频道中所有场次
     */
    @VerifyField(ignore = true)
    private String sessionId;

    public static final class PolyvChannelLotteryListLotteryParameterBuilder extends ParameterBuilder<PolyvChannelLotteryListLotteryParameter> {
        private PolyvChannelLotteryListLotteryParameter polyvChannelLotteryListLotteryParameter;

        private PolyvChannelLotteryListLotteryParameterBuilder() {
            polyvChannelLotteryListLotteryParameter = new PolyvChannelLotteryListLotteryParameter();
        }

        public static PolyvChannelLotteryListLotteryParameterBuilder aPolyvChannelLotteryListLotteryParameter() {
            return new PolyvChannelLotteryListLotteryParameterBuilder();
        }

        public PolyvChannelLotteryListLotteryParameterBuilder withStartTime(Long startTime) {
            polyvChannelLotteryListLotteryParameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelLotteryListLotteryParameterBuilder withEndTime(Long endTime) {
            polyvChannelLotteryListLotteryParameter.setEndTime(endTime);
            return this;
        }

        public PolyvChannelLotteryListLotteryParameterBuilder withPage(Integer page) {
            polyvChannelLotteryListLotteryParameter.setPage(page);
            return this;
        }

        public PolyvChannelLotteryListLotteryParameterBuilder withLimit(Integer limit) {
            polyvChannelLotteryListLotteryParameter.setLimit(limit);
            return this;
        }

        public PolyvChannelLotteryListLotteryParameterBuilder withSessionId(String sessionId) {
            polyvChannelLotteryListLotteryParameter.setSessionId(sessionId);
            return this;
        }

        @Override
        public PolyvChannelLotteryListLotteryParameter build() {
            return polyvChannelLotteryListLotteryParameter;
        }
    }
}
