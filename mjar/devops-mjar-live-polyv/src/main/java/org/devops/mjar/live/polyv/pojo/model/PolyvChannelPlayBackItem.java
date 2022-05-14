package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gensen.Lee
 * @date 2019/4/30 19:04
 */
@Data
@ApiModel(description = "回放列表项")
public class PolyvChannelPlayBackItem extends BaseBean {

    @ApiModelProperty("直播系统生成的id")
    private String videoId;
    @ApiModelProperty("点播视频vid")
    private String videoPoolId;
    @ApiModelProperty("点播后台用户id")
    private String userId;
    @ApiModelProperty("回放视频对应的直播频道id")
    private String channelId;
    @ApiModelProperty("视频标题")
    private String title;
    @ApiModelProperty("视频首图")
    private String firstImage;
    @ApiModelProperty("视频长度")
    private String duration;
    @ApiModelProperty("默认视频的播放清晰度，1为流畅，2为高清，3为超清")
    private String myBr;
    @ApiModelProperty("访客信息收集id")
    private String qid;
    @ApiModelProperty("视频加密状态，1表示为加密状态，0为非加密")
    private Integer seed;
    @ApiModelProperty(value = "",hidden = true)
    private Long ordertime;
    @ApiModelProperty("添加为回放视频的日期")
    private Long createdTime;
    @ApiModelProperty("视频最后修改日期")
    private Long lastModified;
    @ApiModelProperty(value = "",hidden = true)
    private Integer rank;
    @ApiModelProperty("是否为默认播放视频，值为Y/N")
    private String asDefault;
    @ApiModelProperty("视频播放地址，注：如果视频为加密视频，则此地址无法访问")
    private String url;
    @ApiModelProperty("用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null")
    private String channelSessionId;
    @ApiModelProperty("视频合并信息，后续补充")
    private String mergeInfo;
    @ApiModelProperty(value = "",hidden = true)
    private String status;
    @ApiModelProperty(value = "",hidden = true)
    private String fileUrl;
    @ApiModelProperty(value = "",hidden = true)
    private String fileId;
    @ApiModelProperty("直播开始时间")
    private String startTime;
    @ApiModelProperty(value = "",hidden = true)
    private String liveType;
    @ApiModelProperty("转存后的回放视频对应的mp4")
    private String mp4;
    @ApiModelProperty("视频语言类型 EN英文、zh_CN中文")
	public String lang;
}
