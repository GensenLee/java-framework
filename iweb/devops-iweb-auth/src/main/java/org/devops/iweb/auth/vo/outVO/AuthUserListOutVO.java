package org.devops.iweb.auth.vo.outVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.vo.inVO.AuthUserInstanceVO;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/12/6 15:59
 * @description：用户列表
 */
@Data
public class AuthUserListOutVO extends AuthUser {

    @ApiModelProperty("用户实例信息")
    private List<AuthUserInstanceVO> instanceList;

}
