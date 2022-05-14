package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 * @date 2019/4/30 18:52
 */
@Data
@ApiModel(description = "录制视频信息")
public class PolyvChannelRecordItem extends BaseBean {
    @ApiModelProperty("录制文件ID，唯一标示")
    private String fileId;
    @ApiModelProperty("频道号")
    private Integer channelId;
    @ApiModelProperty("录制视频名称")
    private String name;
    @ApiModelProperty("录制视频mp4地址(可能为空)")
    private String mp4;
    @ApiModelProperty("录制视频m3u8地址")
    private String m3u8;
    @ApiModelProperty("开始录制时间（格式为yyyyMMddHHmmss，如20170120184803）")
    private String startTime;
    @ApiModelProperty("结束录制时间（格式为yyyyMMddHHmmss，如20170120184803）")
    private String endTime;
    @ApiModelProperty("flv录制文件大小（单位：字节")
    private Long fileSize;
    @ApiModelProperty("时长（单位：秒）")
    private Integer duration;
    @ApiModelProperty("录制文件码率（单位：字节）")
    private String bitrate;
    @ApiModelProperty("录制视频宽")
    private String width;
    @ApiModelProperty("录制视频高")
    private String height;
    @ApiModelProperty("直播的场次ID")
    private String channelSessionId;
    @ApiModelProperty("频道直播类型")
    private String liveType;
    @ApiModelProperty("录制视频的剩余天数")
    private String daysLeft;
    @ApiModelProperty("用户Id")
    private String userId;
    @ApiModelProperty("时长")
    private String time;
    @ApiModelProperty("录制来源。auto-自动录制，merge-合并，clip-裁剪")
    private String origin;
    @ApiModelProperty("视频语言类型 EN英文、zh_CN中文")
	public String lang;
}
