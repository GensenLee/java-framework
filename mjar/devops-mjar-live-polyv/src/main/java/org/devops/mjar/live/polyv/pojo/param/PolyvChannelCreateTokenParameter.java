package org.devops.mjar.live.polyv.pojo.param;


import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 11:00
 * @description：获取频道API的访问令牌
 */
@Data
public class PolyvChannelCreateTokenParameter extends ChannelSignBean {

    /**
     * 是否一次性有效，true/false，默认为false
     */
    @VerifyField(ignore = true)
    private Boolean disposable;
    /**
     * token有效时长，单位为秒，且 0 < expireSeconds <= 3600，默认为半个小时
     */
    @VerifyField(ignore = true)
    private Integer expireSeconds;

    public static final class PolyvChannelCreateTokenParameterBuilder extends ParameterBuilder<PolyvChannelCreateTokenParameter> {
        private PolyvChannelCreateTokenParameter polyvChannelCreateTokenParameter;

        private PolyvChannelCreateTokenParameterBuilder() {
            polyvChannelCreateTokenParameter = new PolyvChannelCreateTokenParameter();
        }

        public static PolyvChannelCreateTokenParameterBuilder aPolyvChannelCreateTokenParameter() {
            return new PolyvChannelCreateTokenParameterBuilder();
        }

        public PolyvChannelCreateTokenParameterBuilder withDisposable(Boolean disposable) {
            polyvChannelCreateTokenParameter.setDisposable(disposable);
            return this;
        }

        public PolyvChannelCreateTokenParameterBuilder withExpireSeconds(Integer expireSeconds) {
            polyvChannelCreateTokenParameter.setExpireSeconds(expireSeconds);
            return this;
        }

        @Override
        public PolyvChannelCreateTokenParameter build() {
            return polyvChannelCreateTokenParameter;
        }
    }
}
