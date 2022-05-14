package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 更新频道重制课件设置
 */
@Data
public class PolyvChannelUpdatePptRecordSettingParameter extends ChannelSignBean {

    /**
     * 应用全局设置开关 Y开启、N关闭
     */
    private String globalSettingEnabled;

    /**
     * 摄像头画面比例 0为16:9、1为4:3
     */
    @VerifyField(ignore = true)
    private Integer videoRatio;

    /**
     * 背景图片，旧版重制有效，尺寸为1280X720,支持jpg和png格式
     */
    @VerifyField(ignore = true)
    @SignIgnore
    private MultipartFile backgroundImgFile;

    /**
     * 展示图片，新版重制有效，摄像头画面比例为16:9时尺寸为480X810，摄像头画面比例为4:3时尺寸为480X720,支持jpg和png格式
     */
    @VerifyField(ignore = true)
    @SignIgnore
    private MultipartFile brandImgFile;

    /**
     * 视频布局方式，0.三分屏、1.纯文档
     */
    private Integer type;

    public static final class PolyvChannelUpdatePptRecordSettingParameterBuilder extends ParameterBuilder<PolyvChannelUpdatePptRecordSettingParameter> {
        private PolyvChannelUpdatePptRecordSettingParameter polyvChannelUpdatePptRecordSettingParameter;

        private PolyvChannelUpdatePptRecordSettingParameterBuilder() {
            polyvChannelUpdatePptRecordSettingParameter = new PolyvChannelUpdatePptRecordSettingParameter();
        }

        public static PolyvChannelUpdatePptRecordSettingParameterBuilder aPolyvChannelUpdatePptRecordSettingParameter() {
            return new PolyvChannelUpdatePptRecordSettingParameterBuilder();
        }

        public PolyvChannelUpdatePptRecordSettingParameterBuilder withGlobalSettingEnabled(EnableSetting globalSettingEnabled) {
            polyvChannelUpdatePptRecordSettingParameter.setGlobalSettingEnabled(globalSettingEnabled.getValue());
            return this;
        }

        public PolyvChannelUpdatePptRecordSettingParameterBuilder withVideoRatio(Integer videoRatio) {
            polyvChannelUpdatePptRecordSettingParameter.setVideoRatio(videoRatio);
            return this;
        }

        public PolyvChannelUpdatePptRecordSettingParameterBuilder withBackgroundImgFile(MultipartFile backgroundImgFile) {
            polyvChannelUpdatePptRecordSettingParameter.setBackgroundImgFile(backgroundImgFile);
            return this;
        }

        public PolyvChannelUpdatePptRecordSettingParameterBuilder withBrandImgFile(MultipartFile brandImgFile) {
            polyvChannelUpdatePptRecordSettingParameter.setBrandImgFile(brandImgFile);
            return this;
        }

        public PolyvChannelUpdatePptRecordSettingParameterBuilder withType(Integer type) {
            polyvChannelUpdatePptRecordSettingParameter.setType(type);
            return this;
        }

        @Override
        public PolyvChannelUpdatePptRecordSettingParameter build() {
            return polyvChannelUpdatePptRecordSettingParameter;
        }
    }
}
