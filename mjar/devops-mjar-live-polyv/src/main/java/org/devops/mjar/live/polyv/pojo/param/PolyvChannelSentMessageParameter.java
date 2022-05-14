package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：发送图文信息
 */
@Data
public class PolyvChannelSentMessageParameter extends ChannelSignBean {

    /**
     * 发送的文本消息
     */
    private String msg;

    /**
     * 管理员头像
     */
    private String pic;

    /**
     * 昵称，最大为8个长度，超出会被截断
     */
    private String nickName;

    /**
     * 管理员索引，可以指定多个管理员发送消息，默认只有一个管理员
     */
    @VerifyField(ignore = true)
    private Integer adminIndex;

    /**
     * 头衔，最大为4个长度，超出会被截断，不传参数则表示无头衔
     */
    @VerifyField(ignore = true)
    private String actor;

    /**
     * 当频道开启审核后消息是否需要经过审核，默认为N
     * Y：不需要
     * N：需要
     */
    @VerifyField(ignore = true)
    private String freeReview;

    /**
     * 接口版本，不填将使用默认版本，目前可选版本(3.1)，不同的版本返回数据会有细微差异，详情查看响应示例
     */
    @VerifyField(ignore = true)
    private String apiVersion;

    public enum FreeReview {
        Y("Y","不需要"),
        N("N", "需要");

        FreeReview(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }


    public static final class PolyvChannelSentMessageParameterBuilder extends ParameterBuilder<PolyvChannelSentMessageParameter> {
        private PolyvChannelSentMessageParameter polyvChannelSentMessageParameter;

        private PolyvChannelSentMessageParameterBuilder() {
            polyvChannelSentMessageParameter = new PolyvChannelSentMessageParameter();
        }

        public static PolyvChannelSentMessageParameterBuilder aPolyvChannelSentMessageParameter() {
            return new PolyvChannelSentMessageParameterBuilder();
        }

        public PolyvChannelSentMessageParameterBuilder withMsg(String msg) {
            polyvChannelSentMessageParameter.setMsg(msg);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withPic(String pic) {
            polyvChannelSentMessageParameter.setPic(pic);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withNickName(String nickName) {
            polyvChannelSentMessageParameter.setNickName(nickName);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withAdminIndex(Integer adminIndex) {
            polyvChannelSentMessageParameter.setAdminIndex(adminIndex);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withActor(String actor) {
            polyvChannelSentMessageParameter.setActor(actor);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withFreeReview(FreeReview freeReview) {
            polyvChannelSentMessageParameter.setFreeReview(freeReview.value);
            return this;
        }

        public PolyvChannelSentMessageParameterBuilder withApiVersion(String apiVersion) {
            polyvChannelSentMessageParameter.setApiVersion(apiVersion);
            return this;
        }

        @Override
        public PolyvChannelSentMessageParameter build() {
            return polyvChannelSentMessageParameter;
        }
    }
}
