package org.devops.mjar.live.core.sign;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GENSEN
 * @date 2020/10/21 14:27
 * @description：校验信息
 */
@Data
@NoArgsConstructor
public class LiveApiProfiles extends BaseBean {

    private String appId;

    private String appSecret;

    private String userId;

    private String channelId;
}
