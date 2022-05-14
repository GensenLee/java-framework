package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "聊天信息")
public class PolyvChatUserV3 extends BaseBean {
    @ApiModelProperty("角色")
    private String actor;
    @ApiModelProperty("是否禁言")
    private Boolean banned;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("用户ip")
    private String clientIp;
    @ApiModelProperty("观众昵称")
    private String nickname;
    @ApiModelProperty("观众头像")
    private String pic;
    @ApiModelProperty("房间号")
    private String roomId;
    @ApiModelProperty("场次号")
    private String sessionId;
    @ApiModelProperty("socketId")
    private String uid;
    @ApiModelProperty("用户唯一标示")
    private String userId;
    @ApiModelProperty("观众来源")
    private String userSource;
    @ApiModelProperty("用户类型\n" + "slice：云课堂学员\n" + "teacher：讲师\n" + "guest：嘉宾\n" + "manager：管理员\n" + "assistant：助教\n" + "viewer：特邀观众\n" + "monitor：场监\n" + "attendee：研讨会参与者\n" + "student：普通直播观众")
    private String userType;
}
