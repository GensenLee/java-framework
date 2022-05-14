package org.devops.iweb.xxl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(table="xxl_job_log",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobLog extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/** 执行器主键ID */
	public static final String JOB_GROUP = "job_group";
	/** 任务，主键ID */
	public static final String JOB_ID = "job_id";
	/** 执行器地址，本次执行的地址 */
	public static final String EXECUTOR_ADDRESS = "executor_address";
	/** 执行器任务handler */
	public static final String EXECUTOR_HANDLER = "executor_handler";
	/** 执行器任务参数 */
	public static final String EXECUTOR_PARAM = "executor_param";
	/** 执行器任务分片参数，格式如 1/2 */
	public static final String EXECUTOR_SHARDING_PARAM = "executor_sharding_param";
	/** 失败重试次数 */
	public static final String EXECUTOR_FAIL_RETRY_COUNT = "executor_fail_retry_count";
	/** 调度-时间 */
	public static final String TRIGGER_TIME = "trigger_time";
	/** 调度-结果 */
	public static final String TRIGGER_CODE = "trigger_code";
	/** 调度-日志 */
	public static final String TRIGGER_MSG = "trigger_msg";
	/** 执行-时间 */
	public static final String HANDLE_TIME = "handle_time";
	/** 执行-状态 */
	public static final String HANDLE_CODE = "handle_code";
	/** 执行-日志 */
	public static final String HANDLE_MSG = "handle_msg";
	/** 告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败 */
	public static final String ALARM_STATUS = "alarm_status";

	@SuppressWarnings("serial")
	public static List<XxlJobLog> init(){
		return new ArrayList<XxlJobLog>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_log.sql");
	}
	
        /**  */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Long id;
    
        /** 执行器主键ID */
	@Column(name = "job_group",jdbcType="int(11)",notNull=true,def="0",extra="",comment="执行器主键ID")
	@ApiModelProperty("执行器主键ID")
	@VerifyField(value="执行器主键ID",ignore = true)
	private Integer jobGroup;
    
        /** 任务，主键ID */
	@Column(name = "job_id",jdbcType="int(11)",notNull=true,def="0",extra="",comment="任务，主键ID")
	@ApiModelProperty("任务，主键ID")
	@VerifyField(value="任务，主键ID",ignore = true)
	private Integer jobId;
    
        /** 执行器地址，本次执行的地址 */
	@Column(name = "executor_address",jdbcType="varchar(255)",notNull=false,def="",extra="",comment="执行器地址，本次执行的地址")
	@ApiModelProperty("执行器地址，本次执行的地址")
	@VerifyField(value="执行器地址，本次执行的地址",ignore = true)
	private String executorAddress;
    
        /** 执行器任务handler */
	@Column(name = "executor_handler",jdbcType="varchar(255)",notNull=false,def="",extra="",comment="执行器任务handler")
	@ApiModelProperty("执行器任务handler")
	@VerifyField(value="执行器任务handler",ignore = true)
	private String executorHandler;
    
        /** 执行器任务参数 */
	@Column(name = "executor_param",jdbcType="varchar(512)",notNull=false,def="",extra="",comment="执行器任务参数")
	@ApiModelProperty("执行器任务参数")
	@VerifyField(value="执行器任务参数",ignore = true)
	private String executorParam;
    
        /** 执行器任务分片参数，格式如 1/2 */
	@Column(name = "executor_sharding_param",jdbcType="varchar(20)",notNull=false,def="",extra="",comment="执行器任务分片参数，格式如 1/2")
	@ApiModelProperty("执行器任务分片参数，格式如 1/2")
	@VerifyField(value="执行器任务分片参数，格式如 1/2",ignore = true)
	private String executorShardingParam;
    
        /** 失败重试次数 */
	@Column(name = "executor_fail_retry_count",jdbcType="int(11)",notNull=true,def="0",extra="",comment="失败重试次数")
	@ApiModelProperty("失败重试次数")
	@VerifyField(value="失败重试次数",ignore = true)
	private Integer executorFailRetryCount;
    
        /** 调度-时间 */
	@Column(name = "trigger_time",jdbcType="datetime",notNull=false,def="",extra="",comment="调度-时间")
	@ApiModelProperty("调度-时间")
	@VerifyField(value="调度-时间",ignore = true)
	private Date triggerTime;
    
        /** 调度-结果 */
	@Column(name = "trigger_code",jdbcType="int(11)",notNull=true,def="0",extra="",comment="调度-结果")
	@ApiModelProperty("调度-结果")
	@VerifyField(value="调度-结果",ignore = true)
	private Integer triggerCode;
    
        /** 调度-日志 */
	@Column(name = "trigger_msg",jdbcType="text",notNull=false,def="",extra="",comment="调度-日志")
	@ApiModelProperty("调度-日志")
	@VerifyField(value="调度-日志",ignore = true)
	private String triggerMsg;
    
        /** 执行-时间 */
	@Column(name = "handle_time",jdbcType="datetime",notNull=false,def="",extra="",comment="执行-时间")
	@ApiModelProperty("执行-时间")
	@VerifyField(value="执行-时间",ignore = true)
	private Date handleTime;
    
        /** 执行-状态 */
	@Column(name = "handle_code",jdbcType="int(11)",notNull=true,def="0",extra="",comment="执行-状态")
	@ApiModelProperty("执行-状态")
	@VerifyField(value="执行-状态",ignore = true)
	private Integer handleCode;
    
        /** 执行-日志 */
	@Column(name = "handle_msg",jdbcType="text",notNull=false,def="",extra="",comment="执行-日志")
	@ApiModelProperty("执行-日志")
	@VerifyField(value="执行-日志",ignore = true)
	private String handleMsg;
    
        /** 告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败 */
	@Column(name = "alarm_status",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败")
	@ApiModelProperty("告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败")
	@VerifyField(value="告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败",ignore = true)
	private Byte alarmStatus;
    
}
