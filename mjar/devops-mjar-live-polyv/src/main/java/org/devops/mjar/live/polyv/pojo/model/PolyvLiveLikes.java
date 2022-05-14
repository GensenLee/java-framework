package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "直播开始时间信息")
public class PolyvLiveLikes extends BaseBean {
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道点赞数")
    private Integer likes;
    @ApiModelProperty("频道观看热度")
    private Integer viewers;
}
