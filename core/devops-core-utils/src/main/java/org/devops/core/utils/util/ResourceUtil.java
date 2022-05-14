package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 替代原 IOUtil
 * @author GENSEN
 */
@Slf4j
public class ResourceUtil {

    private static final String PATH_PREFIX = "classpath:";

    /**
     * 读取资源文件流
     * @param path
     * @return
     */
    public static InputStream readResourceAsStream(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
        } catch (Exception e) {
            if (path.startsWith(PATH_PREFIX)) {
                path = path.replaceFirst(PATH_PREFIX, "");
            }else {
                path += PATH_PREFIX;
            }
            classPathResource = new ClassPathResource(path);
            try {
                inputStream = classPathResource.getInputStream();
            } catch (IOException e1) {
                log.error("ResourceUtil.readResourceAsStream", e1);
            }
        }
        return inputStream;
    }

    /**
     * 读取资源文件文本
     * @param path
     * @return
     */
    public static String readResourceAsString(String path){
        InputStream inputStream = readResourceAsStream(path);
        try {
            return StreamUtil.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("ResourceUtil.readResourceAsString", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return "";
    }

}
