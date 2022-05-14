package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GENSEN
 * @date 2020/10/28 18:04
 * @description：图片上传请求
 */
@Data
public class PolyvAppUploadImageFileParameter extends AppSignBean {
    /**
     * 文件
     */
    @SignIgnore
    private MultipartFile file;

    /**
     * 文件类型
     */
    private String type;

    public static final class PolyvAppUploadImageFileParameterBuilder extends ParameterBuilder<PolyvAppUploadImageFileParameter>{
        private PolyvAppUploadImageFileParameter polyvAppUploadImageFileParameter;

        private PolyvAppUploadImageFileParameterBuilder() {
            polyvAppUploadImageFileParameter = new PolyvAppUploadImageFileParameter();
        }

        public static PolyvAppUploadImageFileParameterBuilder aPolyvAppUploadImageFileParameter() {
            return new PolyvAppUploadImageFileParameterBuilder();
        }

        public PolyvAppUploadImageFileParameterBuilder withFile(MultipartFile file) {
            polyvAppUploadImageFileParameter.setFile(file);
            return this;
        }

        public PolyvAppUploadImageFileParameterBuilder withType(Type type) {
            polyvAppUploadImageFileParameter.setType(type.getCode());
            return this;
        }

        @Override
        public PolyvAppUploadImageFileParameter build() {
            return polyvAppUploadImageFileParameter;
        }
    }

    @Getter
    public enum Type{
        COVER_IMAGE("coverImage" ,"频道图标，建议140 x 140 大小的图标，支持jpg、jpeg、png格式，文件大小不超过 2M"),
        SPLASH_IMAGE("splashImage" ,"直播引导图，建议 750 x 1334 大小的图片，支持jpg、jpeg、png格式，大小不超过 2M"),
        LOGO_IMAGE("logoImage" ,"播放器logo，建议不大于 140 x 50 大小的图片，支持jpg、jpeg、png格式，文件大小不超过 2M"),
        ADMIN_AVATAR("adminAvatar" ,"频道图标，建议140 x 140 大小的图标，支持jpg、jpeg、png格式，文件大小不超过 2M"),
        ASSISTANT_AVATAR("assistantAvatar" ,"助教头像，建议 140 x 140 大小的图标，文件大小不超过2M"),
        AUTH_CODE_IMAGE("authCodeImage" ,"授权观看二维码图片, 最大不超过 200K"),
        WARM_IMAGE("warmImage" ,"暖场图片, 建议1280 x 720，图片大小不超过 2M，支持 jpg、jpeg、png、gif 格式"),
        AD_IMAGE("adImage" ,"广告栏图片，建议750 x 120，支持png、jpg等文件格式，最大不超过2 M"),
        START_AD_IMAGE("startAdImage" ,"片头广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M"),
        STOP_AD_IMAGE("stopAdImage" ,"暂停广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M"),
        GOOD_IMAGE("goodImage" ,"打赏图标，建议 180 x 180 大小的图标，文件大小不超过 300 k"),
        INVITATION_IMAGE("invitationImage" ,"邀请卡图片，建议 750 x 1334 大小的图片，支持jpg、jpeg、png格式，大小不超过 4 M"),
        MENU_IMAGE("menuImage" ,"频道菜单图片, 最大不能超过为 2M");
        private String code;
        private String description;

        Type(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public static Type get(String code){
            for (Type value : values()) {
                if (value.code.equalsIgnoreCase(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
