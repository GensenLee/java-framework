package org.devops.iweb.file.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import java.util.ArrayList;
import java.util.List;

@Table(value = "file_cos_config",create=true,comment="cos配置管理",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
public class FileCosConfig extends BaseBean{

	private static final long serialVersionUID = 1L;
	/** 流水号 */
	public static final String ID = "id";
	/** 集团ID */
	public static final String COMPANY_ID = "company_id";
	/** 地域节点 */
	public static final String REGION = "region";
	/** cname域名，可以为空 */
	public static final String CNAME = "cname";
	/** 存储空间 */
	public static final String BUCKET = "bucket";
	/** secret id */
	public static final String SECRET_ID = "secret_id";
	/** secret key */
	public static final String SECRET_KEY = "secret_key";

	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true,comment="流水号")
	@VerifyField(ignore = true)
	private Long id;

	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="0",comment="集团ID")
	private Long companyId;

	@Column(name = "region",jdbcType="varchar(50)",notNull=true,def="''",comment="地域节点")
	private String region;

	@Column(name = "cname",jdbcType="varchar(255)",notNull=true,def="''",comment="cname域名，可以为空")
	private String cname;

	@Column(name = "bucket",jdbcType="varchar(50)",notNull=true,def="''",comment="存储空间")
	private String bucket;

	@Column(name = "secret_id",jdbcType="varchar(50)",notNull=true,def="''",comment="secret id")
	private String secretId;

	@Column(name = "secret_key",jdbcType="varchar(50)",notNull=true,def="''",comment="secret key")
	private String secretKey;

	@SuppressWarnings("serial")
	public static List<FileCosConfig> init(){
		return new ArrayList<FileCosConfig>(){{
		}};
	}

}
