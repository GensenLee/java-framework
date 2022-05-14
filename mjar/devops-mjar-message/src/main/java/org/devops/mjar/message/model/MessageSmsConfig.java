package org.devops.mjar.message.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(table="message_sms_config",create=true,comment="短信服务配置",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="短信服务配置")
public class MessageSmsConfig extends BaseBean{
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    public static final String ID = "id";
    /** 集团ID */
    public static final String COMPANY_ID = "company_id";
    /** secretId */
    public static final String SECRET_ID = "secret_id";
    /** secretKey */
    public static final String SECRET_KEY = "secret_key";
    /** sdkAppId */
    public static final String SDK_APP_ID = "sdk_app_id";
    /** endpoint */
    public static final String ENDPOINT = "endpoint";
    /** region */
    public static final String REGION = "region";
    /**  是否激活  0否 1是 */
    public static final String ACTIVE = "active";
	/**  类型(ALIYUN,TENCENT) */
	public static final String TYPE = "type";

	@SuppressWarnings("serial")
	public static List<MessageSmsConfig> init(){
		return new ArrayList<MessageSmsConfig>(){{
		}};
	}

		/** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	@VerifyField(value="流水号",ignore = true)
	private Long id;
	
		/** 集团ID */
	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="'-1'",extra="",comment="集团ID")
	@ApiModelProperty("集团ID")
	@VerifyField(value="集团ID",ignore = true)
	private Long companyId;
	
		/** secretId */
	@Column(name = "secret_id",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="secretId")
	@ApiModelProperty("secretId")
	@VerifyField(value="secretId",ignore = true)
	private String secretId;
	
		/** secretKey */
	@Column(name = "secret_key",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="secretKey")
	@ApiModelProperty("secretKey")
	@VerifyField(value="secretKey",ignore = true)
	private String secretKey;
	
		/** sdkAppId */
	@Column(name = "sdk_app_id",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="sdkAppId")
	@ApiModelProperty("sdkAppId")
	@VerifyField(value="sdkAppId",ignore = true)
	private String sdkAppId;
	
		/** endpoint */
	@Column(name = "endpoint",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="endpoint")
	@ApiModelProperty("endpoint")
	@VerifyField(value="endpoint",ignore = true)
	private String endpoint;
	
		/** region */
	@Column(name = "region",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="region")
	@ApiModelProperty("region")
	@VerifyField(value="region",ignore = true)
	private String region;
	
		/**  是否激活  0否 1是 */
	@Column(name = "active",jdbcType="tinyint(4)",notNull=true,def="1",extra="",comment=" 是否激活  0否 1是")
	@ApiModelProperty(" 是否激活  0否 1是")
	@VerifyField(value=" 是否激活  0否 1是",ignore = true)
	private Byte active;

	/** 类型 */
	@Column(name = "type",jdbcType="varchar(16)",notNull=true,def="'ALIYUN'",extra="",comment="类型(ALIYUN,TENCENT)")
	@ApiModelProperty("type")
	@VerifyField(value="type",ignore = true)
	private String type;

	public static class Dict {
		public interface Type {
			/**
			 * 阿里云短信
			 */
			String ALIYUN = "ALIYUN";
			/**
			 * 腾讯短信
			 */
			String TENCENT = "TENCENT";
		}
	}
}
