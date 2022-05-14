package org.devops.iweb.xxl.model;

import java.util.ArrayList;
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

@Table(table="xxl_job_lock",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobLock extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 锁名称 */
	public static final String LOCK_NAME = "lock_name";

	@SuppressWarnings("serial")
	public static List<XxlJobLock> init(){
		return new ArrayList<XxlJobLock>(){{
			add(new XxlJobLock("schedule_lock"));
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_lock.sql");
	}
	
	public XxlJobLock(){
		
	}
	
	public XxlJobLock(String lockName) {
		super();
		this.lockName = lockName;
	}



		/** 锁名称 */
	@Column(name = "lock_name",jdbcType="varchar(50)",priKey=true, comment="锁名称")
	@ApiModelProperty("锁名称")
	@VerifyField(value="锁名称",ignore = true)
	private String lockName;
    
}
