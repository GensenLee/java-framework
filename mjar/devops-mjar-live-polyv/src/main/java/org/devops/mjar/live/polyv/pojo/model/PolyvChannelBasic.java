package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "频道基础信息")
public class PolyvChannelBasic extends BaseBean {

    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String name;
    @ApiModelProperty("主持人名称")
    private String publisher;
    @ApiModelProperty("直播开始时间，关闭时为0，开启时为13位毫秒级时间戳")
    private Long startTime;
    @ApiModelProperty("页面累计观看数")
    private Integer pageView;
    @ApiModelProperty("观看页点赞数")
    private Integer likes;
    @ApiModelProperty("频道图标url")
    private String coverImg;
    @ApiModelProperty("频道引导图url")
    private String splashImg;
    @ApiModelProperty("引导页开关, Y开启, N关闭")
    private String splashEnabled;
    @ApiModelProperty("直播介绍")
    private String desc;
    @ApiModelProperty("最大在线观看人数")
    private Integer maxViewer;
    @ApiModelProperty("频道的观看页状态： live：直播中, end：直播结束, playback：回放中, waiting：等待直播")
    private String watchStatus;
    @ApiModelProperty("观看页状态描述，直播中，回放中，已结束，未开始")
    private String watchStatusText;
    @ApiModelProperty("在线人数")
    private Integer onlineNum;
    @ApiModelProperty("暖场图片URL")
    private String bgImg;
    @ApiModelProperty("分类ID")
    private String categoryId;
    @ApiModelProperty("回放视频列表")
    private List<videoVo> videoList;

    @Data
    public static class videoVo {
        @ApiModelProperty("直播系统生成的id（视频库中的回放视频）")
        private String videoId;
        @ApiModelProperty("点播视频vid（视频库中的回放视频）")
        private String videoPoolId;
    }

}
