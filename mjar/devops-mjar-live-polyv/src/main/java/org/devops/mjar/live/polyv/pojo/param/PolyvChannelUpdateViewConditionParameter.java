package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author bigboss
 * @date 2021/07/27 11:41
 * @description:修改频道观看条件
 */
@Data
public class PolyvChannelUpdateViewConditionParameter extends ChannelSignBean {

    @SignIgnore
    private  AuthSettingsBody body = new AuthSettingsBody();

    @Data
    public static class AuthSettingsBody extends BaseBean {
        private List<PolyvChannelAuthSetting> authSettings;
    }

    public static final class PolyvChannelUpdateViewConditionParameterBuilder extends ParameterBuilder<PolyvChannelUpdateViewConditionParameter> {
        private PolyvChannelUpdateViewConditionParameter polyvChannelUpdateViewConditionParameter;

        private PolyvChannelUpdateViewConditionParameterBuilder() {
            polyvChannelUpdateViewConditionParameter = new PolyvChannelUpdateViewConditionParameter();
        }

        public static PolyvChannelUpdateViewConditionParameterBuilder aPolyvChannelUpdateViewConditionParameter() {
            return new PolyvChannelUpdateViewConditionParameterBuilder();
        }

        public PolyvChannelUpdateViewConditionParameterBuilder withAuthSettings(List<PolyvChannelAuthSetting> settings) {
            polyvChannelUpdateViewConditionParameter.getBody().setAuthSettings(settings) ;
            return this;
        }

        @Override
        public PolyvChannelUpdateViewConditionParameter build() {
            return polyvChannelUpdateViewConditionParameter;
        }
    }
}

