package org.devops.iweb.file.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.file.service.FileOssService;
import org.devops.iweb.file.vo.outVO.OssSingatureOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "OSS")
public class IWebFileOssController {

	@Autowired
	private FileOssService fileOssService;
	
	/**
	 * @description oss签名
	 * @author xhz
	 * @date 2017年7月4日 上午11:49:46
	 * @param request
	 * @return
	 * @lastModifier
	 */
	@ApiOperation("OSS获取签名")
	@PostMapping(value = "/api/v1/devops/file/oss/getSingature.do")
	public ApiResult<OssSingatureOutVO> getSingature(HttpServletRequest request,HttpServletResponse response,Long companyId){
		return ApiResult.getSuccess(fileOssService.getSingature(companyId));
	}
}
