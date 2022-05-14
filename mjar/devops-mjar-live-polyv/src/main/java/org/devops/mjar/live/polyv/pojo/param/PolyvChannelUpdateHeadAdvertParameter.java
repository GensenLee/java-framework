package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * @author bigboss
 * @date 2021/7/28 11:20
 * @description：修改频道播放器片头广告
 */

@Data
public class PolyvChannelUpdateHeadAdvertParameter extends ChannelSignBean {
    /**
     * 设置开关状态
     */
    @VerifyField(ignore = true)
    private String enabled;
    /**
     * 广告类型
     */
    @VerifyField(ignore = true)
    private String headAdvertType;
    /**
     * 广告地址
     */
    @VerifyField(ignore = true)
    private String headAdvertMediaUrl;
    /**
     * 广告跳转地址
     */
    @VerifyField(ignore = true)
    private String headAdvertHref;
    /**
     * 广告时长，单位秒
     */
    @VerifyField(ignore = true)
    private Integer headAdvertDuration;
    /**
     * 广告宽度
     */
    @VerifyField(ignore = true)
    private Integer headAdvertWidth;
    /**
     * 广告高度
     */
    @VerifyField(ignore = true)
    private Integer headAdvertHeight;

    @Getter
    public static enum HeadAdvertType{

        NONE( "NONE","无广告" ),
        IMAGE("IMAGE","图片广告" ),
        FLV( "FLV","视频广告" );

        HeadAdvertType(String code, String value){
            this.code = code;
            this.value = value;
        }
        private String code;
        private String value;

    }

    public static final class PolyvChannelUpdateHeadAdvertParameterBuilder extends ParameterBuilder<PolyvChannelUpdateHeadAdvertParameter> {
        private PolyvChannelUpdateHeadAdvertParameter plyvChannelUpdateHeadAdvertParameter;

        private PolyvChannelUpdateHeadAdvertParameterBuilder() {
            plyvChannelUpdateHeadAdvertParameter = new PolyvChannelUpdateHeadAdvertParameter();
        }

        public static PolyvChannelUpdateHeadAdvertParameterBuilder aPolyvChannelUpdateHeadAdvertParameter() {
            return new PolyvChannelUpdateHeadAdvertParameterBuilder();
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withEnabled(EnableSetting enabled) {
            plyvChannelUpdateHeadAdvertParameter.setEnabled(enabled.getValue());
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertType(HeadAdvertType headAdvertType) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertType(headAdvertType.getCode());
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertMediaUrl(String headAdvertMediaUrl) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertMediaUrl(headAdvertMediaUrl);
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertHref(String headAdvertHref) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertHref(headAdvertHref);
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertDuration(Integer headAdvertDuration) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertDuration(headAdvertDuration);
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertWidth(Integer headAdvertWidth) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertWidth(headAdvertWidth);
            return this;
        }

        public PolyvChannelUpdateHeadAdvertParameterBuilder withHeadAdvertHeight(Integer headAdvertHeight) {
            plyvChannelUpdateHeadAdvertParameter.setHeadAdvertHeight(headAdvertHeight);
            return this;
        }

        @Override
        public PolyvChannelUpdateHeadAdvertParameter build() {
            return plyvChannelUpdateHeadAdvertParameter;
        }
    }
}
