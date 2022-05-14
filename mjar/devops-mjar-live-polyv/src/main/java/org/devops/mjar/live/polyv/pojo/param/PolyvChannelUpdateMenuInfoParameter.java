package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：修改页面菜单信息
 */
@Data
public class PolyvChannelUpdateMenuInfoParameter extends ChannelSignBean {


    /**
     * 频道菜单ID列表，必须是完整的列表（不能多也不能少）
     */
    private String menuId;

    /**
     * 菜单的内容
     */
    private String content;

    /**
     * "菜单语言类型，默认为中文"
     */
    @VerifyField( ignore = true )
    private String lang;

    public static final class PolyvChannelUpdateMenuInfoParameterBuilder extends ParameterBuilder<PolyvChannelUpdateMenuInfoParameter> {
        private PolyvChannelUpdateMenuInfoParameter polyvChannelUpdateMenuInfoParameter;

        private PolyvChannelUpdateMenuInfoParameterBuilder() {
            polyvChannelUpdateMenuInfoParameter = new PolyvChannelUpdateMenuInfoParameter();
        }

        public static PolyvChannelUpdateMenuInfoParameterBuilder aPolyvChannelUpdateMenuInfoParameter() {
            return new PolyvChannelUpdateMenuInfoParameterBuilder();
        }

        public PolyvChannelUpdateMenuInfoParameterBuilder withMenuId(String menuId) {
            polyvChannelUpdateMenuInfoParameter.setMenuId(menuId);
            return this;
        }

        public PolyvChannelUpdateMenuInfoParameterBuilder withContent(String content) {
            polyvChannelUpdateMenuInfoParameter.setContent(content);
            return this;
        }

        public PolyvChannelUpdateMenuInfoParameterBuilder withLang(String lang) {
            polyvChannelUpdateMenuInfoParameter.setLang(lang);
            return this;
        }

        @Override
        public PolyvChannelUpdateMenuInfoParameter build() {
            return polyvChannelUpdateMenuInfoParameter;
        }
    }
}
