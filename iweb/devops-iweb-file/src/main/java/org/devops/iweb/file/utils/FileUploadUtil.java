package org.devops.iweb.file.utils;

import org.devops.core.utils.constant.FileType;
import org.devops.core.utils.util.identifier.ULID;
import org.devops.iweb.file.vo.FileUploadInfoVO;

/**
 * @author GENSEN
 * @date 2021/10/14 15:55
 * @description：
 */
public abstract class FileUploadUtil {

    public static final ULID ULID = new ULID();

    public static String createPath(FileType fileType) {
        return FileType.getExtPath(fileType.getType()) + "/" + ULID.nextULID().toLowerCase() + "." + fileType.getType();
    }

    public static String createPath() {
        return createPath(FileType.UNKNOWN);
    }

    public static String createName(FileType fileType) {
        return ULID.nextULID().toLowerCase() + "." + fileType.getType();
    }

    public static String createName() {
        return createName(FileType.UNKNOWN);
    }

    public static FileUploadInfoVO createMetadata(FileType fileType) {
        String uid = ULID.nextULID().toLowerCase();
        String filePath = FileType.getExtPath(fileType.getType()) + "/" + uid + "." + fileType.getType();
        String fileName = uid + "." + fileType.getType();
        return new FileUploadInfoVO(fileName, filePath);
    }

    public static FileUploadInfoVO createMetadata(FileType fileType, String fileName) {
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + fileType.getType();
        String filePath = FileType.getExtPath(fileType.getType()) + "/" + System.currentTimeMillis() + "/" + fileName;
        return new FileUploadInfoVO(fileName, filePath);
    }

}
