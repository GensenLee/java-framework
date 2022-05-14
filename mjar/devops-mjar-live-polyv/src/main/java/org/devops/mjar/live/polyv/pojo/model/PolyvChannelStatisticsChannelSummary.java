package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "查询频道实时并发数据")
public class PolyvChannelStatisticsChannelSummary extends BaseBean {

        @ApiModelProperty(value = "账号Id")
        private String userId;
        @ApiModelProperty(value = "频道Id")
        private String channelId;
        @ApiModelProperty(value = "频道名称")
        private String name;
        @ApiModelProperty(value = "pc端播放时长，单位：分钟")
        private Integer pcPlayDuration;
        @ApiModelProperty(value = "pc端播放流量，单位：bytes")
        private Long pcFlowSize;
        @ApiModelProperty(value = "频播放量")
        private Integer pcVideoView;
        @ApiModelProperty(value = "pc端唯一观众数")
        private Integer pcUniqueViewer;
        @ApiModelProperty(value = "移动端播放时长，单位：分钟")
        private Integer mobilePlayDuration;
        @ApiModelProperty(value = "移动端播放流量，单位：bytes")
        private Long mobileFlowSize;

        @ApiModelProperty(value = "移动端播放量")
        private Integer mobileVideoView;
        @ApiModelProperty(value = "移动端唯一观众数")
        private Integer mobileUniqueViewer;
        @ApiModelProperty(value = "pc端直播播放时长，单位：分钟")
        private Integer livePcPlayDuration;
        @ApiModelProperty(value = "pc端回放播放时长，单位：分钟")
        private Integer playbackPcPlayDuration;
        @ApiModelProperty(value = "移动端直播播放时长，单位：分钟")
        private Integer liveMobilePlayDuration;
        @ApiModelProperty(value = "移动端回放播放时长，单位：分钟")
        private Integer playbackMobilePlayDuration;
        @ApiModelProperty(value = "pc其他播放时长，单位：分钟")
        private Integer unknownPcPlayDuration;
        @ApiModelProperty(value = "移动其他播放时长，单位：分钟")
        private Integer unknownMobilePlayDuration;
        @ApiModelProperty( value = "创建者名称")
        private String creatorName;

}
