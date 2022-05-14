package org.devops.mjar.live.polyv.pojo.model;

import lombok.Data;
import org.devops.core.utils.vo.BaseBean;

/**
 * @author fangzy
 * @description：伪直播信息
 */
@Data
public class PolyvDiskVideo extends BaseBean {

    /**
     * 状态 Y未推流 N已推流
     */
    private String status;

    /**
     * 频道id
     */
    private Integer channelId;

    /**
     * 语言类型（CN：中文， EN：英文）
     */
    private String langType;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频地址
     */
    private String url;

    /**
     * 助教最后修改时间
     */
    private long lastModified;

    /**
     * 点播视频ID
     */
    private String videoPoolId;

    /**
     * 伪直播视频id
     */
    private String videoId;

    /**
     * 创建时间
     */
    private long createdTime;

    /**
     * 视频开始播放时间，例如 1500496639000。可能为 null，表示该视频尚未设置开始播放时间。
     */
    private long startTime;

    /**
     * 视频结束播放时间，例如 1500496639000。
     */
    private long endTime;

    /**
     * 视频时长，例如 "01:03:49"
     */
    private String duration;

    /**
     * 视频预览图，可能为绝对地址或相对地址，例如：http://img.videocc.net/uimage/2/259be3cc91/c/259be3cc9124139f067bddb0b1233cac_5.jpg或259be3cc91/c/259be3cc9124139f067bddb0b1233cac_5.jpg
     */
    private String firstImage;

    /**
     * 流状态 waiting-等待中 live-直播中 end-已结束
     */
    private String streamStatus;

}
