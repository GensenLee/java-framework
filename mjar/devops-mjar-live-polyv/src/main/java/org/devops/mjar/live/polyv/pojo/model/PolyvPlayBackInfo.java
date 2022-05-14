package org.devops.mjar.live.polyv.pojo.model;

import lombok.Data;
import org.devops.core.utils.vo.BaseBean;

/**
 * @author fangzy
 * @description：频道单个回放信息
 */
@Data
public class PolyvPlayBackInfo extends BaseBean {

    /**
     * 频道ID
     */
    private Integer channelId;

    /**
     * 点播系统VID（常用该字段）
     */
    private String vid;

    /**
     * 直播系统生成的id （不常用，视频库中的回放视频）
     */
    private String videoId;

    /**
     * 点播视频videoPoolId （不常用）
     */
    private String videoPoolId;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频首图
     */
    private String firstImg;

    /**
     * 视频时间，如：05:15:01
     */
    private String duration;

}
