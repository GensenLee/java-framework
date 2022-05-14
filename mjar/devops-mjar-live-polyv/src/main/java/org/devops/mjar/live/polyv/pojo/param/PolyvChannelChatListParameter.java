package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

@Data
public class PolyvChannelChatListParameter extends ChannelSignBean {

    /**
     * 房间号
     */

    private String roomId;

    /**
     * 页码，默认1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每一页条数，默认100，最多返回1000条
     */
    @VerifyField(ignore = true)
    private String len;

    /**
     * 回调函数，用于解决前端请求跨域问题
     */
    @VerifyField(ignore = true)
    private Integer callback;

    /**
     * 回调函数，用于解决前端请求跨域问题
     */
    @VerifyField(ignore = true)
    private Integer toGetSubRooms;


    public static final class PolyvChannelChatListParameterBuilder extends ParameterBuilder<PolyvChannelChatListParameter> {
        private PolyvChannelChatListParameter polyvChannelChatListParameter;

        private PolyvChannelChatListParameterBuilder() {
            polyvChannelChatListParameter = new PolyvChannelChatListParameter();
        }

        public static PolyvChannelChatListParameterBuilder aPolyvChannelChatListParameter() {
            return new PolyvChannelChatListParameterBuilder();
        }

        public PolyvChannelChatListParameterBuilder withRoomId(String roomId) {
            polyvChannelChatListParameter.setRoomId(roomId);
            return this;
        }

        public PolyvChannelChatListParameterBuilder withPage(Integer page) {
            polyvChannelChatListParameter.setPage(page);
            return this;
        }

        public PolyvChannelChatListParameterBuilder withLen(String len) {
            polyvChannelChatListParameter.setLen(len);
            return this;
        }

        public PolyvChannelChatListParameterBuilder withCallback(Integer callback) {
            polyvChannelChatListParameter.setCallback(callback);
            return this;
        }

        public PolyvChannelChatListParameterBuilder withToGetSubRooms(Integer toGetSubRooms) {
            polyvChannelChatListParameter.setToGetSubRooms(toGetSubRooms);
            return this;
        }

        @Override
        public PolyvChannelChatListParameter build() {
            return polyvChannelChatListParameter;
        }
    }
}
