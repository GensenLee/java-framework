package org.devops.mjar.live.core.sign;

import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.helper.PolyvSignHelper;
import org.devops.core.utils.util.StringUtil;
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
public abstract class RootSignBean extends RequestHandleBean {

    /**
     * 应用id
     */
    @VerifyField(ignore = true)
    private String appId;

    /**
     * 时间戳
     */
    @VerifyField(ignore = true)
    private Long timestamp;

    /**
     * 签名
     */
    @VerifyField(ignore = true)
    private String sign;

    void init() throws CommonException {
        if (StringUtil.isEmpty(appId)) {
            throw new CommonException("appId缺失");
        }
        timestamp = System.currentTimeMillis();
    }

    @Override
    public void sign(LiveApiProfiles auth) throws CommonException {
        setAppId(auth.getAppId());
        init();
        Map<String, Object> map = this.toMap();
        String sign = PolyvSignHelper.sign(map, auth.getAppSecret());
        setSign(sign);
    }
}
