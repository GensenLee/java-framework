package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel(description = "聊天记录")
public class PolyvChannelChatHistory extends BaseBean {
    @ApiModelProperty("用户IP")
    private String clientIP;
    @ApiModelProperty("聊天内容")
    private Object content;
    @ApiModelProperty("聊天消息id")
    private String id;
    @ApiModelProperty("图片消息的图片地址")
    private String image;
    @ApiModelProperty("频道号")
    private String roomId;
    @ApiModelProperty("发送消息时的时间戳")
    private String time;
    @ApiModelProperty("用户信息")
    private PolyvChatUser user;

}
