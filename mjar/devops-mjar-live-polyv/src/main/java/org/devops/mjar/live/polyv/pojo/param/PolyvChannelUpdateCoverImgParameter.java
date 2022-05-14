package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fangzy
 * @description：修改频道图标
 */
@Data
public class PolyvChannelUpdateCoverImgParameter extends ChannelSignBean {

    /**
     *  图片为大小为2MB的JPG、JPEG、PNG图片
     */
    @SignIgnore
    private MultipartFile imgfile;


    public static final class PolyvChannelUpdateCoverImgParameterBuilder extends ParameterBuilder<PolyvChannelUpdateCoverImgParameter> {
        private PolyvChannelUpdateCoverImgParameter polyvChannelUpdateCoverImgParameter;

        private PolyvChannelUpdateCoverImgParameterBuilder() {
            polyvChannelUpdateCoverImgParameter = new PolyvChannelUpdateCoverImgParameter();
        }

        public static PolyvChannelUpdateCoverImgParameterBuilder aPolyvChannelUpdateCoverImgParameter() {
            return new PolyvChannelUpdateCoverImgParameterBuilder();
        }

        public PolyvChannelUpdateCoverImgParameterBuilder withImgfile(MultipartFile imgfile) {
            polyvChannelUpdateCoverImgParameter.setImgfile(imgfile);
            return this;
        }

        @Override
        public PolyvChannelUpdateCoverImgParameter build() {
            return polyvChannelUpdateCoverImgParameter;
        }
    }
}
