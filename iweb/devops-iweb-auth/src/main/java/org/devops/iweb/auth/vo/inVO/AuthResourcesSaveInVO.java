package org.devops.iweb.auth.vo.inVO;

import java.util.ArrayList;
import java.util.List;

import org.devops.iweb.auth.model.AuthResources;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthResourcesSaveInVO extends AuthResources{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthResourcesSaveInVO> children;

	public List<AuthResourcesSaveInVO> getChildren() {
		return children;
	}

	public void setChildren(List<AuthResourcesSaveInVO> children) {
		this.children = children;
	}
	
	public void addChildren(AuthResourcesSaveInVO child) {
		if(children == null){
			children = new ArrayList<AuthResourcesSaveInVO>();
		}
		children.add(child);
	}
	
	
	
	
}
