package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/3/5 11:48
 * @description：打赏设置查询
 */
@Data
public class PolyvAppChannelDonatePointSettingParameter extends AppSignBean {

    @VerifyField(ignore = true)
    /**
     * 频道号，不传为获取全局设置，否则为当前生效设置
     */
    private String channelId;


    public static final class PolyvAppChannelDonatePointSettingParameterBuilder extends ParameterBuilder<PolyvAppChannelDonatePointSettingParameter> {
        private PolyvAppChannelDonatePointSettingParameter polyvAppChannelDonatePointSettingParameter;

        private PolyvAppChannelDonatePointSettingParameterBuilder() {
            polyvAppChannelDonatePointSettingParameter = new PolyvAppChannelDonatePointSettingParameter();
        }

        public static PolyvAppChannelDonatePointSettingParameterBuilder aPolyvAppChannelDonatePointSettingParameter() {
            return new PolyvAppChannelDonatePointSettingParameterBuilder();
        }

        public PolyvAppChannelDonatePointSettingParameterBuilder withChannelId(String channelId) {
            polyvAppChannelDonatePointSettingParameter.setChannelId(channelId);
            return this;
        }

        @Override
        public PolyvAppChannelDonatePointSettingParameter build() {
            return polyvAppChannelDonatePointSettingParameter;
        }
    }
}
