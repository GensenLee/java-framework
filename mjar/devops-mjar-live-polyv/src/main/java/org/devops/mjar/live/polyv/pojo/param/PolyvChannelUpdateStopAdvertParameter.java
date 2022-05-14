package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/28 11:40
 * @description：修改频道播放器暂停广告
 */
@Data
public class PolyvChannelUpdateStopAdvertParameter extends ChannelSignBean {

    /**
     * 设置开关状态
     * Y：开启
     * N：关闭
     */
    @VerifyField(ignore = true)
    private String enabled;
    /**
     * 图片地址，不填代表删除
     */
    @VerifyField(ignore = true)
    private String stopAdvertImage;
    /**
     * 点击图片跳转Url
     */
    @VerifyField(ignore = true)
    private String stopAdvertHref;

    public static final class PolyvChannelUpdateStopAdvertParameterBuilder extends ParameterBuilder<PolyvChannelUpdateStopAdvertParameter> {
        private PolyvChannelUpdateStopAdvertParameter polyvChannelUpdateStopAdvertParameter;

        private PolyvChannelUpdateStopAdvertParameterBuilder() {
            polyvChannelUpdateStopAdvertParameter = new PolyvChannelUpdateStopAdvertParameter();
        }

        public static PolyvChannelUpdateStopAdvertParameterBuilder aPolyvChannelUpdateStopAdvertParameter() {
            return new PolyvChannelUpdateStopAdvertParameterBuilder();
        }

        public PolyvChannelUpdateStopAdvertParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateStopAdvertParameter.setEnabled(enabled.getValue());
            return this;
        }

        public PolyvChannelUpdateStopAdvertParameterBuilder withStopAdvertImage(String stopAdvertImage) {
            polyvChannelUpdateStopAdvertParameter.setStopAdvertImage(stopAdvertImage);
            return this;
        }

        public PolyvChannelUpdateStopAdvertParameterBuilder withStopAdvertHref(String stopAdvertHref) {
            polyvChannelUpdateStopAdvertParameter.setStopAdvertHref(stopAdvertHref);
            return this;
        }

        @Override
        public PolyvChannelUpdateStopAdvertParameter build() {
            return polyvChannelUpdateStopAdvertParameter;
        }
    }
}
