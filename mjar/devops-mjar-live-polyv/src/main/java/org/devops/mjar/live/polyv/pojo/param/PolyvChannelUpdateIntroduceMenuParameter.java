package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/28 09:45
 * @description：修改直播介绍菜单
 */
@Data
public class PolyvChannelUpdateIntroduceMenuParameter extends ChannelSignBean {

    /**
     * 菜单类型，目前仅支持取值为desc
     * 示例
     */
    private String menuType;
    /**
     *直播介绍的内容（此处可以填html页面的相关内容，如增加图片、增加文字样式等）
     */
    private String content;

    @VerifyField(ignore = true)
    @SignIgnore
    private String userId;

    @VerifyField(ignore = true)
    @SignIgnore
    private String channelId;

    public static final class PolypChannelUpdateIntroduceMenuParameterBuilder extends ParameterBuilder<PolyvChannelUpdateIntroduceMenuParameter> {
        private PolyvChannelUpdateIntroduceMenuParameter polyvChannelUpdateIntroduceMenuParameter;

        private PolypChannelUpdateIntroduceMenuParameterBuilder() {
            polyvChannelUpdateIntroduceMenuParameter = new PolyvChannelUpdateIntroduceMenuParameter();
        }

        public static PolypChannelUpdateIntroduceMenuParameterBuilder aPolyvChannelUpdateIntroductMenuParameter() {
            return new PolypChannelUpdateIntroduceMenuParameterBuilder();
        }

        public PolypChannelUpdateIntroduceMenuParameterBuilder withMenuType(String menuType) {
            polyvChannelUpdateIntroduceMenuParameter.setMenuType(menuType);
            return this;
        }

        public PolypChannelUpdateIntroduceMenuParameterBuilder withContent(String content) {
            polyvChannelUpdateIntroduceMenuParameter.setContent(content);
            return this;
        }
        @Override
        public PolyvChannelUpdateIntroduceMenuParameter build() {
            return polyvChannelUpdateIntroduceMenuParameter;
        }
    }
}
