package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author bigboss
 * @description 授权
 *
 */
@Data
public class PolyvChannelAuthExternal extends BaseBean {
    /**
     * 设置的频道号
     */
    private String channelId;

    /**
     * 频道号对应外部授权的secretKey
     */
    private String secretKey;
}
