package org.devops.mjar.live.core.sign;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.helper.PolyvSignHelper;
import org.devops.core.utils.verify.VerifyField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 应用管理员通用参数类型
 * @author GENSEN
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ChannelSignBean extends AppSignBean {

    /**
     * 频道号
     */
    @VerifyField(ignore = true)
    public String channelId;

    @Override
    public void sign(LiveApiProfiles auth) throws CommonException {
        setChannelId(auth.getChannelId());
        setAppId(auth.getAppId());
        setUserId(auth.getUserId());
        init();
        Map<String, Object> map = this.toMap();
        String sign = PolyvSignHelper.sign(map, auth.getAppSecret());
        setSign(sign);
    }
}
