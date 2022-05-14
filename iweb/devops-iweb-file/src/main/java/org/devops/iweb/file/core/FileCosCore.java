package org.devops.iweb.file.core;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import org.devops.core.utils.constant.FileType;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.iweb.file.model.FileCosConfig;
import org.devops.iweb.file.model.FileOssConfig;
import org.devops.iweb.file.repository.FileCosConfigRepository;
import org.devops.iweb.file.utils.FileUploadUtil;
import org.devops.iweb.file.vo.FileUploadInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GENSEN
 * @date 2021/10/14 15:07
 * @description：腾讯云cos
 */
@Component
public class FileCosCore {

    private static final Map<String, COSClient> CLIENT_CACHE_MAP = new ConcurrentHashMap<>();

    @Autowired
    private FileCosConfigRepository fileCosConfigRepository;

    public String upload(InputStream inputStream, String fileName) {
        FileUploadInfoVO uploadInfoVO = new FileUploadInfoVO();
        uploadInfoVO.setFileName(fileName);
        uploadInfoVO.setUploadPath(FileUploadUtil.createPath(FileType.get(fileName)));
        return upload(-1L, inputStream, uploadInfoVO);
    }

    public String upload(InputStream inputStream, FileType fileType) {
        return upload(-1L, inputStream, fileType);
    }

    public String upload(Long companyId, InputStream inputStream, FileType fileType) {
        return upload(companyId, inputStream, FileUploadUtil.createMetadata(fileType));
    }

    public String upload(Long companyId, InputStream inputStream, FileUploadInfoVO infoVO) {
        FileCosConfig fileCosConfig = fileCosConfigRepository.where(FileOssConfig.COMPANY_ID, companyId).get();
        COSClient cosClient = getClient(fileCosConfig);
        ObjectMetadata metadata = new ObjectMetadata();
        try {
            metadata.setContentLength(inputStream.available());
        } catch (IOException ignored) {}
        metadata.setContentDisposition("attachment;filename=\"" + infoVO.getFileName() + "\"");

        cosClient.putObject(fileCosConfig.getBucket(), infoVO.getUploadPath(), inputStream, metadata);
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }

        if (!cosClient.doesObjectExist(fileCosConfig.getBucket(), infoVO.getUploadPath())) {
            return null;
        }
        return fileCosConfig.getCname() + "/" + infoVO.getUploadPath();
    }

    public COSClient getClient(FileCosConfig fileCosConfig) {
        if (fileCosConfig == null) {
            throw new CommonRuntimeException("请先配置OSS");
        }
        COSClient cosClient = CLIENT_CACHE_MAP.get(fileCosConfig.getSecretId());
        if (cosClient != null) {
            return cosClient;
        }
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        COSCredentials cred = new BasicCOSCredentials(fileCosConfig.getSecretId(), fileCosConfig.getSecretKey());
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(fileCosConfig.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        CLIENT_CACHE_MAP.put(fileCosConfig.getSecretId(), cosClient);
        return cosClient;
    }

}
