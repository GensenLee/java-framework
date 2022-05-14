package org.devops.mjar.message.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.apiEnhancer.annotation.ApiEnhancer;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.message.model.MessageSmsConfig;
import org.devops.mjar.message.repository.MessageSmsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2022/2/9 10:23
 * @description 阿里云短信客户端
 */
@Slf4j
@Component
public class AliyunSmsClient {

    private final static Map<String, Client> SMS_CLIENT_CACHE = new ConcurrentHashMap<>(1);

    @Autowired
    private MessageSmsConfigRepository messageSmsConfigRepository;


    /**
     * https://next.api.aliyun.com/api/Dysmsapi/2017-05-25/SendSms?spm=api-workbench.SDK%20Document.0.0.57241e0fS0GJc9&lang=JAVA&params={}
     * <p>
     * 使用AK&SK初始化账号Client
     *
     * @param secretId  accessKeyId
     * @param secretKey accessKeySecret
     * @param endpoint
     * @return Client
     * @throws Exception
     */
    private synchronized Client createClient(String secretId, String secretKey, String endpoint) {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(secretId)
                // 您的AccessKey Secret
                .setAccessKeySecret(secretKey)
                // 访问的域名
                .setEndpoint(endpoint);
        try {
            return new Client(config);
        } catch (Exception exception) {
            throw new CommonRuntimeException(exception);
        }
    }


    /**
     * @param secretId
     * @param secretKey
     * @param endpoint
     * @return
     */
    private Client getOrCreateClient(String secretId, String secretKey, String endpoint) {
        if (StringUtil.isEmpty(secretId) || StringUtil.isEmpty(secretKey) || StringUtil.isEmpty(endpoint)) {
            throw new CommonRuntimeException("参数不全");
        }
        String key = secretKey + secretKey + endpoint;
        if (SMS_CLIENT_CACHE.containsKey(key)) {
            return SMS_CLIENT_CACHE.get(key);
        }
        Client client = createClient(secretId, secretKey, endpoint);
        SMS_CLIENT_CACHE.put(key, client);
        return client;
    }

    /**
     * 发送至单个号码
     *
     * @param phone
     * @param templateId
     * @param templateParamJson
     * @param signName
     * @return
     */
    public boolean send(String phone, String templateId, String templateParamJson, String signName) {
        return send(-1L, phone, templateId, templateParamJson, signName);
    }

    /**
     * 发送至多个号码
     *
     * @param phone
     * @param templateId
     * @param templateParamJsons
     * @param signName
     * @return
     */
    public boolean send(String[] phone, String templateId, String[] templateParamJsons, String signName) {
        return send(-1L, phone, templateId, templateParamJsons, signName);
    }

    /**
     * 发送至多个号码
     *
     * @param companyId
     * @param phone
     * @param templateId
     * @param templateParamJsons
     * @param signName
     * @return
     */
    public boolean send(Long companyId, String[] phone, String templateId, String[] templateParamJsons, String signName) {
        MessageSmsConfig config = getConfig(companyId);
        try {
            return doSend(config.getSecretId(), config.getSecretKey(), config.getEndpoint(), phone,
                    templateId, templateParamJsons, signName);
        } catch (Exception e) {
            log.error("aliyun sms send fail", e);
        }
        return false;
    }

    /**
     * 发送至单个号码
     *
     * @param companyId
     * @param phone
     * @param templateId
     * @param templateParamJson
     * @param signName
     * @return
     */
    public boolean send(Long companyId, String phone, String templateId, String templateParamJson, String signName) {
        MessageSmsConfig config = getConfig(companyId);
        try {
            return doSend(config.getSecretId(), config.getSecretKey(), config.getEndpoint(), new String[]{phone},
                    templateId, new String[]{templateParamJson}, signName);
        } catch (Exception e) {
            log.error("aliyun sms send fail", e);
        }
        return false;
    }

    /**
     * @param secretId       accessKeyId
     * @param secretKey      accessKeySecret
     * @param endpoint
     * @param phones
     * @param templateId
     * @param templateParams 参数json数组
     * @param signName
     */
    public boolean doSend(String secretId, String secretKey, String endpoint, String[] phones, String templateId,
                          String[] templateParams, String signName) throws Exception {
        if (phones.length == 0) {
            return false;
        }
        Client client = getOrCreateClient(secretId, secretKey, endpoint);
        SendBatchSmsRequest sendSmsRequest = new SendBatchSmsRequest();
        sendSmsRequest.setPhoneNumberJson(FastJsonUtils.toJsonString(phones));
        sendSmsRequest.setTemplateCode(templateId);
        String[] signNameArr = new String[phones.length];
        Arrays.fill(signNameArr, signName);
        sendSmsRequest.setSignNameJson(FastJsonUtils.toJsonString(signNameArr));
        sendSmsRequest.setTemplateParamJson(FastJsonUtils.toJsonString(templateParams));

        SendBatchSmsResponse sendBatchSmsResponse = client.sendBatchSms(sendSmsRequest);

        log.info("aliyun sms send params[{}]", FastJsonUtils.toJsonString(sendSmsRequest));

        // 输出json格式的字符串回包
        log.info("aliyun sms send result[{}]", FastJsonUtils.toJsonString(sendBatchSmsResponse.body));

        return "ok".equalsIgnoreCase(sendBatchSmsResponse.getBody().getCode());
    }

    /**
     * @param companyId
     * @return
     */
    @ApiEnhancer
    private MessageSmsConfig getConfig(Long companyId) {
        MessageSmsConfig messageSmsConfig = messageSmsConfigRepository
                .where(MessageSmsConfig.ACTIVE, CommonConstant.ACTIVE)
                .where(MessageSmsConfig.COMPANY_ID, companyId)
                .where(MessageSmsConfig.TYPE, MessageSmsConfig.Dict.Type.ALIYUN)
                .get();
        if (messageSmsConfig == null) {
            throw new CommonRuntimeException("未配置短信服务");
        }
        return messageSmsConfig;
    }

}
