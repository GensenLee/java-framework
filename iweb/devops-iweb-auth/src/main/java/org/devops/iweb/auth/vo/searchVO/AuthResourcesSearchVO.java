package org.devops.iweb.auth.vo.searchVO;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseSearchVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthResourcesSearchVO  extends BaseSearchVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@VerifyField("系统id")
	private Long appId;
	
	@VerifyField("实例id")
	private Long appInstanceId;
	
	private Integer type;
}
