package org.devops.iweb.auth.vo.inVO;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthUserRepasswdInVO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@VerifyField("原始密码")
	private String passwd;
	
	@VerifyField("确认密码")
	private String newPasswd;

	
}
