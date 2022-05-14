package org.devops.iweb.auth.vo.searchVO;

import org.devops.core.utils.vo.BaseSearchVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AuthAppSearchVO  extends BaseSearchVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer type;

}
