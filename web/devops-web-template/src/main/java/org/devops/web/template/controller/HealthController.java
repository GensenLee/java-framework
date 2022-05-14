package org.devops.web.template.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.web.template.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "心跳包", tags = {"心跳包"})
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping(value = "/api/health")
    @ApiOperation(value = "心跳", notes = "心跳")
    public ApiResult<Object> health() throws Exception {
        return ApiResult.getSuccess(healthService.health());
	}


}
