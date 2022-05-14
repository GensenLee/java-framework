package org.devops.web.test.test.service;

import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.message.configuration.EnableMjarMessage;
import org.devops.mjar.message.core.MessageEmailCore;
import org.devops.mjar.message.sms.AliyunSmsClient;
import org.devops.mjar.message.sms.TencentSmsClient;
import org.devops.mjar.message.vo.MessageVO;
import org.devops.web.test.bean.datasource.DataSourceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;

/**
 * @author GENSEN
 * @date 2021/11/11 19:51
 * @description：
 */
@ActiveProfiles({"dev"})
@EnableMjarMessage(datasource = DataSourceType.NAME)
public class MjarMessageTest extends AbstractJUnit4ServiceAction {

    @Autowired
    private MessageEmailCore messageEmailCore;

    @Test
    public void test(){
        MessageVO messageVO = new MessageVO();
        messageVO.setData(new HashMap<>());
        messageVO.setSubject("xxxxxxxxxx");
        messageEmailCore.send("TEMP001", "liguangze@linkkap.com", messageVO);
    }

    @Autowired
    private TencentSmsClient tencentSmsClient;

    @Autowired
    private AliyunSmsClient aliyunSmsClient;

    @Test
    public void smsTest(){
//        tencentSmsClient.send(new String[]{"13580603497", "13580603497"}, "1201509", new String[]{"6666","5555"}, "魔方创拼室");

        aliyunSmsClient.send(new String[]{"13580603497", "13580603497"}, "SMS_203726393", new String[]{"{\"code\":\"6666\"}", "{\"code\":\"1111\"}"}, "预见大学");
    }


    public static void main(String[] args) {

        

    }

}
