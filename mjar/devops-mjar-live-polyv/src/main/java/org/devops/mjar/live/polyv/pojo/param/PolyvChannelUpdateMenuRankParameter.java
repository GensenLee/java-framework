package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：修改页面菜单排序
 */
@Data
public class PolyvChannelUpdateMenuRankParameter extends ChannelSignBean {

    /**
     * 频道菜单ID列表，必须是完整的列表（不能多也不能少)
     */
    private String menuIds;

    /**
     * 菜单语言类型，默认为中文
     */
    @VerifyField( ignore = true )
    private String lang;

    public static final class PolyvChannelUpdateMenuRankParameterBuilder extends ParameterBuilder<PolyvChannelUpdateMenuRankParameter> {
        private PolyvChannelUpdateMenuRankParameter polyvChannelUpdateMenuRankParameter;

        private PolyvChannelUpdateMenuRankParameterBuilder() {
            polyvChannelUpdateMenuRankParameter = new PolyvChannelUpdateMenuRankParameter();
        }

        public static PolyvChannelUpdateMenuRankParameterBuilder aPolyvChannelUpdateMenuRankParameter() {
            return new PolyvChannelUpdateMenuRankParameterBuilder();
        }

        public PolyvChannelUpdateMenuRankParameterBuilder withMenuIds(String menuIds) {
            polyvChannelUpdateMenuRankParameter.setMenuIds(menuIds);
            return this;
        }

        public PolyvChannelUpdateMenuRankParameterBuilder withLang(String lang) {
            polyvChannelUpdateMenuRankParameter.setLang(lang);
            return this;
        }

        @Override
        public PolyvChannelUpdateMenuRankParameter build() {
            return polyvChannelUpdateMenuRankParameter;
        }
    }
}
