package org.devops.iweb.file.service;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.constant.FileType;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.FileUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.iweb.file.configuration.IWebFileConfiguration;
import org.devops.iweb.file.constant.IwebFileConstant;
import org.devops.iweb.file.vo.outVO.FileUploadOutVO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {

    public FileUploadOutVO uploadFile(String oriFileName, InputStream in, long size) {

        FileUploadOutVO fileUploadOutVO = new FileUploadOutVO();

        Calendar calendar = Calendar.getInstance();

        String year = StringUtil.fixed(calendar.get(Calendar.YEAR), 4);
        String month = StringUtil.fixed((calendar.get(Calendar.MONTH) + 1), 2);
        String day = StringUtil.fixed(calendar.get(Calendar.DATE), 2);

        String ext = FileUtil.getFileSuffix(oriFileName);
        //保存相对路径地址
        String filePath = FileType.getExtPath(ext) + File.separator + year + File.separator + month + File.separator + day;
        FileUtil.mkDir(IwebFileConstant.getPath() + File.separator + filePath);


        String fileName = UUID.randomUUID().toString() + ext;

        try {
            FileUtil.copyFile(in, IwebFileConstant.getPath() + File.separator + filePath, fileName);
        } catch (Exception e) {
            throw new CommonRuntimeException("文件上传异常", e);
        }

        String path = IWebFileConfiguration.PTAH_PREFIX + FileType.getExtPath(ext) + "/" + year + "/" + month + "/" + day + "/" + fileName;
        fileUploadOutVO.setPath(path);
        fileUploadOutVO.setFullPath(IwebFileConstant.getDomain() + path);
        fileUploadOutVO.setSize(size);
        fileUploadOutVO.setOriName(oriFileName);
        fileUploadOutVO.setDomain(IwebFileConstant.getDomain());
        return fileUploadOutVO;
    }
}
