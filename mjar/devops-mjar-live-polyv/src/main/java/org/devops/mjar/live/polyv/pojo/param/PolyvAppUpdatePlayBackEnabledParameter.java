package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/29 18:45
 * @description：账号回放设置
 */
@Data
public class PolyvAppUpdatePlayBackEnabledParameter extends AppSignBean {

    /**
     * 回放开关是开/关的状态，值为Y/N，必填
     */
    private String playBackEnabled;


    public static final class PolyvAppUpdatePlayBackEnabledParameterBuilder extends ParameterBuilder<PolyvAppUpdatePlayBackEnabledParameter>{
        private PolyvAppUpdatePlayBackEnabledParameter polyvAppUpdatePlayBackEnabledParameter;

        private PolyvAppUpdatePlayBackEnabledParameterBuilder() {
            polyvAppUpdatePlayBackEnabledParameter = new PolyvAppUpdatePlayBackEnabledParameter();
        }

        public static PolyvAppUpdatePlayBackEnabledParameterBuilder aPolyvAppUpdatePlayBackEnabledParameter() {
            return new PolyvAppUpdatePlayBackEnabledParameterBuilder();
        }

        public PolyvAppUpdatePlayBackEnabledParameterBuilder withPlayBackEnabled(EnableSetting playBackEnabled) {
            polyvAppUpdatePlayBackEnabledParameter.setPlayBackEnabled(playBackEnabled.getValue());
            return this;
        }

        @Override
        public PolyvAppUpdatePlayBackEnabledParameter build() {
            return polyvAppUpdatePlayBackEnabledParameter;
        }
    }
}
