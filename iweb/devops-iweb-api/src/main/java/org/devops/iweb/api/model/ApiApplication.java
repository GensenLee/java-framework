package org.devops.iweb.api.model;

import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(value = "api_application",create = true,comment = "应用",parametric = true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "应用",description="应用")
public class ApiApplication extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 应用ID */
	public static final String APP_ID = "app_id";
	/** secret */
	public static final String APP_SECRET = "app_secret";
	/** 用户第三方ID */
	public static final String USER_ID = "user_id";
	/** 消息加密串，长度一般为43位 */
	public static final String ENCODING_AES_KEY = "encoding_aes_key";
	/** 是否激活 (0否 1是) */
	public static final String IS_ACTIVE = "is_active";
	/** accessToken过期时间，默认7200秒 */
	public static final String EXPIRES_IN = "expiresIn";

	
        /** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true,comment="流水号")
	@ApiModelProperty("流水号")
	private Long id;
    
        /** 应用ID */
	@Column(name = "app_id",jdbcType="varchar(200)",notNull=true,def="''",comment="应用ID")
	@ApiModelProperty("应用ID")
	private String appId;
    
        /** secret */
	@Column(name = "app_secret",jdbcType="varchar(200)",notNull=true,def="''",comment="secret")
	@ApiModelProperty("secret")
	private String appSecret;
    
        /** 用户第三方ID */
	@Column(name = "user_id",jdbcType="varchar(200)",notNull=true,def="''",comment="用户第三方ID")
	@ApiModelProperty("用户第三方ID")
	private String userId;
	
	
	 /** 消息加密串，长度一般为43位 */
	@Column(name = "encoding_aes_key",jdbcType="varchar(200)",notNull=true,def="''",comment="消息加密串，长度一般为43位")
	@ApiModelProperty("消息加密串，长度一般为43位")
	private String encodingAesKey;
    
        /** 是否激活 (0否 1是) */
	@Column(name = "is_active",jdbcType="tinyint(3)",notNull=true,def="1",comment="是否激活 (0否 1是)")
	@ApiModelProperty("是否激活 (0否 1是)")
	private Byte isActive;
    
        /** accessToken过期时间，默认7200秒 */
	@Column(name = "expiresIn",jdbcType="int(11)",notNull=true,def="7200",comment="accessToken过期时间，默认7200秒")
	@ApiModelProperty("accessToken过期时间，默认7200秒")
	private Integer expiresIn;
    
}
