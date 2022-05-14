package org.devops.mjar.live.polyv.pojo.param;


import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.MenuType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/7/23 12:07
 * @description：增加页面菜单
 */
@Data
public class PolyvChannelCreateMenuParameter extends ChannelSignBean {

    /**
     *  菜单名称
     */
    private String name;
    /*
        菜单类型
     */
    private String type;
    /**
     *菜单内容\n" +
     *       "当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。\n" +
     *       "当菜单类型为外链推广时，该值为外链链接地址。\n" +
     *       "当菜单类型为互动聊天、咨询提问时，该值不需要传值，默认为null
     *
     */
    @VerifyField(ignore = true)
    private String content;
    /**
      *  菜单语言类型，默认为中文是否一次性有效，true/false，默认为false\n
     */
    @VerifyField(ignore = true)
    private String lang;


    public static final class PolyvChannelCreateMenuParameterBuilder extends ParameterBuilder<PolyvChannelCreateMenuParameter> {
        private PolyvChannelCreateMenuParameter polyvChannelCreateMenuParameter;

        private PolyvChannelCreateMenuParameterBuilder() {
            polyvChannelCreateMenuParameter = new PolyvChannelCreateMenuParameter();
        }

        public static PolyvChannelCreateMenuParameterBuilder aPolyvChannelCreateMenuParameter() {
            return new PolyvChannelCreateMenuParameterBuilder();
        }

        public PolyvChannelCreateMenuParameterBuilder withName(String name) {
            polyvChannelCreateMenuParameter.setName(name);
            return this;
        }

        public PolyvChannelCreateMenuParameterBuilder withType(MenuType type) {
            polyvChannelCreateMenuParameter.setType(type.getCode());
            return this;
        }

        public PolyvChannelCreateMenuParameterBuilder withContent(String content) {
            polyvChannelCreateMenuParameter.setContent(content);
            return this;
        }

        public PolyvChannelCreateMenuParameterBuilder withLang(String lang) {
            polyvChannelCreateMenuParameter.setLang(lang);
            return this;
        }

        @Override
        public PolyvChannelCreateMenuParameter build() {
            return polyvChannelCreateMenuParameter;
        }
    }
}
