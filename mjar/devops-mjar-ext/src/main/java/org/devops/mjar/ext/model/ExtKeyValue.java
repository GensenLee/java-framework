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

@Table(value="ext_key_value",create=true,comment="键值对")
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value = "键值对",description="键值对")
public class ExtKeyValue extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 流水号 */
	public static final String ID = "id";
	/** 集团id */
	public static final String COMPANY_ID = "company_id";
	/** 店铺id */
	public static final String SHOP_ID = "shop_id";
	/** 实例id */
	public static final String APP_INSTANCE_ID = "app_instance_id";
	/** 类型 */
	public static final String TYPE = "type";
	/** 名字 */
	public static final String NAME = "name";
	/** 键 */
	public static final String KEY = "key";
	/** 值 */
	public static final String VALUE = "value";

	public static List<ExtKeyValue> init(){
		return new ArrayList<>();
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-ext/ext_key_value.sql");
	}
	
    	/** 流水号 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="流水号")
	@ApiModelProperty("流水号")
	private Long id;
    
    	/** 集团id */
	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="0",comment="集团id")
	@ApiModelProperty("集团id")
	private Long companyId;
    
    	/** 店铺id */
	@Column(name = "shop_id",jdbcType="bigint(20)",notNull=true,def="0",comment="店铺id")
	@ApiModelProperty("店铺id")
	private Long shopId;
    
    	/** 实例id */
	@Column(name = "app_instance_id",jdbcType="bigint(20)",notNull=true,def="0",comment="实例id")
	@ApiModelProperty("实例id")
	private Long appInstanceId;
    
    	/** 类型 */
	@Column(name = "type",jdbcType="varchar(100)",notNull=true,def="''",comment="类型")
	@ApiModelProperty("类型")
	private String type;
    
    	/** 名字 */
	@Column(name = "name",jdbcType="varchar(100)",notNull=true,def="''",comment="名字")
	@ApiModelProperty("名字")
	private String name;
    
    	/** 键 */
	@Column(name = "key",jdbcType="varchar(100)",notNull=true,def="''",comment="键")
	@ApiModelProperty("键")
	private String key;
    
    	/** 值 */
	@Column(name = "value",jdbcType="varchar(3000)",notNull=true,def="''",comment="值")
	@ApiModelProperty("值")
	private String value;
    
}
