package org.devops.iweb.auth.vo.inVO;

import java.util.List;

import org.devops.iweb.auth.model.AuthMenu;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthMenuSaveInVO extends AuthMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthMenuSaveInVO> children;

}
