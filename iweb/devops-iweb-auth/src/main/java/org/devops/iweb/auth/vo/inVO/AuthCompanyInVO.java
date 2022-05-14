package org.devops.iweb.auth.vo.inVO;

import org.devops.iweb.auth.model.AuthCompany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthCompanyInVO extends AuthCompany{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long appId;
}
