package org.devops.mjar.ext.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.vo.BaseBean;

import java.util.ArrayList;
import java.util.List;

@Table(table="ext_column_config",create=true,comment="字段扩展，可配置")
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "字段扩展，可配置",description="字段扩展，可配置")
public class ExtColumnConfig extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 集团ID */
	public static final String COMPANY_ID = "company_id";
	/** 门店ID */
	public static final String SHOP_ID = "shop_id";
	/** 实例ID */
	public static final String APP_INSTANCE_ID = "app_instance_id";
	/** 扩增类型code值 */
	public static final String CODE = "code";
	/** 配置 */
	public static final String CONFIG_VALUE = "config_value";
	/** 备注 */
	public static final String REMARK = "remark";

	public static List<ExtColumnConfig> init(){
		return new ArrayList<>();
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-ext/ext_column_config.sql");
	}
	
        /** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	private Long id;
    
        /** 集团ID */
	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="0",comment="集团ID")
	@ApiModelProperty("集团ID")
	private Long companyId;
    
        /** 门店ID */
	@Column(name = "shop_id",jdbcType="bigint(20)",notNull=true,def="0",comment="门店ID")
	@ApiModelProperty("门店ID")
	private Long shopId;
    
        /** 实例ID */
	@Column(name = "app_instance_id",jdbcType="bigint(20)",notNull=true,def="0",comment="实例ID")
	@ApiModelProperty("实例ID")
	private Long appInstanceId;
    
        /** 扩增类型code值 */
	@Column(name = "code",jdbcType="varchar(50)",notNull=true,def="''",comment="扩增类型code值")
	@ApiModelProperty("扩增类型code值")
	private String code;
    
        /** 配置 */
	@Column(name = "config_value",jdbcType="varchar(3000)",notNull=true,def="''",comment="配置")
	@ApiModelProperty("配置")
	private String configValue;
    
        /** 备注 */
	@Column(name = "remark",jdbcType="varchar(100)",notNull=true,def="",comment="备注")
	@ApiModelProperty("备注")
	private String remark;
    
}
