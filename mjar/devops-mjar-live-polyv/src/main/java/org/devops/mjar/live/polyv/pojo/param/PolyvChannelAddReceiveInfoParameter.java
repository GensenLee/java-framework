package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：提交中奖信息
 */
@Data
public class PolyvChannelAddReceiveInfoParameter extends ChannelSignBean {

    /**
     * 抽奖场次ID
     */
    private String lotteryId;

    /**
     * 中奖码
     */
    private String winnerCode;

    /**
     * 中奖者ID
     */
    private String viewerId;

    /**
     * 中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效）
     */
    @VerifyField(ignore = true)
    private String name;

    /**
     * 中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效）
     */
    @VerifyField(ignore = true)
    private String telephone;

    /**
     * 自定义字段数据，数据类型为JSON数组
     * [{"field":"姓名","value":"测试"},{"field":"手机","value":"13412345678"}]
     * field：字段名称
     * value：字段值，如果传这个参数，name和telephone字段不需要传（无效）
     */
    @VerifyField(ignore = true)
    private String receiveInfo;


    public static final class PolyvChannelAddReceiveInfoParameterBuilder extends ParameterBuilder<PolyvChannelAddReceiveInfoParameter> {
        private PolyvChannelAddReceiveInfoParameter polyvChannelAddReceiveInfoParameter;

        private PolyvChannelAddReceiveInfoParameterBuilder() {
            polyvChannelAddReceiveInfoParameter = new PolyvChannelAddReceiveInfoParameter();
        }

        public static PolyvChannelAddReceiveInfoParameterBuilder aPolyvChannelAddReceiveInfoParameter() {
            return new PolyvChannelAddReceiveInfoParameterBuilder();
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withLotteryId(String lotteryId) {
            polyvChannelAddReceiveInfoParameter.setLotteryId(lotteryId);
            return this;
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withWinnerCode(String winnerCode) {
            polyvChannelAddReceiveInfoParameter.setWinnerCode(winnerCode);
            return this;
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withViewerId(String viewerId) {
            polyvChannelAddReceiveInfoParameter.setViewerId(viewerId);
            return this;
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withName(String name) {
            polyvChannelAddReceiveInfoParameter.setName(name);
            return this;
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withTelephone(String telephone) {
            polyvChannelAddReceiveInfoParameter.setTelephone(telephone);
            return this;
        }

        public PolyvChannelAddReceiveInfoParameterBuilder withReceiveInfo(String receiveInfo) {
            polyvChannelAddReceiveInfoParameter.setReceiveInfo(receiveInfo);
            return this;
        }

        @Override
        public PolyvChannelAddReceiveInfoParameter build() {
            return polyvChannelAddReceiveInfoParameter;
        }
    }
}
