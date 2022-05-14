package org.devops.iweb.file.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.devops.iweb.file.service.FileViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@Api(tags = "文件预览")
public class IWebFileViewController {

    public static final String FILE_VIEW_API = "/api/v1/devops/file/fileView/view.do";

    @Autowired
    private FileViewService fileViewService;

    /**
     * @param request
     * @param response
     * @param fileName 文件路径
     * @description 文件预览与下载
     * @author xhz
     * @date 2017年7月4日 上午11:50:22
     * @lastModifier
     */
    @GetMapping(value = FILE_VIEW_API)
    public void fileView(HttpServletRequest request, HttpServletResponse response, String fileName) {

        //让请求支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");

        fileViewService.fileView(response, fileName);
    }
}
