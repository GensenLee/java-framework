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

@Table(table="xxl_job_info",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobInfo extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/** 执行器主键ID */
	public static final String JOB_GROUP = "job_group";
	/** 任务执行CRON */
	public static final String JOB_CRON = "job_cron";
	/**  */
	public static final String JOB_DESC = "job_desc";
	/**  */
	public static final String ADD_TIME = "add_time";
	/**  */
	public static final String UPDATE_TIME = "update_time";
	/** 作者 */
	public static final String AUTHOR = "author";
	/** 报警邮件 */
	public static final String ALARM_EMAIL = "alarm_email";
	/** 执行器路由策略 */
	public static final String EXECUTOR_ROUTE_STRATEGY = "executor_route_strategy";
	/** 执行器任务handler */
	public static final String EXECUTOR_HANDLER = "executor_handler";
	/** 执行器任务参数 */
	public static final String EXECUTOR_PARAM = "executor_param";
	/** 阻塞处理策略 */
	public static final String EXECUTOR_BLOCK_STRATEGY = "executor_block_strategy";
	/** 任务执行超时时间，单位秒 */
	public static final String EXECUTOR_TIMEOUT = "executor_timeout";
	/** 失败重试次数 */
	public static final String EXECUTOR_FAIL_RETRY_COUNT = "executor_fail_retry_count";
	/** GLUE类型 */
	public static final String GLUE_TYPE = "glue_type";
	/** GLUE源代码 */
	public static final String GLUE_SOURCE = "glue_source";
	/** GLUE备注 */
	public static final String GLUE_REMARK = "glue_remark";
	/** GLUE更新时间 */
	public static final String GLUE_UPDATETIME = "glue_updatetime";
	/** 子任务ID，多个逗号分隔 */
	public static final String CHILD_JOB_ID = "child_job_id";
	/** 调度状态：0-停止，1-运行 */
	public static final String TRIGGER_STATUS = "trigger_status";
	/** 上次调度时间 */
	public static final String TRIGGER_LAST_TIME = "trigger_last_time";
	/** 下次调度时间 */
	public static final String TRIGGER_NEXT_TIME = "trigger_next_time";

	@SuppressWarnings("serial")
	public static List<XxlJobInfo> init(){
		return new ArrayList<XxlJobInfo>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_info.sql");
	}
	
        /**  */
	@Column(name = "id",jdbcType="int(11)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Integer id;
    
        /** 执行器主键ID */
	@Column(name = "job_group",jdbcType="int(11)",notNull=true,def="0",extra="",comment="执行器主键ID")
	@ApiModelProperty("执行器主键ID")
	@VerifyField(value="执行器主键ID",ignore = true)
	private Integer jobGroup;
    
        /** 任务执行CRON */
	@Column(name = "job_cron",jdbcType="varchar(128)",notNull=true,def="''",extra="",comment="任务执行CRON")
	@ApiModelProperty("任务执行CRON")
	@VerifyField(value="任务执行CRON",ignore = true)
	private String jobCron;
    
        /**  */
	@Column(name = "job_desc",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private String jobDesc;
    
        /**  */
	@Column(name = "add_time",jdbcType="datetime",notNull=false,def="",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Date addTime;
    
        /**  */
	@Column(name = "update_time",jdbcType="datetime",notNull=false,def="",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Date updateTime;
    
        /** 作者 */
	@Column(name = "author",jdbcType="varchar(64)",notNull=false,def="",extra="",comment="作者")
	@ApiModelProperty("作者")
	@VerifyField(value="作者",ignore = true)
	private String author;
    
        /** 报警邮件 */
	@Column(name = "alarm_email",jdbcType="varchar(255)",notNull=false,def="",extra="",comment="报警邮件")
	@ApiModelProperty("报警邮件")
	@VerifyField(value="报警邮件",ignore = true)
	private String alarmEmail;
    
        /** 执行器路由策略 */
	@Column(name = "executor_route_strategy",jdbcType="varchar(50)",notNull=false,def="",extra="",comment="执行器路由策略")
	@ApiModelProperty("执行器路由策略")
	@VerifyField(value="执行器路由策略",ignore = true)
	private String executorRouteStrategy;
    
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
    
        /** 阻塞处理策略 */
	@Column(name = "executor_block_strategy",jdbcType="varchar(50)",notNull=false,def="",extra="",comment="阻塞处理策略")
	@ApiModelProperty("阻塞处理策略")
	@VerifyField(value="阻塞处理策略",ignore = true)
	private String executorBlockStrategy;
    
        /** 任务执行超时时间，单位秒 */
	@Column(name = "executor_timeout",jdbcType="int(11)",notNull=true,def="0",extra="",comment="任务执行超时时间，单位秒")
	@ApiModelProperty("任务执行超时时间，单位秒")
	@VerifyField(value="任务执行超时时间，单位秒",ignore = true)
	private Integer executorTimeout;
    
        /** 失败重试次数 */
	@Column(name = "executor_fail_retry_count",jdbcType="int(11)",notNull=true,def="0",extra="",comment="失败重试次数")
	@ApiModelProperty("失败重试次数")
	@VerifyField(value="失败重试次数",ignore = true)
	private Integer executorFailRetryCount;
    
        /** GLUE类型 */
	@Column(name = "glue_type",jdbcType="varchar(50)",notNull=true,def="''",extra="",comment="GLUE类型")
	@ApiModelProperty("GLUE类型")
	@VerifyField(value="GLUE类型",ignore = true)
	private String glueType;
    
        /** GLUE源代码 */
	@Column(name = "glue_source",jdbcType="mediumtext",notNull=false,def="",extra="",comment="GLUE源代码")
	@ApiModelProperty("GLUE源代码")
	@VerifyField(value="GLUE源代码",ignore = true)
	private String glueSource;
    
        /** GLUE备注 */
	@Column(name = "glue_remark",jdbcType="varchar(128)",notNull=false,def="",extra="",comment="GLUE备注")
	@ApiModelProperty("GLUE备注")
	@VerifyField(value="GLUE备注",ignore = true)
	private String glueRemark;
    
        /** GLUE更新时间 */
	@Column(name = "glue_updatetime",jdbcType="datetime",notNull=false,def="",extra="",comment="GLUE更新时间")
	@ApiModelProperty("GLUE更新时间")
	@VerifyField(value="GLUE更新时间",ignore = true)
	private Date glueUpdatetime;
    
        /** 子任务ID，多个逗号分隔 */
	@Column(name = "child_job_id",jdbcType="varchar(255)",notNull=false,def="",extra="",comment="子任务ID，多个逗号分隔")
	@ApiModelProperty("子任务ID，多个逗号分隔")
	@VerifyField(value="子任务ID，多个逗号分隔",ignore = true)
	private String childJobId;
    
        /** 调度状态：0-停止，1-运行 */
	@Column(name = "trigger_status",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="调度状态：0-停止，1-运行")
	@ApiModelProperty("调度状态：0-停止，1-运行")
	@VerifyField(value="调度状态：0-停止，1-运行",ignore = true)
	private Byte triggerStatus;
    
        /** 上次调度时间 */
	@Column(name = "trigger_last_time",jdbcType="bigint(13)",notNull=true,def="0",extra="",comment="上次调度时间")
	@ApiModelProperty("上次调度时间")
	@VerifyField(value="上次调度时间",ignore = true)
	private Long triggerLastTime;
    
        /** 下次调度时间 */
	@Column(name = "trigger_next_time",jdbcType="bigint(13)",notNull=true,def="0",extra="",comment="下次调度时间")
	@ApiModelProperty("下次调度时间")
	@VerifyField(value="下次调度时间",ignore = true)
	private Long triggerNextTime;
    
}
