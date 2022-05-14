package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthSetting;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelUpdateBasicSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/07/12 15:19
 * @description：频道更新v3
 */
@Data
public class PolyvChannelUpdateV3Parameter extends ChannelSignBean {

    @VerifyField(ignore = true)
    private String userId;


    @SignIgnore
    private PolyvChannelUpdateV3ParameterBody body = new PolyvChannelUpdateV3ParameterBody();

    @Data
    public static class PolyvChannelUpdateV3ParameterBody extends BaseBean {

        /**
         * 基础设置
         */
        private PolyvChannelUpdateBasicSetting basicSetting;

        /**
         * 观看条件设置
         */
        private List<PolyvChannelAuthSetting> authSettings;

    }


    public static final class PolyvChannelUpdateV3ParameterBuilder extends ParameterBuilder<PolyvChannelUpdateV3Parameter> {
        private PolyvChannelUpdateV3Parameter polyvChannelUpdateV3Parameter;

        private PolyvChannelUpdateV3ParameterBuilder() {
            polyvChannelUpdateV3Parameter = new PolyvChannelUpdateV3Parameter();
        }

        public static PolyvChannelUpdateV3ParameterBuilder aPolyvChannelUpdateV3Parameter() {
            return new PolyvChannelUpdateV3ParameterBuilder();
        }

        public PolyvChannelUpdateV3ParameterBuilder withBasicSetting(PolyvChannelUpdateBasicSetting basicSetting) {
            polyvChannelUpdateV3Parameter.getBody().setBasicSetting(basicSetting);
            return this;
        }

        public PolyvChannelUpdateV3ParameterBuilder withAuthSettings(List<PolyvChannelAuthSetting> authSettings) {
            polyvChannelUpdateV3Parameter.getBody().setAuthSettings(authSettings);
            return this;
        }

        @Override
        public PolyvChannelUpdateV3Parameter build() {
            return polyvChannelUpdateV3Parameter;
        }
    }

}
