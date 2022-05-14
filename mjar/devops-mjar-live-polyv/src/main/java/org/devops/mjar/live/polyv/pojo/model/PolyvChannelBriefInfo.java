package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GENSEN
 * @date 2020/10/29 14:10
 * @description：频道简略信息
 */
@NoArgsConstructor
@Data
public class PolyvChannelBriefInfo extends BaseBean {

    @ApiModelProperty("直播频道ID")
    private String channelId;

    @ApiModelProperty("直播频道名称")
    private String name;

    @ApiModelProperty("频道密码")
    private String channelPasswd;

    @ApiModelProperty("分类id")
    private String categoryId;

    @ApiModelProperty("场景，alone-活动直播，ppt-三分屏，topclass-大班课")
    private String scene;

    @ApiModelProperty("场景描述")
    private String sceneText;

    @ApiModelProperty("观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始")
    private String watchStatus;

    @ApiModelProperty("观看页状态描述，直播中，回放中，已结束，未开始")
    private String watchStatusText;

    @ApiModelProperty("观看页链接")
    private String watchUrl;
}
