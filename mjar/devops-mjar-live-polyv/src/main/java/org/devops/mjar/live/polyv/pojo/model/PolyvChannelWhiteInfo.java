package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author bigboss
 * @description: 白名单人员信息
 */
@Data
public class PolyvChannelWhiteInfo extends BaseBean {

    /**
     * 昵称(或备注)
     */
    private String name;

    /**
     * 会员码
     */
    private String phone;

}
