package org.devops.iweb.operationlog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.devops.core.utils.interfaces.BaseUserInfo;
import org.devops.core.utils.vo.ApiResult;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.operationlog.configuration.IWebOperationLogConfiguration;
import org.devops.iweb.operationlog.model.OperationLog;
import org.devops.iweb.operationlog.web.vo.OperationLogSearchVO;
import org.devops.iweb.operationlog.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GENSEN
 * @date 2021/11/19 17:14
 * @description：操作日志
 */
@Api(tags = "操作日志")
@RestController
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    private BaseUserInfo getBaseUserInfo() {
        return IWebOperationLogConfiguration.getDefaultUserInfoDefineExpression().getValue(this, BaseUserInfo.class);
    }

    /**
     * 默认使用 iweb 鉴权
     *
     * @param vo
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/api/v1/devops/instance/logs/list")
    public ApiResult<PageResult<OperationLog>> list(OperationLogSearchVO vo) {
        return ApiResult.getSuccess(operationLogService.list(vo, getBaseUserInfo()));
    }

    /**
     * 默认使用 iweb 鉴权
     *
     * @param id
     * @return
     */
    @ApiOperation("获取详情")
    @GetMapping("/api/v1/devops/instance/logs/get")
    public ApiResult<OperationLog> get(@RequestParam("id") Long id,
                                       @RequestParam(name = "scope", required = false) String scope) {
        return ApiResult.getSuccess(operationLogService.get(id, scope, getBaseUserInfo()));
    }

}
