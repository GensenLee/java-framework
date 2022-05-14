package org.devops.iweb.api.vo.oauth2.dto;

import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel("IWebApiOAuth2AccessTokenDTO")
public class IWebApiOAuth2AccessTokenDTO extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("accessToken")
	private String accessToken;
	
	// private String refreshToken;
	@ApiModelProperty("expiresIn")
	private Integer expiresIn;

}
