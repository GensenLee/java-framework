package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：账号设置禁言/解禁用户
 */
@Data
public class PolyvAppUpdateBannedParameter extends AppSignBean {

    /**
     * 聊天室用户ID（非直播账号ID），json数组格式
     */
    private String viewerIds;

    /**
     * Y表示禁言，N表示解除禁言
     */
    private String banned;

    public enum Banned{
        Y("Y","禁言"),
        N("N","解除禁言");
        private String code;
        private String name;

        Banned(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }


    public static final class PolyvAppUpdateBannedParameterBuilder extends ParameterBuilder<PolyvAppUpdateBannedParameter> {
        private PolyvAppUpdateBannedParameter polyvAppUpdateBannedParameter;

        private PolyvAppUpdateBannedParameterBuilder() {
            polyvAppUpdateBannedParameter = new PolyvAppUpdateBannedParameter();
        }

        public static PolyvAppUpdateBannedParameterBuilder aPolyvAppUpdateBannedParameter() {
            return new PolyvAppUpdateBannedParameterBuilder();
        }

        public PolyvAppUpdateBannedParameterBuilder withViewerIds(String viewerIds) {
            polyvAppUpdateBannedParameter.setViewerIds(viewerIds);
            return this;
        }

        public PolyvAppUpdateBannedParameterBuilder withBanned(Banned banned) {
            polyvAppUpdateBannedParameter.setBanned(banned.code);
            return this;
        }

        @Override
        public PolyvAppUpdateBannedParameter build() {
            return polyvAppUpdateBannedParameter;
        }
    }
}
