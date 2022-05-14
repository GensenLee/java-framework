package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/3/5 13:45
 * @description：频道功能开关切换
 */
@Data
public class PolyvChannelUpdateGlobalEnabledParameter extends ChannelSignBean {

    /**
     * 功能类型 auth--观看条件设置  switch--功能开关设置  marquee--跑马灯设置  restrict--播放限制设置  " +
     *  "donate--打赏设置  advert--广告设置--回调设置 player--播放器设置
     */
    private String globalEnabledType;

    /**
     * 开关状态,Y或N，Y开启，N关闭
     */
    private String enabled;


    public static final class PolyvChannelUpdateGlobalEnabledParameterBuilder extends ParameterBuilder<PolyvChannelUpdateGlobalEnabledParameter> {
        private PolyvChannelUpdateGlobalEnabledParameter polyvChannelUpdateGlobalEnabledParameter;

        private PolyvChannelUpdateGlobalEnabledParameterBuilder() {
            polyvChannelUpdateGlobalEnabledParameter = new PolyvChannelUpdateGlobalEnabledParameter();
        }

        public static PolyvChannelUpdateGlobalEnabledParameterBuilder aPolyvChannelUpdateGlobalEnabledParameter() {
            return new PolyvChannelUpdateGlobalEnabledParameterBuilder();
        }

        public PolyvChannelUpdateGlobalEnabledParameterBuilder withGlobalEnabledType(SwitchType globalEnabledType) {
            polyvChannelUpdateGlobalEnabledParameter.setGlobalEnabledType(globalEnabledType.getValue());
            return this;
        }

        public PolyvChannelUpdateGlobalEnabledParameterBuilder withEnabled(EnableSetting enabled) {
            polyvChannelUpdateGlobalEnabledParameter.setEnabled(enabled.getValue());
            return this;
        }

        @Override
        public PolyvChannelUpdateGlobalEnabledParameter build() {
            return polyvChannelUpdateGlobalEnabledParameter;
        }
    }

    @Getter
    public enum SwitchType{
        AUTH("观看条件设置", "auth"),
        SWITCH("功能开关设置", "switch"),
        MARQUEE("跑马灯设置", "marquee"),
        RESTRICT("播放限制设置", "restrict"),
        DONATE("打赏设置", "donate"),
        ADVERT("广告设置", "advert"),
        CALLBACK("回调设置", "callback"),
        PLAYER("播放器设置", "player");

        SwitchType(String name, String value) {
            this.name = name;
            this.value = value;
        }

        private String name;

        private String value;
    }

}
