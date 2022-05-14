package org.devops.iweb.auth.vo.inVO;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthToInstanceInVO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 目标实例id
	 */
	@VerifyField("用户系统实例ID")
	private Long userInstanceId;
}
