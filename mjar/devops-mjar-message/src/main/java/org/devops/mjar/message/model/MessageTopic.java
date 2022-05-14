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

@Table(table="message_topic",create=true,comment="主题",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="主题")
public class MessageTopic extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 主题编码 */
	public static final String TOPIC = "topic";
	/** 激活状态 0未激活 1激活 */
	public static final String ACTIVE = "active";

	@SuppressWarnings("serial")
	public static List<MessageTopic> init(){
		return new ArrayList<MessageTopic>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-message/message_topic.sql");
	}

	
        /** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	@VerifyField(value="流水号",ignore = true)
	private Long id;
    
        /** 主题编码 */
	@Column(name = "topic",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="主题编码")
	@ApiModelProperty("主题编码")
	@VerifyField(value="主题编码",ignore = true)
	private String topic;
	
	/** 激活状态 0未激活 1激活  */
	@Column(name = "active",jdbcType="tinyint(4)",notNull=true,def="1",extra="",comment="激活状态")
	@ApiModelProperty("激活状态 0未激活 1激活")
	@VerifyField(value="激活状态",ignore = true)
	private Byte active;
    
}
