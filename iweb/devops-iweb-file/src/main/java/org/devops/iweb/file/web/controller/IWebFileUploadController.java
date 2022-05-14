package org.devops.iweb.file.web.controller;

import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.file.constant.IwebFileConstant;
import org.devops.iweb.file.service.FileUploadService;
import org.devops.iweb.file.vo.outVO.FileUploadOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(tags = "自定义文件上传")
public class IWebFileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * @param request
     * @return
     * @description 文件上传
     * @author xhz
     * @date 2017年7月4日 上午11:49:46
     * @lastModifier
     */
    @PostMapping(value = "/api/v1/devops/file/fileUpload/upload.do")
    @ApiOperation("文件上传，文件放file字段")
    public ApiResult<FileUploadOutVO> uploadFile(HttpServletRequest request, HttpServletResponse response) {

        //让请求支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");

        if (StringUtil.isEmpty(IwebFileConstant.getDomain())) {
            IwebFileConstant.setDomain(request.getScheme() + "://" + request.getServerName());
            if (request.getServerPort() != 80 && request.getServerPort() != 443) {
                IwebFileConstant.setDomain(IwebFileConstant.getDomain() + ":" + request.getServerPort());
            }
        }


        ApiResult<FileUploadOutVO> result = null;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        @SuppressWarnings("rawtypes")
        Iterator iter = multipartRequest.getFileNames();
        InputStream in = null;
        try {
            while (iter.hasNext()) {
                String param = (String) iter.next();
                CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(param);
                in = file.getFileItem().getInputStream();
                String oriFileName = file.getOriginalFilename();
                result = ApiResult.getSuccess(fileUploadService.uploadFile(oriFileName, in, file.getSize()));
                in.close();
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
        }
        return result;
    }
}
