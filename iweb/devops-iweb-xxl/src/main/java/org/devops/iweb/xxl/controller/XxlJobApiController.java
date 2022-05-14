package org.devops.iweb.xxl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.devops.iweb.xxl.annotation.PermissionLimit;
import org.devops.iweb.xxl.conf.XxlJobScheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxl.job.core.biz.AdminBiz;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
@RequestMapping("/xxl-job-admin")
public class XxlJobApiController implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @RequestMapping(AdminBiz.MAPPING)
    @PermissionLimit(limit=false)
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        XxlJobScheduler.invokeAdminService(request, response);
    }


}
