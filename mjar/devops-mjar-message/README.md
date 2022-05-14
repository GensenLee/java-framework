# sinmn-mjar-message
##短信、邮件服务

###配置类上添加注解 EnableMjarMessage 启动组件
```
@EnableMjarMessage(
    //指定数据源
    datasource = ""
)
```

###腾讯短信服务
```
public class TencentSms {

    @Autowired
    private TencentSmsClient tencentSmsClient;
    
    /**
     * @param phone 手机号码
     * @param templateId 模板Id
     * @param params 验证码
     * @param signName 签名
     */
    public void sendSmsCode(String phone, String templateId, String[] params, String signName) {
        tencentSmsClient.send(phone, templateId, params, signName);
    }
}
```

###邮件服务
```
public class Email {

    @Autowired
    private MessageEmailCore messageEmailCore;
    
    /**
     * @param templateNo 模板编码
     * @param toAddress 邮件接收者地址
     * @param messageVO 邮件主题和内容VO
     */
    public void sendEmailCode(String templateNo, final String toAddress, final MessageVO messageVO) {
        messageEmailCore.send(templateNo, toAddress, messageVO);
    }
}
```

