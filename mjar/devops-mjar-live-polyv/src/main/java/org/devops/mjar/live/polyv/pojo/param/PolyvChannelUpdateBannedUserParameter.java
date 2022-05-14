package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：禁言/解禁用户
 */
@Data
public class PolyvChannelUpdateBannedUserParameter extends ChannelSignBean {

    /**
     * 聊天室用户ID（非直播账号ID），多个用户用半角逗号","隔开
     */
    private String userIds;

    /**
     * Y表示禁言，N表示解除禁言
     */
    @VerifyField(ignore = true)
    private String toBanned;

    public enum ToBanned{
        Y("Y","禁言"),
        N("N","解除禁言");
        private String code;
        private String name;

        ToBanned(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public static final class PolyvChannelUpdateBannedUserParameterBuilder extends ParameterBuilder<PolyvChannelUpdateBannedUserParameter> {
        private PolyvChannelUpdateBannedUserParameter polyvChannelUpdateBannedUserParameter;

        private PolyvChannelUpdateBannedUserParameterBuilder() {
            polyvChannelUpdateBannedUserParameter = new PolyvChannelUpdateBannedUserParameter();
        }

        public static PolyvChannelUpdateBannedUserParameterBuilder aPolyvChannelUpdateBannedUserParameter() {
            return new PolyvChannelUpdateBannedUserParameterBuilder();
        }

        public PolyvChannelUpdateBannedUserParameterBuilder withUserIds(String userIds) {
            polyvChannelUpdateBannedUserParameter.setUserIds(userIds);
            return this;
        }

        public PolyvChannelUpdateBannedUserParameterBuilder withToBanned(ToBanned toBanned) {
            polyvChannelUpdateBannedUserParameter.setToBanned(toBanned.code);
            return this;
        }

        @Override
        public PolyvChannelUpdateBannedUserParameter build() {
            return polyvChannelUpdateBannedUserParameter;
        }
    }
}
