package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2021/07/23 11:41
 * @description:修改暖场图片
 */
@Data
public class PolyvChannelUpdateWarmUpImageParameter extends ChannelSignBean {


    /**
     * 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式
     */
    private String coverImage;


    /**
     * 暖场图片跳转地址
     */
    @VerifyField(ignore = true)
    private String coverHref;


    public static final class PolyvChannelUpdateWarmUpImageParameterBuilder extends ParameterBuilder<PolyvChannelUpdateWarmUpImageParameter>{
        private PolyvChannelUpdateWarmUpImageParameter polyvChannelUpdateWarmUpImageParameter;

        private PolyvChannelUpdateWarmUpImageParameterBuilder() {
            polyvChannelUpdateWarmUpImageParameter = new PolyvChannelUpdateWarmUpImageParameter();
        }

        public static PolyvChannelUpdateWarmUpImageParameterBuilder aPolyvChannelUpdateWarmUpImageParameter() {
            return new PolyvChannelUpdateWarmUpImageParameterBuilder();
        }

        public PolyvChannelUpdateWarmUpImageParameterBuilder withCoverImage(String coverImage) {
            polyvChannelUpdateWarmUpImageParameter.setCoverImage(coverImage);
            return this;
        }

        public PolyvChannelUpdateWarmUpImageParameterBuilder withCoverHref(String coverHref) {
            polyvChannelUpdateWarmUpImageParameter.setCoverHref(coverHref);
            return this;
        }

        @Override
        public PolyvChannelUpdateWarmUpImageParameter build() {
            return polyvChannelUpdateWarmUpImageParameter;
        }
    }
}
