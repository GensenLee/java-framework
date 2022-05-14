package org.devops.mjar.live.polyv.pojo.model;


import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * 开关状态信息
 */
@Data
public class PolyvChannelSwitchState extends BaseBean {

    /**
     * 开关类型
     */
    private String type;
    /**
     * 开关值，取值Y/N
     */
    private String enabled;

}
