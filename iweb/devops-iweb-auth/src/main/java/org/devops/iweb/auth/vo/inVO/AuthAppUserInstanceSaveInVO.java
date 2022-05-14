package org.devops.iweb.auth.vo.inVO;

import java.util.List;

import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthAppUserInstanceMenu;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthAppUserInstanceSaveInVO extends AuthAppUserInstance{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthAppUserInstanceMenu> menus;

}
