package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.ChannelAccountRole;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.PlaybackOrigin;
import org.devops.mjar.live.polyv.enums.PlaybackType;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelAuthSetting;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelCreateBasicSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/07/12 15:19
 * @description：创建频道v3
 */
@Data
public class PolyvChannelCreateV3Parameter extends AppSignBean {

    @VerifyField(ignore = true)
    @SignIgnore
    private String userId;

    @SignIgnore
    private PolyvChannelCreateV3ParameterBody body = new PolyvChannelCreateV3ParameterBody();

    @Data
    public static class PolyvChannelCreateV3ParameterBody extends BaseBean {

        /**
         * 基础设置
         */
        private PolyvChannelCreateBasicSetting basicSetting;

        /**
         * 观看条件设置
         */
        private List<PolyvChannelAuthSetting> authSettings;

        /**
         * 回放设置
         */
        private ChannelPlaybackSetting playbackSetting;

        /**
         * 讲师设置
         */
        private ChannelTeacher teacher;

        /**
         * 角色设置
         */
        private List<ChannelRole> roles;
    }


    public static final class PolyvChannelCreateV3ParameterBuilder extends ParameterBuilder<PolyvChannelCreateV3Parameter> {
        private PolyvChannelCreateV3Parameter polyvChannelCreateV3Parameter;

        private PolyvChannelCreateV3ParameterBuilder() {
            polyvChannelCreateV3Parameter = new PolyvChannelCreateV3Parameter();
        }

        public static PolyvChannelCreateV3ParameterBuilder aPolyvChannelCreateV3Parameter() {
            return new PolyvChannelCreateV3ParameterBuilder();
        }

        public PolyvChannelCreateV3ParameterBuilder withBasicSetting(PolyvChannelCreateBasicSetting basicSetting) {
            polyvChannelCreateV3Parameter.getBody().setBasicSetting(basicSetting);
            return this;
        }

        public PolyvChannelCreateV3ParameterBuilder withAuthSettings(List<PolyvChannelAuthSetting> authSettings) {
            polyvChannelCreateV3Parameter.getBody().setAuthSettings(authSettings);
            return this;
        }

        public PolyvChannelCreateV3ParameterBuilder withPlaybackSetting(ChannelPlaybackSetting playbackSetting) {
            polyvChannelCreateV3Parameter.getBody().setPlaybackSetting(playbackSetting);
            return this;
        }

        public PolyvChannelCreateV3ParameterBuilder withTeacher(ChannelTeacher teacher) {
            polyvChannelCreateV3Parameter.getBody().setTeacher(teacher);
            return this;
        }

        public PolyvChannelCreateV3ParameterBuilder withRoles(List<ChannelRole> roles) {
            polyvChannelCreateV3Parameter.getBody().setRoles(roles);
            return this;
        }

        @Override
        public PolyvChannelCreateV3Parameter build() {
            return polyvChannelCreateV3Parameter;
        }
    }


    /**
     * playbackSetting参数描述
     */
    @Getter
    public static class ChannelPlaybackSetting {
        /**
         * 是否应用通用设置
         * Y：是
         * N：否
         */
        private String globalSettingEnabled;

        public void setGlobalSettingEnabled(EnableSetting globalSettingEnabled) {
            if (globalSettingEnabled == null) {
                return;
            }
            this.globalSettingEnabled = globalSettingEnabled.getValue();
        }

        /**
         * 回放开关
         * Y：开启
         * N：关闭
         */
        private String playbackEnabled;

        public void setPlaybackEnabled(EnableSetting playbackEnabled) {
            if (playbackEnabled == null) {
                return;
            }
            this.playbackEnabled = playbackEnabled.getValue();
        }

        /**
         * 回放方式
         * single：单个回放
         * list：列表回放
         */
        private String type;

        public void setType(PlaybackType type) {
            if (type == null) {
                return;
            }
            this.type = type.getCode();
        }

        /**
         * 回放来源
         * record：暂存
         * playback：回放列表
         * vod：点播列表
         */
        private String origin;

        public void setOrigin(PlaybackOrigin origin) {
            if (origin == null) {
                return;
            }
            this.origin = origin.getCode();
        }

        /**
         * 单个回放的视频id
         */
        @Setter
        private String videoId;

    }

    @Data
    public static class ChannelTeacher {
        /**
         * 讲师昵称
         */
        private String nickname;

        /**
         * 讲师头衔
         */
        private String actor;

        /**
         * 讲师密码
         */
        private String passwd;

        /**
         * 头像图片地址
         */
        private String avatar;

    }

    @Data
    public static class ChannelRole {
        /**
         * 角色昵称
         */
        private String nickname;

        /**
         * 角色头衔
         */
        private String actor;

        /**
         * 角色密码
         */
        private String passwd;

        /**
         * 头像图片地址
         */
        private String avatar;

        /**
         * 角色类型
         * Assistant-助教
         * Guest-嘉宾
         */
        private String role;

        public void setRole(ChannelAccountRole role) {
            if (role == null) {
                return;
            }
            this.role = role.getCode();
        }
    }

}
