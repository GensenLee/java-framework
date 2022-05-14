package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 15:56
 * @description：查询频道页面菜单信息
 */
@Data
public class PolyvChannelSearchMenuListParameter extends ChannelSignBean {

    /**
     * 菜单语言类型，默认为中文
     */
    @VerifyField(ignore = true)
    private String lang;

    public static final class PolyvChannelSearchMenuListParameterBuilder extends ParameterBuilder<PolyvChannelSearchMenuListParameter> {
        private PolyvChannelSearchMenuListParameter polyvChannelSearchMenuListParameter;

        private PolyvChannelSearchMenuListParameterBuilder() {
            polyvChannelSearchMenuListParameter = new PolyvChannelSearchMenuListParameter();
        }

        public static PolyvChannelSearchMenuListParameterBuilder aPolyvChannelSearchMenuListParameter() {
            return new PolyvChannelSearchMenuListParameterBuilder();
        }

        public PolyvChannelSearchMenuListParameterBuilder withLang(String lang) {
            polyvChannelSearchMenuListParameter.setLang(lang);
            return this;
        }

        @Override
        public PolyvChannelSearchMenuListParameter build() {
            return polyvChannelSearchMenuListParameter;
        }
    }
}
