package org.devops.mjar.message.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import java.util.ArrayList;
import java.util.List;

@Table(table="message_email_template",create=true,comment="邮件模板",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="邮件模板")
public class MessageEmailTemplate extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 伙伴ID */
	public static final String PARTNER_ID = "partner_id";
	/** 模板编码 */
	public static final String NO = "no";
	/**  是否激活  0否 1是 */
	public static final String ACTIVE = "active";
	/** 内容 */
	public static final String CONTENT = "content";
	/** 主题 */
	public static final String SUBJECT = "subject";
	/** 类型 TEXT(文本) HTML(html) */
	public static final String TYPE = "type";

	@SuppressWarnings("serial")
	public static List<MessageEmailTemplate> init(){
		return new ArrayList<MessageEmailTemplate>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-message/message_email_template.sql");
	}
	
        /** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	@VerifyField(value="流水号",ignore = true)
	private Long id;
    
        /** 伙伴ID */
	@Column(name = "partner_id",jdbcType="bigint(20)",notNull=true,def="'-1'",extra="",comment="伙伴ID")
	@ApiModelProperty("伙伴ID")
	@VerifyField(value="伙伴ID",ignore = true)
	private Long partnerId;
    
        /** 模板编码 */
	@Column(name = "no",jdbcType="varchar(200)",notNull=true,def="''",extra="",comment="模板编码")
	@ApiModelProperty("模板编码")
	@VerifyField(value="模板编码",ignore = true)
	private String no;
    
        /**  是否激活  0否 1是 */
	@Column(name = "active",jdbcType="tinyint(4)",notNull=true,def="1",extra="",comment=" 是否激活  0否 1是")
	@ApiModelProperty(" 是否激活  0否 1是")
	@VerifyField(value=" 是否激活  0否 1是",ignore = true)
	private Byte active;
    
        /** 内容 */
	@Column(name = "content",jdbcType="varchar(5000)",notNull=true,def="''",extra="",comment="内容")
	@ApiModelProperty("内容")
	@VerifyField(value="内容",ignore = true)
	private String content;
	
	/** 内容 */
	@Column(name = "subject",jdbcType="varchar(200)",notNull=true,def="''",extra="",comment="主题")
	@ApiModelProperty("主题")
	@VerifyField(value="主题",ignore = true)
	private String subject;
    
        /** 类型 TEXT(文本) HTML(html) */
	@Column(name = "type",jdbcType="varchar(20)",notNull=true,def="'HTML'",extra="",comment="类型 TEXT(文本) HTML(html)")
	@ApiModelProperty("类型 TEXT(文本) HTML(html)")
	@VerifyField(value="类型 TEXT(文本) HTML(html)",ignore = true)
	private String type;
    
}
