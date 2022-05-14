package org.devops.mjar.weixin.model;

import java.util.ArrayList;
import java.util.List;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

@Table(table="weixin_config",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class WeixinConfig extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	public static final String ID = "id";
	/** 设置微信小程序的appid */
	public static final String APP_ID = "app_id";
	/** 设置微信小程序的Secret */
	public static final String SECRET = "secret";
	/** 设置微信小程序消息服务器配置的token */
	public static final String TOKEN = "token";
	/** 设置微信小程序消息服务器配置的EncodingAESKey */
	public static final String ASE_KYE = "ase_kye";
	/** 消息格式，XML或者JSON */
	public static final String MSG_DATA_FORMAT = "msg_data_format";
	/** 配置类型 */
	public static final String TYPE = "type";
	/** 微信支付商户号 */
	public static final String MCH_ID = "mch_id";
	/** 微信支付商户密钥 */
	public static final String MCH_KEY = "mch_key";
	/** 服务商模式下的子商户公众账号ID */
	public static final String SUB_APP_ID = "sub_app_id";
	/** 服务商模式下的子商户号 */
	public static final String SUB_MCH_ID = "sub_mch_id";
	/** apiclient_cert.p12文件的绝对路径 */
	public static final String KEY_PATH = "key_path";

	@SuppressWarnings("serial")
	public static List<WeixinConfig> init(){
		return new ArrayList<WeixinConfig>(){{
		}};
	}
	
        /** 主键 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="主键")
	@ApiModelProperty("主键")
	@VerifyField(value="主键",ignore = true)
	private Long id;

	/** 配置类型 ma-小程序、mp-公众号、pay-微信支付 */
	@Column(name = "type",jdbcType="varchar(16)",notNull=true,def="'ma'",comment="配置类型 ma-小程序、mp-公众号、pay-微信支付")
	@ApiModelProperty("配置类型 ma-小程序、mp-公众号、pay-微信支付")
	@VerifyField(value="配置类型",ignore = true)
	private String type;

        /** 设置微信小程序的appid */
	@Column(name = "app_id",jdbcType="varchar(255)",notNull=true,def="''",comment="设置微信小程序的appid")
	@ApiModelProperty("设置微信小程序的appid")
	@VerifyField(value="设置微信小程序的appid",ignore = true)
	private String appId;
    
        /** 设置微信小程序的Secret */
	@Column(name = "secret",jdbcType="varchar(255)",notNull=true,def="''",comment="设置微信小程序的Secret")
	@ApiModelProperty("设置微信小程序的Secret")
	@VerifyField(value="设置微信小程序的Secret",ignore = true)
	private String secret;
    
        /** 设置微信小程序消息服务器配置的token */
	@Column(name = "token",jdbcType="varchar(255)",notNull=true,def="''",comment="设置微信小程序消息服务器配置的token")
	@ApiModelProperty("设置微信小程序消息服务器配置的token")
	@VerifyField(value="设置微信小程序消息服务器配置的token",ignore = true)
	private String token;
    
        /** 设置微信小程序消息服务器配置的EncodingAESKey */
	@Column(name = "ase_kye",jdbcType="varchar(255)",notNull=true,def="''",comment="设置微信小程序消息服务器配置的EncodingAESKey")
	@ApiModelProperty("设置微信小程序消息服务器配置的EncodingAESKey")
	@VerifyField(value="设置微信小程序消息服务器配置的EncodingAESKey",ignore = true)
	private String aseKye;
    
        /** 消息格式，XML或者JSON */
	@Column(name = "msg_data_format",jdbcType="varchar(16)",notNull=true,def="''",comment="消息格式，XML或者JSON")
	@ApiModelProperty("消息格式，XML或者JSON")
	@VerifyField(value="消息格式，XML或者JSON",ignore = true)
	private String msgDataFormat;


	/** 微信支付商户号 */
	@Column(name = "mch_id",jdbcType="varchar(255)",notNull=true,def="''",comment="微信支付商户号")
	@ApiModelProperty("微信支付商户号")
	@VerifyField(value="微信支付商户号",ignore = true)
	private String mchId;

	/** 微信支付商户密钥 */
	@Column(name = "mch_key",jdbcType="varchar(255)",notNull=true,def="''",comment="微信支付商户密钥")
	@ApiModelProperty("微信支付商户密钥")
	@VerifyField(value="微信支付商户密钥",ignore = true)
	private String mchKey;

	/** 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除 */
	@Column(name = "sub_app_id",jdbcType="varchar(255)",notNull=true,def="''",comment="服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除")
	@ApiModelProperty("服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除")
	@VerifyField(value="服务商模式下的子商户公众账号ID",ignore = true)
	private String subAppId;

	/** 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除 */
	@Column(name = "sub_mch_id",jdbcType="varchar(255)",notNull=true,def="''",comment="服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除")
	@ApiModelProperty("服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除")
	@VerifyField(value="服务商模式下的子商户号",ignore = true)
	private String subMchId;

	/** apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定 */
	@Column(name = "key_path",jdbcType="varchar(255)",notNull=true,def="''",comment="apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定")
	@ApiModelProperty("apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定")
	@VerifyField(value="apiclient_cert.p12文件的绝对路径",ignore = true)
	private String keyPath;


	public static class Dict {
		public static class Type {
			public static final String MA = "ma";
			public static final String MP = "mp";
			public static final String PAY = "pay";
			public static final String OPEN = "open";
		}
	}

}
