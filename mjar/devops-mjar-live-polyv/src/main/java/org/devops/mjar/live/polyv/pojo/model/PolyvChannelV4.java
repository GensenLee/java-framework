package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：频道信息
 */
@Data
public class PolyvChannelV4 extends BaseBean {
    /**
     * 频道ID
     */
    private String channelId;

    /**
     * POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）
     */
    private String userId;
}
