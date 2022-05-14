package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.LiveScene;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * @author GENSEN
 * @date 2020/11/13 14:06
 * @description：频道更新
 */
@Data
public class PolyvChannelUpdateParameter extends ChannelSignBean {

    /**
     * 要更新的字段名称：password 密码 scene 直播场景 maxViewer 最大同时在线人数
     */
    private String field;

    /**
     * 新的字段值，除设置无限制最大观看人数时可不提交，其他情况都为必填
     */
    @VerifyField(ignore = true)
    private String value;


    public static final class PolyvChannelUpdateParameterBuilder extends ParameterBuilder<PolyvChannelUpdateParameter> {
        private PolyvChannelUpdateParameter polyvChannelUpdateParameter;

        private PolyvChannelUpdateParameterBuilder() {
            polyvChannelUpdateParameter = new PolyvChannelUpdateParameter();
        }

        public static PolyvChannelUpdateParameterBuilder aPolyvChannelUpdateParameter() {
            return new PolyvChannelUpdateParameterBuilder();
        }

        public PolyvChannelUpdateParameterBuilder withField(UpdateField field) {
            polyvChannelUpdateParameter.setField(field.getCode());
            return this;
        }

        public PolyvChannelUpdateParameterBuilder withValue(String value) {
            polyvChannelUpdateParameter.setValue(value);
            return this;
        }

        @Override
        public PolyvChannelUpdateParameter build() {
            if (StringUtil.isNotEmpty(polyvChannelUpdateParameter.field)
                    && polyvChannelUpdateParameter.field.equals(UpdateField.SCENE.getCode())) {
                if (LiveScene.get(polyvChannelUpdateParameter.value) == null) {
                    System.err.println("直播场景(scene)值请使用枚举设置 com.sinmn.polyv.api.enums.LiveScene");
                    throw new LiveApiRuntimeException("错误的直播场景值");
                }
            }
            return polyvChannelUpdateParameter;
        }
    }

    @Getter
    public enum UpdateField{
        PASSWORD("password", "密码"),
        NAME("name", "频道名"),
        MAXVIEWER("maxViewer", "最大同时在线人数"),
        SCENE("scene", "直播场景");

        UpdateField(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;
    }
}
