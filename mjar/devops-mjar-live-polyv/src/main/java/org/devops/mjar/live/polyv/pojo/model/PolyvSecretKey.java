package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 */
@Data
@ApiModel("频道号独立授权的secretkey")
public class PolyvSecretKey extends BaseBean {

    @ApiModelProperty("授权类型，判断为 “direct”是独立授权，其他值为其他方式授权")
    private String authType;
    @ApiModelProperty("独立授权的secretkey(只有在 authType = “direct” 时该参数才会提供)")
    private String secretKey;

}
