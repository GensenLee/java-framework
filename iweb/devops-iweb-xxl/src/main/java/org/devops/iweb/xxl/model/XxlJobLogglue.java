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

@Table(table="xxl_job_logglue",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobLogglue extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/** 任务，主键ID */
	public static final String JOB_ID = "job_id";
	/** GLUE类型 */
	public static final String GLUE_TYPE = "glue_type";
	/** GLUE源代码 */
	public static final String GLUE_SOURCE = "glue_source";
	/** GLUE备注 */
	public static final String GLUE_REMARK = "glue_remark";
	/**  */
	public static final String ADD_TIME = "add_time";
	/**  */
	public static final String UPDATE_TIME = "update_time";

	@SuppressWarnings("serial")
	public static List<XxlJobLogglue> init(){
		return new ArrayList<XxlJobLogglue>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_logglue.sql");
	}
	
        /**  */
	@Column(name = "id",jdbcType="int(11)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Integer id;
    
        /** 任务，主键ID */
	@Column(name = "job_id",jdbcType="int(11)",notNull=true,def="0",extra="",comment="任务，主键ID")
	@ApiModelProperty("任务，主键ID")
	@VerifyField(value="任务，主键ID",ignore = true)
	private Integer jobId;
    
        /** GLUE类型 */
	@Column(name = "glue_type",jdbcType="varchar(50)",notNull=false,def="",extra="",comment="GLUE类型")
	@ApiModelProperty("GLUE类型")
	@VerifyField(value="GLUE类型",ignore = true)
	private String glueType;
    
        /** GLUE源代码 */
	@Column(name = "glue_source",jdbcType="mediumtext",notNull=false,def="",extra="",comment="GLUE源代码")
	@ApiModelProperty("GLUE源代码")
	@VerifyField(value="GLUE源代码",ignore = true)
	private String glueSource;
    
        /** GLUE备注 */
	@Column(name = "glue_remark",jdbcType="varchar(128)",notNull=true,def="''",extra="",comment="GLUE备注")
	@ApiModelProperty("GLUE备注")
	@VerifyField(value="GLUE备注",ignore = true)
	private String glueRemark;
    
        /**  */
	@Column(name = "add_time",jdbcType="timestamp",notNull=false,def="",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Date addTime;
    
        /**  */
	@Column(name = "update_time",jdbcType="timestamp",notNull=false,def="",extra="on update CURRENT_TIMESTAMP",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Date updateTime;
    
}
