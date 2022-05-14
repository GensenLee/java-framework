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
public class PolyvChannelRecordDetail extends BaseBean {
    @ApiModelProperty("录制文件ID")
    private String fileId;
    @ApiModelProperty("POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）")
    private String userId;
    @ApiModelProperty("频道号")
    private String channelId;
    @ApiModelProperty("开始录制时间，格式为：yyyyMMddHHmmss")
    private String startTime;
    @ApiModelProperty("结束录制时间，格式为：yyyyMMddHHmmss")
    private String endTime;
    @ApiModelProperty("录制文件名称")
    private String fileName;
    @ApiModelProperty("录制文件大小（单位：字节）")
    private Long fileSize;
    @ApiModelProperty("创建时间，13位毫秒级时间戳")
    private Long createdTime;
    @ApiModelProperty("视频宽度（像素）")
    private Integer width;
    @ApiModelProperty("视频高度（像素）")
    private Integer height;
    @ApiModelProperty("时长（单位：秒）")
    private Integer duration;
    @ApiModelProperty("录制文件码率（单位：字节）")
    private Integer bitrate;
    @ApiModelProperty("mp4文件地址")
    private String mp4;
    @ApiModelProperty("m3u8文件地址")
    private String m3u8;
    @ApiModelProperty("直播的场次ID")
    private String channelSessionId;
    @ApiModelProperty("直播类型: alone：活动直播、ppt：三分屏、topclass：大班课、seminar：研讨会")
    private String liveType;
}
