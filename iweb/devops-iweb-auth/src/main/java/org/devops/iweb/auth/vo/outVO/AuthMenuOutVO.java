package org.devops.iweb.auth.vo.outVO;

import java.util.ArrayList;
import java.util.List;

import org.devops.iweb.auth.model.AuthMenu;

public class AuthMenuOutVO extends AuthMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthMenuOutVO> children;

	public List<AuthMenuOutVO> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenuOutVO> children) {
		this.children = children;
	}
	

	public void addChildren(AuthMenuOutVO child) {
		if(children == null){
			children = new ArrayList<AuthMenuOutVO>();
		}
		children.add(child);
	}
	
	
	
	
}
