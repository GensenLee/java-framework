package org.devops.web.template.service;

import me.chanjar.weixin.mp.api.WxMpService;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GENSEN
 * @date 2021/9/26 10:57
 * @description：
 */
@Service
public class HealthService {

    @Autowired
    private AuthAppRepository authAppRepository;

    @Autowired
    private WxMpService wxMpService;

    public Object health() throws Exception{
        authAppRepository.list();
        return wxMpService.createJsapiSignature("https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#62");
    }

}
