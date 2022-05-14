package org.devops.iweb.auth.vo.inVO;

import java.util.List;

import org.devops.iweb.auth.model.AuthRole;
import org.devops.iweb.auth.model.AuthRoleMenu;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthRoleSaveInVO extends AuthRole{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthRoleMenu> menus;

}
