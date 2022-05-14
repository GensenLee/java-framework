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

@Table(table="xxl_job_registry",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobRegistry extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/**  */
	public static final String REGISTRY_GROUP = "registry_group";
	/**  */
	public static final String REGISTRY_KEY = "registry_key";
	/**  */
	public static final String REGISTRY_VALUE = "registry_value";
	/**  */
	public static final String UPDATE_TIME = "update_time";

	@SuppressWarnings("serial")
	public static List<XxlJobRegistry> init(){
		return new ArrayList<XxlJobRegistry>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_registry.sql");
	}
	
        /**  */
	@Column(name = "id",jdbcType="int(11)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Integer id;
    
        /**  */
	@Column(name = "registry_group",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private String registryGroup;
    
        /**  */
	@Column(name = "registry_key",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private String registryKey;
    
        /**  */
	@Column(name = "registry_value",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private String registryValue;
    
        /**  */
	@Column(name = "update_time",jdbcType="timestamp",notNull=true,def="CURRENT_TIMESTAMP",extra="",comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Date updateTime;
    
}
