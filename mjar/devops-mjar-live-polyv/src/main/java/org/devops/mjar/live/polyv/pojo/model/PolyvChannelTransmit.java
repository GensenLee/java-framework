package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

@Data
public class PolyvChannelTransmit extends BaseBean {

    /**
     * 发起转播频道号，如果一个接收转播频道没有关联主频道，则该值为null
     */
    private String channelId;

    /**
     * 接收转播频道号
     */
    private String receiveChannelId;
}
