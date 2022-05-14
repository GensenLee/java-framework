package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "聊天记录")
public class PolyvChannelChatHistoryV3 extends BaseBean {
    @ApiModelProperty("聊天消息id")
    private String id;
    @ApiModelProperty("账号id")
    private String accountId;
    @ApiModelProperty("用户ip")
    private String clientIP;
    @ApiModelProperty("聊天内容")
    private String content;
    @ApiModelProperty("事件，具体参考聊天室SDK")
    private String event;
    @ApiModelProperty("图片消息的图片地址")
    private String image;
    @ApiModelProperty("消息类型\n" + "redpaper：红包消息\n" + "get_redpaper：领取红包消息\n" + "chatImg：图片消息\n" + "custom：自定义消息（通过socket发送的自定义消息）\n" + "reward：打赏消息\n" + "customerMessage：自定义消息（通过http接口发送的自定义消息）为空：普通聊天消息")
    private String msgType;
    @ApiModelProperty("引用")
    private String quote;
    @ApiModelProperty("场次号")
    private String sessionId;
    @ApiModelProperty("房间号")
    private String roomId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("发送消息时的时间戳")
    private Long time;
    @ApiModelProperty("用户类型\n" + "slice：云课堂学员\n" + "teacher：讲师\n" + "guest：嘉宾\n" + "manager：管理员\n" + "assistant：助教\n" + "viewer：特邀观众\n" + "monitor：场监\n" + "attendee：研讨会参与者\n" + "student：普通直播观众")
    private String userType;
    @ApiModelProperty("审核状态\n" + "pass：已审核\n" + "censor：审核中\n" + "delete：删除")
    private String status;
    @ApiModelProperty("消息来源\n" + "public：群聊\n" + "extend：管理员私聊")
    private String sourceType;
    @ApiModelProperty("聊天信息数组")
    private PolyvChatUserV3 user;

}
