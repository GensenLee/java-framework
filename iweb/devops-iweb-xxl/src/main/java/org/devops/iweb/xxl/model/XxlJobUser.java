package org.devops.iweb.xxl.model;

import java.util.ArrayList;
import java.util.List;

import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(table="xxl_job_user",create=true,comment="",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="")
public class XxlJobUser extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**  */
	public static final String ID = "id";
	/** 账号 */
	public static final String USERNAME = "username";
	/** 密码 */
	public static final String PASSWORD = "password";
	/** 角色：0-普通用户、1-管理员 */
	public static final String ROLE = "role";
	/** 权限：执行器ID列表，多个逗号分割 */
	public static final String PERMISSION = "permission";

	@SuppressWarnings("serial")
	public static List<XxlJobUser> init(){
		return new ArrayList<XxlJobUser>(){{
			add(new XxlJobUser(1, "admin", "a66abb5684c45962d887564f08346e8d", (byte)1, null));
		}};
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-xxl/xxl_job_user.sql");
	}
	
	public XxlJobUser(){
	
	}
	
   public XxlJobUser(Integer id, String username, String password, Byte role, String permission) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.permission = permission;
	}



		/**  */
	@Column(name = "id",jdbcType="int(11)",priKey=true,autoIncrement=true, comment="")
	@ApiModelProperty("")
	@VerifyField(value="",ignore = true)
	private Integer id;
    
        /** 账号 */
	@Column(name = "username",jdbcType="varchar(50)",notNull=true,def="''",extra="",comment="账号")
	@ApiModelProperty("账号")
	@VerifyField(value="账号",ignore = true)
	private String username;
    
        /** 密码 */
	@Column(name = "password",jdbcType="varchar(50)",notNull=true,def="''",extra="",comment="密码")
	@ApiModelProperty("密码")
	@VerifyField(value="密码",ignore = true)
	private String password;
    
        /** 角色：0-普通用户、1-管理员 */
	@Column(name = "role",jdbcType="tinyint(4)",notNull=true,def="0",extra="",comment="角色：0-普通用户、1-管理员")
	@ApiModelProperty("角色：0-普通用户、1-管理员")
	@VerifyField(value="角色：0-普通用户、1-管理员",ignore = true)
	private Byte role;
    
        /** 权限：执行器ID列表，多个逗号分割 */
	@Column(name = "permission",jdbcType="varchar(255)",notNull=false,def="",extra="",comment="权限：执行器ID列表，多个逗号分割")
	@ApiModelProperty("权限：执行器ID列表，多个逗号分割")
	@VerifyField(value="权限：执行器ID列表，多个逗号分割",ignore = true)
	private String permission;
	
	
	// plugin
	public boolean validPermission(int jobGroup){
		if (this.role == 1) {
			return true;
		} else {
			if (StringUtils.hasText(this.permission)) {
				for (String permissionItem : this.permission.split(",")) {
					if (String.valueOf(jobGroup).equals(permissionItem)) {
						return true;
					}
				}
			}
			return false;
		}

	}
    
}
