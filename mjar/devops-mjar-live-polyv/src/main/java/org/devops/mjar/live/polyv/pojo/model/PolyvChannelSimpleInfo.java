package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("频道缩略信息")
public class PolyvChannelSimpleInfo extends BaseBean {
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String name;
    @ApiModelProperty("频道密码")
    private String channelPasswd;
    @ApiModelProperty("频道分类id")
    private String categoryId;
    @ApiModelProperty("频道状态描述")
    private String statusDiscription;
    @ApiModelProperty("频道状态")
    private String status;
    @ApiModelProperty("所属应用appId")
    private String appId;
    @ApiModelProperty("所属应用userId")
    private String userId;
    @ApiModelProperty("创建时间")
    private Long createdTime;
    @ApiModelProperty("暖场照片")
    private String coverImage;
}
