package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fangzy
 * @description：修改引导图
 */

@Data
public class PolyvChannelUpdateSplashParameter extends ChannelSignBean {

    /**
     * 设置开启或关闭引导页, Y开启, N关闭
     */
    private String splashEnabled;

    /**
     * 支持jpg、jpeg、png三种格式，大小不能超过4Mb
     */
    @VerifyField(ignore = true)
    @SignIgnore
    private MultipartFile imgfile;


    public static final class PolyvChannelUpdateSplashParameterBuilder extends ParameterBuilder<PolyvChannelUpdateSplashParameter> {
        private PolyvChannelUpdateSplashParameter polyvChannelUpdateSplashParameter;

        private PolyvChannelUpdateSplashParameterBuilder() {
            polyvChannelUpdateSplashParameter = new PolyvChannelUpdateSplashParameter();
        }

        public static PolyvChannelUpdateSplashParameterBuilder aPolyvChannelUpdateSplashParameter() {
            return new PolyvChannelUpdateSplashParameterBuilder();
        }

        public PolyvChannelUpdateSplashParameterBuilder withSplashEnabled(EnableSetting splashEnabled) {
            polyvChannelUpdateSplashParameter.setSplashEnabled(splashEnabled.getValue());
            return this;
        }

        public PolyvChannelUpdateSplashParameterBuilder withImgfile(MultipartFile imgfile) {
            polyvChannelUpdateSplashParameter.setImgfile(imgfile);
            return this;
        }

        @Override
        public PolyvChannelUpdateSplashParameter build() {
            return polyvChannelUpdateSplashParameter;
        }
    }
}
