package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：被踢人信息
 */
@Data
public class PolyvChannelKicked extends BaseBean {

    /**
     * 是否禁言
     */
    private Boolean banned;

    /**
     * 频道号
     */
    private String channelId;

    /**
     * 频道号
     */
    private String clientIp;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 自定义参数param4
     */
    private String param4;

    /**
     * 头像图片地址
     */
    private String pic;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 聊天室socketid
     */
    private String uid;

    /**
     * 用户userId
     */
    private String userId;

    /**
     * 用户身份
     * manager：管理员
     * teacher：讲师
     * assistant：助教
     * guest：嘉宾
     * viewer：参与者
     * slice/student：观看者
     */
    private String userType;





}
