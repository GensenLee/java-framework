package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangzy
 */
@Data
@ApiModel(description = "录制视频信息")
public class PolyvChannelRecordInfo extends BaseBean {
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("录制文件ID")
    private String fileId;
    @ApiModelProperty("录制文件地址，优先返回MP4，若没有MP4会返回M3U8")
    private String url;
    @ApiModelProperty("开始录制时间，格式为：yyyyMMddHHmmss")
    private String startTime;
    @ApiModelProperty("结束录制时间，格式为：yyyyMMddHHmmss")
    private String endTime;
    @ApiModelProperty("录制文件大小（单位：字节）")
    private Long fileSize;
    @ApiModelProperty("时长（单位：秒）")
    private Integer duration;
    @ApiModelProperty("录制文件码率（单位：字节）")
    private Integer bitrate;
    @ApiModelProperty("分辨率，示例：1280x720")
    private String resolution;
    @ApiModelProperty("直播的场次ID")
    private String channelSessionId;
    @ApiModelProperty("录制文件名称")
    private String fileName;
}
