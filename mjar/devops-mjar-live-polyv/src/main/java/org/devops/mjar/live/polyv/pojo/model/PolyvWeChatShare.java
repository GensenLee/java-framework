package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

@Data
public class PolyvWeChatShare extends BaseBean {
    /**
     * 频道号
     */
    private String channelId;
    /**
     * 频道名称
     */
    private String channelName;
    /**
     * 微信分享图标，即频道的直播图标
     */
    private String coverImg;
    /**
     * 微信分享的标题
     */
    private String weixinShareTitle;
    /**
     * 微信分享的描述
     */
    private String weixinShareDesc;
    /**
     * 微信分享客户自定义url
     */
    private String weixinShareCustomUrl;
    /**
     * 网页分享客户自定义url
     */
    private String webShareCustomUrl;


}
