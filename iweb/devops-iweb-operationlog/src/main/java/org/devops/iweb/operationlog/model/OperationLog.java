package org.devops.iweb.operationlog.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
	import java.util.Date;

@Table(table="operation_log",create=true,comment="操作日志",parametric=true)
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="操作日志")
public class OperationLog extends BaseBean{
    /**
    *
    */
    private static final long serialVersionUID = 1L;

    /** 主键 */
    public static final String ID = "id";
    /** 用户id */
    public static final String USER_ID = "user_id";
    /** 用户名 */
    public static final String USER_NAME = "user_name";
    /** 用户类型code */
    public static final String USER_TYPE_CODE = "user_type_code";
    /** 用户类型显示文本 */
    public static final String USER_TYPE_TEXT = "user_type_text";
    /** 系统模块 */
    public static final String MODULE = "module";
    /** 操作堆栈，功能项记录; 逗号隔开 */
    public static final String STACK = "stack";
    /** 操作类型 */
    public static final String TYPE = "type";
    /** 记录时间 */
    public static final String RECORD_TIME = "record_time";
    /** 操作结果 */
    public static final String RESULT = "result";
    /** 失败原因 */
    public static final String FAIL_REASON = "fail_reason";
    /** 方法入参 */
    public static final String INPUT = "input";
    /** 方法出参 */
    public static final String OUTPUT = "output";
    /** 操作项描述 */
    public static final String OPERATION_DESCRIPTION = "operation_description";
    /** 集团ID */
    public static final String COMPANY_ID = "company_id";
    /** 自定义参数1 */
    public static final String PARAM1 = "param1";
    /** 自定义参数2 */
    public static final String PARAM2 = "param2";
    /** 自定义参数3 */
    public static final String PARAM3 = "param3";
    /** 自定义参数4 */
    public static final String PARAM4 = "param4";
    /** 客户端类型 */
    public static final String USER_AGENT = "user_agent";
    /** 请求路径uri */
    public static final String REQUEST_URI = "request_uri";
    /** 请求域名 */
    public static final String REQUEST_HOST = "request_host";
    /** 请求完整路径 http://host/url?query */
    public static final String REQUEST_URL = "request_url";
    /** 请求方法 */
    public static final String REQUEST_METHOD = "request_method";
    /** 请求id */
    public static final String REQUEST_ID = "request_id";
    /** 请求来源地址 */
    public static final String REQUEST_REFERER = "request_referer";
    /** 请求ip */
    public static final String REQUEST_IP = "request_ip";
    /** 国家 */
    public static final String COUNTRY = "country";
    /** 省份 */
    public static final String PROVINCE = "province";
    /** 城市 */
    public static final String CITY = "city";

    public static List<String> QUICK_SEARCH_SUPPORT;
    static {
		QUICK_SEARCH_SUPPORT = new ArrayList<>();
		QUICK_SEARCH_SUPPORT.add(CITY);
		QUICK_SEARCH_SUPPORT.add(PROVINCE);
		QUICK_SEARCH_SUPPORT.add(COUNTRY);
		QUICK_SEARCH_SUPPORT.add(REQUEST_IP);
		QUICK_SEARCH_SUPPORT.add(REQUEST_ID);
		QUICK_SEARCH_SUPPORT.add(REQUEST_URI);
		QUICK_SEARCH_SUPPORT.add(PARAM1);
		QUICK_SEARCH_SUPPORT.add(PARAM2);
		QUICK_SEARCH_SUPPORT.add(PARAM3);
		QUICK_SEARCH_SUPPORT.add(PARAM4);
		QUICK_SEARCH_SUPPORT.add(STACK);
		QUICK_SEARCH_SUPPORT.add(MODULE);
	}

	@SuppressWarnings("serial")
	public static List<OperationLog> init(){
		return new ArrayList<OperationLog>(){{
		}};
	}

		/** 主键 */
	@Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true, comment="主键")
	@ApiModelProperty("主键")
	@VerifyField(value="主键",ignore = true)
	private Long id;
	
		/** 用户id */
	@Column(name = "user_id",jdbcType="varchar(32)",notNull=true,def="'-1'",extra="",comment="用户id")
	@ApiModelProperty("用户id")
	@VerifyField(value="用户id",ignore = true)
	private String userId;
	
		/** 用户名 */
	@Column(name = "user_name",jdbcType="varchar(64)",notNull=true,def="''",extra="",comment="用户名")
	@ApiModelProperty("用户名")
	@VerifyField(value="用户名",ignore = true)
	private String userName;
	
		/** 用户类型code */
	@Column(name = "user_type_code",jdbcType="varchar(32)",notNull=true,def="''",extra="",comment="用户类型code")
	@ApiModelProperty("用户类型code")
	@VerifyField(value="用户类型code",ignore = true)
	private String userTypeCode;
	
		/** 用户类型显示文本 */
	@Column(name = "user_type_text",jdbcType="varchar(64)",notNull=true,def="''",extra="",comment="用户类型显示文本")
	@ApiModelProperty("用户类型显示文本")
	@VerifyField(value="用户类型显示文本",ignore = true)
	private String userTypeText;
	
		/** 系统模块 */
	@Column(name = "module",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="系统模块")
	@ApiModelProperty("系统模块")
	@VerifyField(value="系统模块",ignore = true)
	private String module;
	
		/** 操作堆栈，功能项记录; 逗号隔开 */
	@Column(name = "stack",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="操作堆栈，功能项记录; 逗号隔开")
	@ApiModelProperty("操作堆栈，功能项记录; 逗号隔开")
	@VerifyField(value="操作堆栈，功能项记录; 逗号隔开",ignore = true)
	private String stack;
	
		/** 操作类型 */
	@Column(name = "type",jdbcType="varchar(32)",notNull=true,def="''",extra="",comment="操作类型")
	@ApiModelProperty("操作类型")
	@VerifyField(value="操作类型",ignore = true)
	private String type;
	
		/** 记录时间 */
	@Column(name = "record_time",jdbcType="datetime",notNull=false,def="",extra="",comment="记录时间")
	@ApiModelProperty("记录时间")
	@VerifyField(value="记录时间",ignore = true)
	private Date recordTime;
	
		/** 操作结果 */
	@Column(name = "result",jdbcType="varchar(32)",notNull=true,def="''",extra="",comment="操作结果")
	@ApiModelProperty("操作结果")
	@VerifyField(value="操作结果",ignore = true)
	private String result;
	
		/** 失败原因 */
	@Column(name = "fail_reason",jdbcType="varchar(255)",notNull=true,def="''",extra="",comment="失败原因")
	@ApiModelProperty("失败原因")
	@VerifyField(value="失败原因",ignore = true)
	private String failReason;
	
		/** 方法入参 */
	@Column(name = "input",jdbcType="text",notNull=false,def="",extra="",comment="方法入参")
	@ApiModelProperty("方法入参")
	@VerifyField(value="方法入参",ignore = true)
	private String input;
	
		/** 方法出参 */
	@Column(name = "output",jdbcType="text",notNull=false,def="",extra="",comment="方法出参")
	@ApiModelProperty("方法出参")
	@VerifyField(value="方法出参",ignore = true)
	private String output;
	
		/** 操作项描述 */
	@Column(name = "operation_description",jdbcType="text",notNull=false,def="",extra="",comment="操作项描述")
	@ApiModelProperty("操作项描述")
	@VerifyField(value="操作项描述",ignore = true)
	private String operationDescription;
	
		/** 集团ID */
	@Column(name = "company_id",jdbcType="bigint(20)",notNull=true,def="'-1'",extra="",comment="集团ID")
	@ApiModelProperty("集团ID")
	@VerifyField(value="集团ID",ignore = true)
	private Long companyId;
	
		/** 自定义参数1 */
	@Column(name = "param1",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="自定义参数1")
	@ApiModelProperty("自定义参数1")
	@VerifyField(value="自定义参数1",ignore = true)
	private String param1;
	
		/** 自定义参数2 */
	@Column(name = "param2",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="自定义参数2")
	@ApiModelProperty("自定义参数2")
	@VerifyField(value="自定义参数2",ignore = true)
	private String param2;
	
		/** 自定义参数3 */
	@Column(name = "param3",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="自定义参数3")
	@ApiModelProperty("自定义参数3")
	@VerifyField(value="自定义参数3",ignore = true)
	private String param3;
	
		/** 自定义参数4 */
	@Column(name = "param4",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="自定义参数4")
	@ApiModelProperty("自定义参数4")
	@VerifyField(value="自定义参数4",ignore = true)
	private String param4;
	
		/** 客户端类型 */
	@Column(name = "user_agent",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="客户端类型")
	@ApiModelProperty("客户端类型")
	@VerifyField(value="客户端类型",ignore = true)
	private String userAgent;
	
		/** 请求路径uri */
	@Column(name = "request_uri",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="请求路径uri")
	@ApiModelProperty("请求路径uri")
	@VerifyField(value="请求路径uri",ignore = true)
	private String requestUri;
	
		/** 请求域名 */
	@Column(name = "request_host",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="请求域名")
	@ApiModelProperty("请求域名")
	@VerifyField(value="请求域名",ignore = true)
	private String requestHost;
	
		/** 请求完整路径 http://host/url?query */
	@Column(name = "request_url",jdbcType="varchar(1024)",notNull=true,def="''",extra="",comment="请求完整路径 http://host/url?query")
	@ApiModelProperty("请求完整路径 http://host/url?query")
	@VerifyField(value="请求完整路径 http://host/url?query",ignore = true)
	private String requestUrl;
	
		/** 请求方法 */
	@Column(name = "request_method",jdbcType="varchar(16)",notNull=true,def="''",extra="",comment="请求方法")
	@ApiModelProperty("请求方法")
	@VerifyField(value="请求方法",ignore = true)
	private String requestMethod;
	
		/** 请求id */
	@Column(name = "request_id",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="请求id")
	@ApiModelProperty("请求id")
	@VerifyField(value="请求id",ignore = true)
	private String requestId;
	
		/** 请求来源地址 */
	@Column(name = "request_referer",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="请求来源地址")
	@ApiModelProperty("请求来源地址")
	@VerifyField(value="请求来源地址",ignore = true)
	private String requestReferer;
	
		/** 请求ip */
	@Column(name = "request_ip",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="请求ip")
	@ApiModelProperty("请求ip")
	@VerifyField(value="请求ip",ignore = true)
	private String requestIp;
	
		/** 国家 */
	@Column(name = "country",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="国家")
	@ApiModelProperty("国家")
	@VerifyField(value="国家",ignore = true)
	private String country;
	
		/** 省份 */
	@Column(name = "province",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="省份")
	@ApiModelProperty("省份")
	@VerifyField(value="省份",ignore = true)
	private String province;
	
		/** 城市 */
	@Column(name = "city",jdbcType="varchar(125)",notNull=true,def="''",extra="",comment="城市")
	@ApiModelProperty("城市")
	@VerifyField(value="城市",ignore = true)
	private String city;
	
}
