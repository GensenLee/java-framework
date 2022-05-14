package org.devops.iweb.operationlog.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.utils.vo.BaseSearchVO;

/**
 * @author GENSEN
 * @date 2021/11/19 16:30
 * @description：操作日志搜索
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OperationLogSearchVO extends BaseSearchVO {

    @ApiModelProperty("记录时间, 区间开始")
    private Long startRecordTime;

    @ApiModelProperty("记录时间, 区间结束")
    private Long endRecordTime;

    @ApiModelProperty("操作结果 ok/no")
    private String result;

    @ApiModelProperty("请求方法 GET/POST/DELETE ...")
    private String requestMethod;

    @ApiModelProperty("请求id")
    private String requestId;

    @ApiModelProperty("请求ip")
    private String requestIp;

    @ApiModelProperty("请求路径uri")
    private String requestUri;

    @ApiModelProperty("请求域名")
    private String requestHost;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("操作项描述")
    private String operationDescription;

    @ApiModelProperty("自定义参数1")
    private String param1;

    @ApiModelProperty("自定义参数2")
    private String param2;

    @ApiModelProperty("自定义参数3")
    private String param3;

    @ApiModelProperty("自定义参数4")
    private String param4;

    @ApiModelProperty("精确查询自定义参数1")
    private Boolean exactParam1;

    @ApiModelProperty("精确查询自定义参数2")
    private Boolean exactParam2;

    @ApiModelProperty("精确查询自定义参数3")
    private Boolean exactParam3;

    @ApiModelProperty("精确查询自定义参数4")
    private Boolean exactParam4;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户类型code")
    private String userTypeCode;

    @ApiModelProperty("系统模块")
    private String module;

    @ApiModelProperty("操作堆栈")
    private String stack;

    @ApiModelProperty("操作类型 delete/update/create/query/other")
    private String type;

    @ApiModelProperty("搜索范围，分表模式需要指定")
    private String scope;
}
