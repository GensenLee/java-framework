package org.devops.iweb.auth.vo.inVO;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthLoginInVO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@VerifyField(value = "账号")
	private String account;
	
	@VerifyField(value = "密码")
	private String passwd;
	
	@VerifyField(ignore = true, value = "验证码")
	private String verifyCode;
	
}
