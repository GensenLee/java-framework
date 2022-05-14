package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2020/10/31 17:44
 * @description：频道统计数据
 */
@ApiModel("频道统计数据")
@Data
public class PolyvChannelStatistics extends BaseBean {

    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String name;
    @ApiModelProperty("pc端播放时长，单位：分钟")
    private Integer pcPlayDuration;
    @ApiModelProperty("pc端播放流量，单位为Byte")
    private Long pcFlowSize;
    @ApiModelProperty("pc视频播放量")
    private Integer pcVideoView;
    @ApiModelProperty("pc端唯一观众数")
    private Integer pcUniqueViewer;
    @ApiModelProperty("移动端播放时长，单位：分钟")
    private Integer mobilePlayDuration;
    @ApiModelProperty("移动端播放流量，单位为Byte")
    private Integer mobileFlowSize;
    @ApiModelProperty("移动端播放量")
    private Integer mobileVideoView;
    @ApiModelProperty("移动端唯一观众数")
    private Integer mobileUniqueViewer;
    @ApiModelProperty("PC直播播放时长，单位为分钟")
    private Integer livePcPlayDuration;
    @ApiModelProperty("PC回放播放时长，单位为分钟")
    private Integer playbackPcPlayDuration;
    @ApiModelProperty("pc 其他 播放时长，单位为分钟")
    private Integer unknownPcPlayDuration;
    @ApiModelProperty("移动端直播播放时长，单位为分钟")
    private Integer liveMobilePlayDuration;
    @ApiModelProperty("移动端回放播放时长，单位为分钟")
    private Integer playbackMobilePlayDuration;
    @ApiModelProperty("移动端其他 播放时长，单位为分钟")
    private Integer unknownMobilePlayDuration;
}
