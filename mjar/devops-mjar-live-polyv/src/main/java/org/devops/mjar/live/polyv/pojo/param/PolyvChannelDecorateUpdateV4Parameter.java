package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.pojo.model.PolyvDecorateConfigV4;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.Body;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * @author GENSEN
 * @date 2021/9/6 23:09
 * @description：观看页引导设置
 */
@Data
public class PolyvChannelDecorateUpdateV4Parameter extends ChannelSignBean {

    @Body
    private PolyvDecorateConfigV4 body;

    public static final class PolyvChannelDecorateUpdateV4ParameterBuilder extends ParameterBuilder<PolyvChannelDecorateUpdateV4Parameter> {
        private PolyvChannelDecorateUpdateV4Parameter polyvChannelDecorateUpdateV4Parameter;

        private PolyvChannelDecorateUpdateV4ParameterBuilder() {
            polyvChannelDecorateUpdateV4Parameter = new PolyvChannelDecorateUpdateV4Parameter();
        }

        public static PolyvChannelDecorateUpdateV4ParameterBuilder aPolyvChannelDecorateUpdateV4Parameter() {
            return new PolyvChannelDecorateUpdateV4ParameterBuilder();
        }

        public PolyvChannelDecorateUpdateV4ParameterBuilder withConfig(PolyvDecorateConfigV4 config) {
            polyvChannelDecorateUpdateV4Parameter.body = config;
            return this;
        }


        @Override
        public PolyvChannelDecorateUpdateV4Parameter build() {
            return polyvChannelDecorateUpdateV4Parameter;
        }
    }

    @Getter
    public enum Lang {
        EN("EN", "英文"),
        CN("zh_CN", "中文");

        private String value;
        private String code;

        Lang(String code, String value) {
            this.code = code;
            this.value = value;
        }
    }

    @Getter
    public enum MobileWatchLayout {
        NORMAL("normal", "常规直播"),
        PORTRAIT("portrait", "直播带货");

        private String value;
        private String code;

        MobileWatchLayout(String code, String value) {
            this.code = code;
            this.value = value;
        }
    }

    @Getter
    public enum Skin {
        BLACK("black", "时尚黑"),
        RED("red", "喜庆红"),
        BLUE("blue", "科技蓝"),
        WHITE("white", "经典白"),
        GREEN("green", "薄荷绿"),
        GOLDEN("golden", "富贵金");
        private String value;
        private String code;

        Skin(String code, String value) {
            this.code = code;
            this.value = value;
        }
    }
}
