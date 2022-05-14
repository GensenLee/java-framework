package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "查询频道某段时间的直播观看详情数据")
public class PolyvChannelStatisticsSummary extends BaseBean {

    private String status;

    private List<resultData> result;

    @Data
    static class resultData {
        @ApiModelProperty(value = "日期")
        private String currentDay;
        @ApiModelProperty(value = "账号Id")
        private String userId;
        @ApiModelProperty(value = "频道Id")
        private String channelId;
        @ApiModelProperty("pc端播放时长，单位：分钟")
        private Integer pcPlayDuration;
        @ApiModelProperty("pc端播放流量，单位为Byte")
        private Long pcFlowSize;
        @ApiModelProperty("pc视频播放量")
        private Integer pcVideoView;
        @ApiModelProperty("移动端播放时长，单位：分钟")
        private Integer mobilePlayDuration;
        @ApiModelProperty("移动端播放流量，单位为Byte")
        private Integer mobileFlowSize;
        @ApiModelProperty("移动端播放量")
        private Integer mobileVideoView;
        @ApiModelProperty("移动端唯一观众数")
        private Integer mobileUniqueViewer;
        @ApiModelProperty("记录修改的时间，13位毫秒级时间戳")
        private Long lastModified;
        @ApiModelProperty("记录添加的时间，13位毫秒级时间戳")
        private Long createdTime;
        @ApiModelProperty(value = "创建账号的子账号用户ID（为空则为主账号创建）")
        private String creatorId;
        @ApiModelProperty(value = "创建频道的子账号名称（主账号创建的则显示 主账号 ）")
        private String creatorName;
    }

}
