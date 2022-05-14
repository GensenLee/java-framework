package org.devops.iweb.file.model;

import java.util.ArrayList;
import java.util.List;

import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(value = "file_oss_config",create=true,comment="oss配置管理",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
public class FileOssConfig extends BaseBean{

	private static final long serialVersionUID = 1L;
	/** 流水号 */
	public static final String ID = "id";
	/** 集团ID */
	public static final String COMPANY_ID = "company_id";
	/** 地域节点 */
	public static final String END_POINT = "end_point";
	/** cname域名，可以为空 */
	public static final String CNAME = "cname";
	/** 存储空间 */
	public static final String BUCKET = "bucket";
	/** key */
	public static final String ACCESS_KEY = "access_key";
	/** secret */
	public static final String SECRET_KEY = "secret_key";
	
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true,comment="流水号")
	@VerifyField(ignore = true)
	private Long id;
	
	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="0",comment="集团ID")
	private Long companyId;
	
	@Column(name = "end_point",jdbcType="varchar(50)",notNull=true,def="''",comment="地域节点")
	private String endPoint;
	
	@Column(name = "cname",jdbcType="varchar(255)",notNull=true,def="''",comment="cname域名，可以为空")
	private String cname;
	
	@Column(name = "bucket",jdbcType="varchar(50)",notNull=true,def="''",comment="存储空间")
	private String bucket;
	
	@Column(name = "access_key",jdbcType="varchar(50)",notNull=true,def="''",comment="key")
	private String accessKey;
	
	@Column(name = "secret_key",jdbcType="varchar(50)",notNull=true,def="''",comment="secret")
	private String secretKey;
	
	@SuppressWarnings("serial")
	public static List<FileOssConfig> init(){
		return new ArrayList<FileOssConfig>(){{
		}};
	}
	
	public FileOssConfig(){
		
	}
	
	public static String initSQL() {
		return ResourceUtil.readResourceAsString("classpath:sql/devops-iweb-file/file_oss_config.sql");
	}
	
	
}
