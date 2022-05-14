package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/30 10:04
 * @description：删除频道菜单
 */
@Data
public class PolyvChannelDeleteMenuParameter extends ChannelSignBean {

    /**
     * 频道菜单ID列表
     */
    private String menuIds;

    /**
     * 菜单语言类型，默认为中文
     */
    @VerifyField( ignore = true )
    private String lang;


    public static final class PolyvChannelDeletrMenuParameterBuilder extends ParameterBuilder<PolyvChannelDeleteMenuParameter> {
        private PolyvChannelDeleteMenuParameter polyvChannelDeleteMenuParameter;

        private PolyvChannelDeletrMenuParameterBuilder() {
            polyvChannelDeleteMenuParameter = new PolyvChannelDeleteMenuParameter();
        }

        public static PolyvChannelDeletrMenuParameterBuilder aPolyvChannelDeletrMenuParameter() {
            return new PolyvChannelDeletrMenuParameterBuilder();
        }

        public PolyvChannelDeletrMenuParameterBuilder withMenuIds(String menuIds) {
            polyvChannelDeleteMenuParameter.setMenuIds(menuIds);
            return this;
        }

        public PolyvChannelDeletrMenuParameterBuilder withLang(String lang) {
            polyvChannelDeleteMenuParameter.setLang(lang);
            return this;
        }

        @Override
        public PolyvChannelDeleteMenuParameter build() {
            return polyvChannelDeleteMenuParameter;
        }
    }
}
