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

@Table(table="message_topic_msg_log",create=true,comment="消息日志",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="消息日志")
public class MessageTopicMsgLog extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 消息ID */
	public static final String MSG_ID = "msg_id";
	/** 主题编码 */
	public static final String TOPIC = "topic";
	/** 消费者 */
	public static final String CUSTOMER = "customer";
	/** 标签 */
	public static final String TAG = "tag";
	/** 日志 */
	public static final String LOG = "log";
	/** 执行的客户端 */
	public static final String EXEC_CLIENT = "exec_client";
	/** 发送状态 0处理中 1成功 2失败 */
	public static final String EXEC_STATUS = "exec_status";
	/** 开始执行时间 */
	public static final String EXEC_TIME = "exec_time";
	/** 执行完成时间 */
	public static final String EXEC_END_TIME = "exec_end_time";
	/** 执行任务所花时间（单位毫秒） */
	public static final String EXEC_SPAN_TIME = "exec_span_time";
	/** 心跳时间,一般心跳时间为1分钟 */
	public static final String HEART_TIME = "heart_time";

	@SuppressWarnings("serial")
	public static List<MessageTopicMsgLog> init(){
		return new ArrayList<MessageTopicMsgLog>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-message/message_topic_msg_log.sql");
	}
	
        /** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	@VerifyField(value="流水号",ignore = true)
	private Long id;
    
        /** 消息ID */
	@Column(name = "msg_id",jdbcType="bigint(20)",notNull=true,def="'-1'",extra="",comment="消息ID")
	@ApiModelProperty("消息ID")
	@VerifyField(value="消息ID",ignore = true)
	private Long msgId;
    
        /** 主题编码 */
	@Column(name = "topic",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="主题编码")
	@ApiModelProperty("主题编码")
	@VerifyField(value="主题编码",ignore = true)
	private String topic;
    
        /** 消费者 */
	@Column(name = "customer",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="消费者")
	@ApiModelProperty("消费者")
	@VerifyField(value="消费者",ignore = true)
	private String customer;
    
        /** 标签 */
	@Column(name = "tag",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="标签")
	@ApiModelProperty("标签")
	@VerifyField(value="标签",ignore = true)
	private String tag;
    
        /** 日志 */
	@Column(name = "log",jdbcType="text",notNull=false,def="",extra="",comment="日志")
	@ApiModelProperty("日志")
	@VerifyField(value="日志",ignore = true)
	private String log;
	
	/** 执行的客户端 */
	@Column(name = "exec_client",jdbcType="varchar(100)",notNull=true,def="''",extra="",comment="执行的客户端")
	@ApiModelProperty("执行的客户端")
	@VerifyField(value="执行的客户端",ignore = true)
	private String execClient;
    
        /** 发送状态 0处理中 1成功 2失败 */
	@Column(name = "exec_status",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="发送状态 0处理中 1成功 2失败")
	@ApiModelProperty("发送状态 0处理中 1成功 2失败")
	@VerifyField(value="发送状态 0处理中 1成功 2失败",ignore = true)
	private Byte execStatus;
	
	/** 执行时间 */
	@Column(name = "exec_time",jdbcType="datetime",notNull=true,def="",extra="",comment="执行时间")
	@ApiModelProperty("执行时间")
	@VerifyField(value="执行时间",ignore = true)
	private Date execTime;
	
	/** 执行时间 */
	@Column(name = "exec_end_time",jdbcType="datetime",notNull=false,def="",extra="",comment="执行完成时间")
	@ApiModelProperty("执行完成时间")
	@VerifyField(value="执行完成时间",ignore = true)
	private Date execEndTime;
	
	/** 执行任务所花时间（单位毫秒） */
	@Column(name = "exec_span_time",jdbcType="bigint(20)",notNull=true,def="0",extra="",comment="执行任务所花时间（单位毫秒）")
	@ApiModelProperty("执行任务所花时间（单位毫秒）")
	@VerifyField(value="执行任务所花时间（单位毫秒）",ignore = true)
	private Long execSpanTime;
	
	/** 心跳时间 */
	@Column(name = "heart_time",jdbcType="datetime",notNull=false,def="",extra="",comment="心跳时间")
	@ApiModelProperty("心跳时间")
	@VerifyField(value="心跳时间",ignore = true)
	private Date heartTime;
	
	public static class Dict {
		public static class ExecStatus {
			/**
			 * 处理中
			 */
			public final static byte RUNNING = 0;
			/**
			 * 已处理
			 */
			public final static byte SUCCESS = 1;
			/**
			 * 失败
			 */
			public final static byte FAILED = 2;
		}
	}
    
}
