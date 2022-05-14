package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.UserType;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：查询频道聊天记录
 */

@Data
public class PolyvChannelChatHistoryParameter extends ChannelSignBean {

    /**
     * 聊天记录的开始时间
     * 格式为yyyy-MM-dd，例如：2021-03-01
     * 或者格式为yyyy-MM-dd HH:mm:ss，例如：2021-03-01 16:30:12
     */
    @VerifyField(ignore = true)
    private String startDay;
    /**
     * 	聊天记录的结束时间，要求同上
     */
    @VerifyField(ignore = true)
    private String endDay;
    /**
     * 获取第几页聊天记录，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;
    /**
     * 每页记录数，默认为1000
     */
    @VerifyField(ignore = true)
    private Integer pageSize;
    /**
     * 用户类型，可以选择多个类型，用英文逗号隔开
     */
    @VerifyField(ignore = true)
    private String userType;

    /**
     *  聊天记录审核状态
     */
    @VerifyField(ignore = true)
    private String status;

    /**
     * 类型，不填默认公聊 extend：管理员私聊
     */
    @VerifyField(ignore = true)
    private String source;

    /**
     * 如果有房间号，需要传入房间号，默认不传
     */
    @VerifyField(ignore = true)
    private String roomId;


    public static final class PolyvChannelChatHistoryParameterBuilder extends ParameterBuilder<PolyvChannelChatHistoryParameter> {
        private PolyvChannelChatHistoryParameter polyvChannelChatHistoryParameter;

        private PolyvChannelChatHistoryParameterBuilder() {
            polyvChannelChatHistoryParameter = new PolyvChannelChatHistoryParameter();
        }

        public static PolyvChannelChatHistoryParameterBuilder aPolyvChannelChatHistoryParameter() {
            return new PolyvChannelChatHistoryParameterBuilder();
        }

        public PolyvChannelChatHistoryParameterBuilder withStartDay(String startDay) {
            polyvChannelChatHistoryParameter.setStartDay(startDay);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withEndDay(String endDay) {
            polyvChannelChatHistoryParameter.setEndDay(endDay);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withPage(Integer page) {
            polyvChannelChatHistoryParameter.setPage(page);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelChatHistoryParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withUserType(UserType userType) {
            polyvChannelChatHistoryParameter.setUserType(userType.getValue());
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withStatus(String status) {
            polyvChannelChatHistoryParameter.setStatus(status);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withSource(String source) {
            polyvChannelChatHistoryParameter.setSource(source);
            return this;
        }

        public PolyvChannelChatHistoryParameterBuilder withRoomId(String roomId) {
            polyvChannelChatHistoryParameter.setRoomId(roomId);
            return this;
        }

        @Override
        public PolyvChannelChatHistoryParameter build() {
            return polyvChannelChatHistoryParameter;
        }
    }
}
