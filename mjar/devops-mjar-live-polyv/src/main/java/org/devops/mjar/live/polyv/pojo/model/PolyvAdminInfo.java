package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：管理员信息
 */
@Data
public class PolyvAdminInfo extends BaseBean {

    /**
     * 管理员头衔
     */
    private String actor;

    /**
     * 管理员昵称
     */
    private String nickname;

    /**
     * 管理员头像
     */
    private String avatar;
}
