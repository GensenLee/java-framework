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
import java.util.Date;
import java.util.List;

@Table(table="message_topic_msg",create=true,comment="主题消息",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="主题消息")
public class MessageTopicMsg extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 主题编码 */
	public static final String TOPIC = "topic";
	/** 标签 */
	public static final String TAG = "tag";
	/** 消息体 */
	public static final String BODY = "body";
	/** 处理状态 0未处理 1处理中 2异常 3处理完成 */
	public static final String STATUS = "status";
	/** 执行的客户端,如果执行了多次,用逗号隔开 */
	public static final String EXEC_CLIENT = "exec_client";
	/** 创建时间 */
	public static final String CREATE_TIME = "create_time";
	/** 执行时间 */
	public static final String EXEC_TIME = "exec_time";
	/** 执行的消费者 */
	public static final String EXEC_CUSTOMER = "exec_customer";

	@SuppressWarnings("serial")
	public static List<MessageTopicMsg> init(){
		return new ArrayList<MessageTopicMsg>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-message/message_topic_msg.sql");
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
    
        /** 标签 */
	@Column(name = "tag",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="标签")
	@ApiModelProperty("标签")
	@VerifyField(value="标签",ignore = true)
	private String tag;
    
        /** 消息体 */
	@Column(name = "body",jdbcType="varchar(2000)",notNull=true,def="''",extra="",comment="消息体")
	@ApiModelProperty("消息体")
	@VerifyField(value="消息体",ignore = true)
	private String body;
    
        /** 处理状态 0未处理 1处理完成 */
	@Column(name = "status",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="处理状态 0未处理 1处理完成")
	@ApiModelProperty("处理状态0未处理 1处理完成")
	@VerifyField(value="处理状态 0未处理 1处理完成",ignore = true)
	private Byte status;
	
	/** 执行的客户端,如果执行了多次,用逗号隔开 */
	@Column(name = "exec_client",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="执行的客户端")
	@ApiModelProperty("执行的客户端")
	@VerifyField(value="执行的客户端",ignore = true)
	private String execClient;
    
        /** 创建时间 */
	@Column(name = "create_time",jdbcType="datetime",notNull=true,def="",extra="",comment="创建时间")
	@ApiModelProperty("创建时间")
	@VerifyField(value="创建时间",ignore = true)
	private Date createTime;
	
	/** 执行时间 */
	@Column(name = "exec_time",jdbcType="datetime",notNull=false,def="",extra="",comment="执行时间")
	@ApiModelProperty("执行时间")
	@VerifyField(value="执行时间",ignore = true)
	private Date execTime;
	
	/** 执行的消费者*/
	@Column(name = "exec_customer",jdbcType="varchar(5000)",notNull=true,def="''",extra="",comment="执行的消费者")
	@ApiModelProperty("执行的消费者")
	@VerifyField(value="执行的消费者",ignore = true)
	private String execCustomer;
	
	public static class Dict {
		public static class Status {
			/**
			 * 未处理
			 */
			public final static byte WAIT = 0;
			/**
			 * 已处理
			 */
			public final static byte FINASH = 1;
		}
	}
    
}
