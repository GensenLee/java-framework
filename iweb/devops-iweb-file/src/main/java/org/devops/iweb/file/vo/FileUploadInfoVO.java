package org.devops.iweb.file.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.utils.vo.BaseBean;

/**
 * @author GENSEN
 * @date 2021/10/14 16:11
 * @description：文件上传信息
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class FileUploadInfoVO extends BaseBean {
    public FileUploadInfoVO() {
    }

    public FileUploadInfoVO(String fileName, String uploadPath) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
    }

    public FileUploadInfoVO(String fileName, String uploadPath, String bucket) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.bucket = bucket;
    }

    private String fileName;
    private String uploadPath;
    private String bucket;

}
