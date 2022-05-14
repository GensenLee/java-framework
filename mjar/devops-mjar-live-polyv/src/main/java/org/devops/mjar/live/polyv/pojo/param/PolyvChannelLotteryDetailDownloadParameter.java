package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/21 12:15
 * @description：下载频道场次中奖记录
 */
@Data
public class PolyvChannelLotteryDetailDownloadParameter extends ChannelSignBean {

    /**
     * 抽奖场次id
     */
    @VerifyField(ignore = true)
    private String lotteryId;

    public static final class PolyvChannelLotteryDetailDownloadParameterBuilder extends ParameterBuilder<PolyvChannelLotteryDetailDownloadParameter> {
        private PolyvChannelLotteryDetailDownloadParameter polyvChannelLotteryDetailDownloadParameter;

        private PolyvChannelLotteryDetailDownloadParameterBuilder() {
            polyvChannelLotteryDetailDownloadParameter = new PolyvChannelLotteryDetailDownloadParameter();
        }

        public static PolyvChannelLotteryDetailDownloadParameterBuilder aPolyvChannelLotteryDetailDownloadParameter() {
            return new PolyvChannelLotteryDetailDownloadParameterBuilder();
        }

        public PolyvChannelLotteryDetailDownloadParameterBuilder withLotteryId(String lotteryId) {
            polyvChannelLotteryDetailDownloadParameter.setLotteryId(lotteryId);
            return this;
        }

        @Override
        public PolyvChannelLotteryDetailDownloadParameter build() {
            return polyvChannelLotteryDetailDownloadParameter;
        }
    }
}
