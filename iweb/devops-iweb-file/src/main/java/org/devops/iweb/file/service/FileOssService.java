package org.devops.iweb.file.service;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.file.model.FileOssConfig;
import org.devops.iweb.file.repository.FileOssConfigRepository;
import org.devops.iweb.file.vo.outVO.OssSingatureOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileOssService {

    @Autowired
    private FileOssConfigRepository fileOssConfigRepository;

    public OssSingatureOutVO getSingature(Long companyId) {
        Map<String, Object> signature = new HashMap<String, Object>();

        if (LongUtil.isZero(companyId)) {
            companyId = -1L;
        }

        FileOssConfig fileOssConfig = fileOssConfigRepository.where(FileOssConfig.COMPANY_ID, companyId).get();

        if (fileOssConfig == null) {
            throw new CommonRuntimeException("请先配置OSS");
        }

        String host = fileOssConfig.getCname();

        if (StringUtil.isEmpty(host)) {
            String[] tmp = fileOssConfig.getEndPoint().replace("-internal", "").split("://");
            host = tmp[0] + "://" + fileOssConfig.getBucket() + "." + tmp[1];
        }

        //过期时间 60秒
        long expiration_time = System.currentTimeMillis() + 60 * 1000 * 60 * 24;
        String expiration = DateUtil.getDateFormat(new Date(expiration_time), "yyyy-MM-dd HH:ss:mm");
        expiration = expiration.replace(" ", "T") + "Z";

        List<Object> conditions = new ArrayList<Object>();
        List<Object> conditions1 = new ArrayList<Object>();
        conditions1.add("content-length-range");
        conditions1.add(0);
        conditions1.add(1048576000);
        conditions.add(conditions1);

        signature.put("expiration", expiration);
        signature.put("conditions", conditions);
        String policyStr = FastJsonUtils.toJsonString(signature);
        log.debug("[getSingature]" + policyStr);
        policyStr = new String(Base64.getEncoder().encode(policyStr.getBytes()));

        SecretKeySpec signingKey = new SecretKeySpec(fileOssConfig.getSecretKey().getBytes(), "HmacSHA1");

        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(policyStr.getBytes());
            String signatureData = new String(Base64.getEncoder().encode(rawHmac));

            OssSingatureOutVO ossSingatureOutVO = new OssSingatureOutVO();
            ossSingatureOutVO.setAccessid(fileOssConfig.getAccessKey());
            ossSingatureOutVO.setHost(host);
            ossSingatureOutVO.setPolicy(policyStr);
            ossSingatureOutVO.setSignature(signatureData);
            ossSingatureOutVO.setExpire(expiration);
            ossSingatureOutVO.setEnd_time(expiration_time);
            ossSingatureOutVO.setDirs("upload");
            return ossSingatureOutVO;
        } catch (Exception e) {
            throw new CommonRuntimeException("获取签名出错", e);
        }
    }
}
