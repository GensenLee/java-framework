package org.devops.core.utils.modules.apiEnhancer.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.utils.vo.BaseBean;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiEnhancerDataMemoryVO extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long expire = -1;
	
	private String value;
	
	public boolean isExpired() {
		if(expire == -1) {
			return false;
		}
		return expire < System.currentTimeMillis();
	}
	
}
