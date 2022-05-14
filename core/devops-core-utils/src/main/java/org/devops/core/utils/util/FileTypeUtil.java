package org.devops.core.utils.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tika.detect.Detector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author GENSEN
 * @date 2021/5/28 11:34
 * @description：文件类型助手
 */
public class FileTypeUtil {
    private static final Log log = LogFactory.getLog(FileTypeUtil.class);


    /**
     * 将字节数组转换成16进制字符串
     */
    private static String bytes2hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件头
     *
     * @param inputStream
     * @return 16 进制的文件投信息
     * @throws IOException
     */
    private static String getFileHeader(InputStream inputStream) throws IOException {
        byte[] b = new byte[28];
        inputStream.read(b, 0, 28);
        inputStream.close();
        return bytes2hex(b);
    }

    public static MediaType getFileType(InputStream inputStream) {
        return getFileType(inputStream, null);
    }

    /**
     * 获取文件 MediaType
     *
     * @param inputStream
     * @param fileName org.apache.tika.metadata.TikaCoreProperties
     * @return
     */
    public static MediaType getFileType(InputStream inputStream, String fileName) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        AutoDetectParser parser = new AutoDetectParser();
        Detector detector = parser.getDetector();
        Metadata md = new Metadata();
        if (StringUtil.isNotEmpty(fileName)) {
            md.add(TikaCoreProperties.RESOURCE_NAME_KEY, fileName);
        }
        MediaType mediaType = null;
        try {
            mediaType = detector.detect(bufferedInputStream, md);
        } catch (IOException e) {
            log.error("IOException", e);
        }
        return mediaType;
    }
}