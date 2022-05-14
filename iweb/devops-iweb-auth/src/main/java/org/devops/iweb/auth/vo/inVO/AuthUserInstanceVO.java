package org.devops.iweb.auth.vo.inVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/12/6 15:55
 * @description：角色信息
 */
@Data
public class AuthUserInstanceVO {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户实例id")
    private Long id;

    @ApiModelProperty("应用实例id")
    private Long appInstanceId;

    @ApiModelProperty("应用实例名称")
    private String appInstanceName;

    @ApiModelProperty("用户实例角色id")
    private String roleId;

    @ApiModelProperty("用户实例角色名称")
    private String roleName;

}
