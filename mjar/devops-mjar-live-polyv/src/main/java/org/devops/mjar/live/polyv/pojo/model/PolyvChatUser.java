package org.devops.mjar.live.polyv.pojo.model;


import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
@ApiModel(description = "聊天用户")
public class PolyvChatUser extends BaseBean {
    @ApiModelProperty("用户IP")
    private String clientIp;
    @ApiModelProperty("观众昵称")
    private String nick;
    @ApiModelProperty("观众头像")
    private String pic;
    @ApiModelProperty("频道号")
    private String roomId;
    @ApiModelProperty("socket分配的id，其中 uid=1 表示打赏、uid=2 表示自定义消息、uid=3 表示红包")
    private String uid;
    @ApiModelProperty("用户唯一标示")
    private String userId;
    @ApiModelProperty("用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）")
    private String userType;
}