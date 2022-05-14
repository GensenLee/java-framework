package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author fangzy
 * @description：设置现金打赏
 */
@Data
public class PolyvChannelUpdateDonateCashParameter extends ChannelSignBean {

    @SignIgnore
    PolyvChannelUpdateDonateCashParameterBody body = new PolyvChannelUpdateDonateCashParameterBody();

    @Data
    public static class PolyvChannelUpdateDonateCashParameterBody extends BaseBean {

        /**
         * 	现金打赏数额数组，数组的长度必须为6
         */
        private List<Double> cashes;

        /**
         * 现金打赏自定义最小金额
         */
        private Double cashMin;

        /**
         * 现金打赏开关，不传默认开启   N：关闭   Y：开启
         */
        @VerifyField(ignore = true)
        private String enabled;
    }


    public static final class PolyvChannelUpdateDonateCashParameterBuilder extends ParameterBuilder<PolyvChannelUpdateDonateCashParameter> {
        private PolyvChannelUpdateDonateCashParameter polyvChannelUpdateDonateCashParameter;

        private PolyvChannelUpdateDonateCashParameterBuilder() {
            polyvChannelUpdateDonateCashParameter = new PolyvChannelUpdateDonateCashParameter();
        }

        public static PolyvChannelUpdateDonateCashParameterBuilder aPolyvChannelUpdateDonateCashParameter() {
            return new PolyvChannelUpdateDonateCashParameterBuilder();
        }

        public PolyvChannelUpdateDonateCashParameterBuilder withCashes(List<Double> cashes) {
            polyvChannelUpdateDonateCashParameter.getBody().setCashes(cashes);
            return this;
        }

        public PolyvChannelUpdateDonateCashParameterBuilder withCashMin(Double cashMin) {
            polyvChannelUpdateDonateCashParameter.getBody().setCashMin(cashMin);
            return this;
        }

        public PolyvChannelUpdateDonateCashParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateDonateCashParameter.getBody().setEnabled(enabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateDonateCashParameter build() {
            return polyvChannelUpdateDonateCashParameter;
        }
    }
}
