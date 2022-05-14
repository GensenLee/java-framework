package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.LiveNewScene;
import org.devops.mjar.live.polyv.enums.LiveTemplate;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

/**
 * @author fangzy
 * @description：创建频道v4
 */
@Data
public class PolyvChannelCreateV4Parameter extends AppSignBean {

    @SignIgnore
    private PolyvChannelCreateV4ParameterBody body = new PolyvChannelCreateV4ParameterBody();

    @Data
    public static class PolyvChannelCreateV4ParameterBody extends BaseBean {
        /**
         * 直播名称，最大长度100
         */
        private String name;

        /**
         * 直播场景  topclass-大班课 、 double-双师课（该场景需开通） 、 train-企业培训 、 seminar-研讨会 、 alone-活动营销
         */
        private String newScene;

        /**
         * 直播模板  ppt-文档+视频 、 alone-纯视频(专业) 、 topclass-纯视频(极速) 、 portrait_ppt-文档+视频(竖屏) 、 portrait_alone-视频(竖屏) 、 seminar-研讨会
         * 直播场景为topclass、train或alone时，该字段支持ppt、alone、portrait_alone、portrait_alone
         *
         *
         */
        @VerifyField(ignore = true)
        private String template;

        /**
         * 直播延迟 Y无延时 N普通延迟
         */
        @VerifyField(ignore = true)
        private String pureRtcEnabled;

        /**
         * 转播类型  normal不开启、transmit发起转播、receive接收转播（该功能需要开通）
         */
        @VerifyField(ignore = true)
        private String type;

        /**
         * 线上双师
         */
        @VerifyField(ignore = true)
        private String doubleTeacherType;

        /**
         * 中英双语直播开关  Y开、N关
         */
        @VerifyField(ignore = true)
        private String cnAndEnLiveEnabled;

        /**
         * 封面图片地址
         */
        @VerifyField(ignore = true)
        private String splashImg;

        /**
         * 连麦人数限制
         */
        @VerifyField(ignore = true)
        private Integer linkMicLimit;

        /**
         * 分类ID
         */
        @VerifyField(ignore = true)
        private Integer categoryId;

        /**
         * 开始时间
         */
        @VerifyField(ignore = true)
        private Long startTime;
    }


    public static final class PolyvChannelCreateV4ParameterBuilder extends ParameterBuilder<PolyvChannelCreateV4Parameter>{
        private PolyvChannelCreateV4Parameter polyvChannelCreateV4Parameter;

        private PolyvChannelCreateV4ParameterBuilder() {
            polyvChannelCreateV4Parameter = new PolyvChannelCreateV4Parameter();
        }

        public static PolyvChannelCreateV4ParameterBuilder aPolyvChannelCreateV4Parameter() {
            return new PolyvChannelCreateV4ParameterBuilder();
        }

        public PolyvChannelCreateV4ParameterBuilder withName(String name) {
            polyvChannelCreateV4Parameter.getBody().setName(name);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withNewScene(LiveNewScene newScene) {
            polyvChannelCreateV4Parameter.getBody().setNewScene(newScene.getValue());
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withTemplate(LiveTemplate template) {
            polyvChannelCreateV4Parameter.getBody().setTemplate(template.getValue());
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withPureRtcEnabled(EnableSetting pureRtcEnabled) {
            polyvChannelCreateV4Parameter.getBody().setPureRtcEnabled(pureRtcEnabled.getValue());
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withType(Type type) {
            polyvChannelCreateV4Parameter.getBody().setType(type.value);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withDoubleTeacherType(DoubleTeacherType doubleTeacherType) {
            polyvChannelCreateV4Parameter.getBody().setDoubleTeacherType(doubleTeacherType.value);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withCnAndEnLiveEnabled(EnableSetting cnAndEnLiveEnabled) {
            polyvChannelCreateV4Parameter.getBody().setCnAndEnLiveEnabled(cnAndEnLiveEnabled.getValue());
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withSplashImg(String splashImg) {
            polyvChannelCreateV4Parameter.getBody().setSplashImg(splashImg);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withLinkMicLimit(Integer linkMicLimit) {
            polyvChannelCreateV4Parameter.getBody().setLinkMicLimit(linkMicLimit);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withCategoryId(Integer categoryId) {
            polyvChannelCreateV4Parameter.getBody().setCategoryId(categoryId);
            return this;
        }

        public PolyvChannelCreateV4ParameterBuilder withStartTime(Long startTime) {
            polyvChannelCreateV4Parameter.getBody().setStartTime(startTime);
            return this;
        }

        @Override
        public PolyvChannelCreateV4Parameter build() {
            return polyvChannelCreateV4Parameter;
        }
    }

    public enum Type {
        NORMAL("NORMAL","不开启"),
        TRANSMIT("transmit","发起转播"),
        RECEIVE("receive","接收转播");
        private String value;
        private String name;

        Type(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public enum DoubleTeacherType {
        TRANSMIT("transmit","大房间"),
        RECEIVE("receive","小房间");
        private String value;
        private String name;

        DoubleTeacherType(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

}
