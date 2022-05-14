package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询频道提问记录")
@Data
public class PolyvChannelChatGetQuestion extends BaseBean {

    @ApiModelProperty("信息id")
    private String id;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("时间，13位时间戳")
    private Long time;
    @ApiModelProperty("提问者id")
    private String s_userId;
    @ApiModelProperty("消息类型")
    private String event;
    @ApiModelProperty("观众来源")
    private String sourceType;
    @ApiModelProperty("发言人信息")
    private UserContent user;

    @Data
    static class UserContent{

        @ApiModelProperty("发言人昵称")
        private String nick;
        @ApiModelProperty("发言人头像")
        private String pic;
        @ApiModelProperty("发言人id")
        private String userId;
        @ApiModelProperty("场次号")
        private String sessionId;
        @ApiModelProperty("发言人类型值")
        private String userType;
        @ApiModelProperty("是否禁言（true/false）")
        private Boolean banned;
        @ApiModelProperty("频道号")
        private String channelId;


    }

}
