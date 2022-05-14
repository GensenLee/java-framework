package org.devops.iweb.auth.vo.outVO;

import java.util.ArrayList;
import java.util.List;

import org.devops.iweb.auth.model.AuthResources;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthResourcesOutVO extends AuthResources{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AuthResourcesOutVO> children;

	public List<AuthResourcesOutVO> getChildren() {
		return children;
	}

	public void setChildren(List<AuthResourcesOutVO> children) {
		this.children = children;
	}
	

	public void addChildren(AuthResourcesOutVO child) {
		if(children == null){
			children = new ArrayList<AuthResourcesOutVO>();
		}
		children.add(child);
	}
}
