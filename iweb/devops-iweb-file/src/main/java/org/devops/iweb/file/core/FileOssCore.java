package org.devops.iweb.file.core;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import org.devops.core.utils.constant.FileType;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.file.model.FileOssConfig;
import org.devops.iweb.file.repository.FileOssConfigRepository;
import org.devops.iweb.file.utils.FileUploadUtil;
import org.devops.iweb.file.vo.FileUploadInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FileOssCore {

    private static final Map<String, OSSClient> CLIENT_CACHE_MAP = new ConcurrentHashMap<>();

    @Autowired
    private FileOssConfigRepository fileOssConfigRepository;

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
        FileOssConfig fileOssConfig = fileOssConfigRepository.where(FileOssConfig.COMPANY_ID, companyId).get();
        OSSClient ossClient = getClient(fileOssConfig);
        ObjectMetadata metadata = new ObjectMetadata();
        try {
            metadata.setContentLength(inputStream.available());
        } catch (IOException ignored) {
        }
//        metadata.setContentDisposition("attachment;filename=\"" + infoVO.getFileName() + "\"");

        FileType fileType = FileType.get(infoVO.getFileName());
        if (fileType != FileType.UNKNOWN) {
            metadata.setContentType(fileType.getContentType());
        }

        if (StringUtil.isNotEmpty(infoVO.getBucket())) {
            // 指定了bucket，不指定则使用默认值
            String bucket = fileOssConfig.getBucket();
            fileOssConfig.setBucket(infoVO.getBucket());
            fileOssConfig.setCname(fileOssConfig.getCname().replace(bucket, infoVO.getBucket()));
        }
        ossClient.putObject(fileOssConfig.getBucket(), infoVO.getUploadPath(), inputStream, metadata);
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }

        if (!ossClient.doesObjectExist(fileOssConfig.getBucket(), infoVO.getUploadPath())) {
            return null;
        }

        return fileOssConfig.getCname() + "/" + infoVO.getUploadPath();
    }

    private OSSClient getClient(FileOssConfig fileOssConfig) {
        if (fileOssConfig == null) {
            throw new CommonRuntimeException("请先配置OSS");
        }
        OSSClient ossClient = CLIENT_CACHE_MAP.get(fileOssConfig.getAccessKey());
        if (ossClient != null && ossClient.getCredentialsProvider().getCredentials().getSecretAccessKey().equals(fileOssConfig.getSecretKey())) {
            return ossClient;
        }
        DefaultCredentialProvider defaultCredentialProvider = new DefaultCredentialProvider(fileOssConfig.getAccessKey(), fileOssConfig.getSecretKey());
        ossClient = new OSSClient(fileOssConfig.getEndPoint(), defaultCredentialProvider, null);
        CLIENT_CACHE_MAP.put(fileOssConfig.getAccessKey(), ossClient);
        return ossClient;
    }
}
