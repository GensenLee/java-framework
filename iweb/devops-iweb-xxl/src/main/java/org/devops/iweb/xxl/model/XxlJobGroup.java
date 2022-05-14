package org.devops.iweb.xxl.model;

import java.util.ArrayList;
import java.util.Arrays;
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

@Table(table="xxl_job_group",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobGroup extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/** 执行器AppName */
	public static final String APP_NAME = "app_name";
	/** 执行器名称 */
	public static final String TITLE = "title";
	/** 排序 */
	public static final String ORDER = "order";
	/** 执行器地址类型：0=自动注册、1=手动录入 */
	public static final String ADDRESS_TYPE = "address_type";
	/** 执行器地址列表，多地址逗号分隔 */
	public static final String ADDRESS_LIST = "address_list";

	@SuppressWarnings("serial")
	public static List<XxlJobGroup> init(){
		return new ArrayList<XxlJobGroup>(){{
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_group.sql");
	}
	
        /**  */
	@Column(name = "id",jdbcType="int(11)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Integer id;
    
        /** 执行器AppName */
	@Column(name = "app_name",jdbcType="varchar(64)",notNull=true,def="''",extra="",comment="执行器AppName")
	@ApiModelProperty("执行器AppName")
	@VerifyField(value="执行器AppName",ignore = true)
	private String appName;
    
        /** 执行器名称 */
	@Column(name = "title",jdbcType="varchar(12)",notNull=true,def="''",extra="",comment="执行器名称")
	@ApiModelProperty("执行器名称")
	@VerifyField(value="执行器名称",ignore = true)
	private String title;
    
        /** 排序 */
	@Column(name = "order",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="排序")
	@ApiModelProperty("排序")
	@VerifyField(value="排序",ignore = true)
	private Byte order;
    
        /** 执行器地址类型：0=自动注册、1=手动录入 */
	@Column(name = "address_type",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="执行器地址类型：0=自动注册、1=手动录入")
	@ApiModelProperty("执行器地址类型：0=自动注册、1=手动录入")
	@VerifyField(value="执行器地址类型：0=自动注册、1=手动录入",ignore = true)
	private Byte addressType;
    
        /** 执行器地址列表，多地址逗号分隔 */
	@Column(name = "address_list",jdbcType="varchar(512)",notNull=false,def="",extra="",comment="执行器地址列表，多地址逗号分隔")
	@ApiModelProperty("执行器地址列表，多地址逗号分隔")
	@VerifyField(value="执行器地址列表，多地址逗号分隔",ignore = true)
	private String addressList;
	
	
	// registry list
    private List<String> registryList;  // 执行器地址列表(系统注册)
    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }
    
}
