package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 18:15
 * @description：查询频道中奖记录
 */
@Data
public class PolyvChannelLotteryGetWinDetailParameter extends ChannelSignBean {


    /**
     * 抽奖场次id
     */
    @VerifyField(ignore = true)
    private String lotteryId;

    /**
     * 起始下标，从0开始
     */
    @VerifyField(ignore = true)
    private Integer page;
    /**
     *  结束下标，-1表示不分页
     */
    @VerifyField(ignore = true)
    private Integer limit;

    public static final class PolyvChannelLotteryGetWinDetailParameterBuilder extends ParameterBuilder<PolyvChannelLotteryGetWinDetailParameter> {
        private PolyvChannelLotteryGetWinDetailParameter polyvChannelLotteryGetWinDetailParameter;

        private PolyvChannelLotteryGetWinDetailParameterBuilder() {
            polyvChannelLotteryGetWinDetailParameter = new PolyvChannelLotteryGetWinDetailParameter();
        }

        public static PolyvChannelLotteryGetWinDetailParameterBuilder aPolyvChannelLotteryGetWinDetailParameter() {
            return new PolyvChannelLotteryGetWinDetailParameterBuilder();
        }

        public PolyvChannelLotteryGetWinDetailParameterBuilder withLotteryId(String lotteryId) {
            polyvChannelLotteryGetWinDetailParameter.setLotteryId(lotteryId);
            return this;
        }

        public PolyvChannelLotteryGetWinDetailParameterBuilder withPage(Integer page) {
            polyvChannelLotteryGetWinDetailParameter.setPage(page);
            return this;
        }

        public PolyvChannelLotteryGetWinDetailParameterBuilder withLimit(Integer limit) {
            polyvChannelLotteryGetWinDetailParameter.setLimit(limit);
            return this;
        }


        @Override
        public PolyvChannelLotteryGetWinDetailParameter build() {
            return polyvChannelLotteryGetWinDetailParameter;
        }
    }
}
