package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道禁言用户Userid/IP
 */
@Data
public class PolyvChannelBannedListParameter extends ChannelSignBean {

    /**
     * 禁言类型
     * ip ：查询禁言IP
     * userId ：查询禁言用户Id
     */
    private String type;

    /**
     * 是否获取子频道
     * 0：不获取
     * 1：获取
     */
    @VerifyField(ignore = true)
    private Integer toGetSubRooms;

    public enum Type {
        IP("ip","查询禁言IP"),
        USERID("userid", "查询禁言用户id");

        Type(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }

    public enum ToGetSubRooms {
        ZERO(0, "不获取"),
        ONE(1, "获取");

        ToGetSubRooms(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        private Integer code;
        private String name;
    }


    public static final class PolyvChannelBannedListParameterBuilder extends ParameterBuilder<PolyvChannelBannedListParameter> {
        private PolyvChannelBannedListParameter polyvChannelBannedListParameter;

        private PolyvChannelBannedListParameterBuilder() {
            polyvChannelBannedListParameter = new PolyvChannelBannedListParameter();
        }

        public static PolyvChannelBannedListParameterBuilder aPolyvChannelBannedListParameter() {
            return new PolyvChannelBannedListParameterBuilder();
        }

        public PolyvChannelBannedListParameterBuilder withType(Type type) {
            polyvChannelBannedListParameter.setType(type.value);
            return this;
        }

        public PolyvChannelBannedListParameterBuilder withToGetSubRooms(ToGetSubRooms toGetSubRooms) {
            polyvChannelBannedListParameter.setToGetSubRooms(toGetSubRooms.code);
            return this;
        }

        @Override
        public PolyvChannelBannedListParameter build() {
            return polyvChannelBannedListParameter;
        }
    }
}
