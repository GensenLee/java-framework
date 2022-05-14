package org.devops.iweb.file.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.constant.FileType;
import org.devops.iweb.file.constant.IwebFileConstant;
import org.devops.iweb.file.image.Image;
import org.devops.iweb.file.vo.innerVO.ImageParamInnerVO;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileViewService {

    /**
     * 预览
     *
     * @param response
     * @param fileName
     */
    public void fileView(HttpServletResponse response, String fileName) {
        try {
            fileName = fileName.replace("/", File.separator);

            String realFileName = IwebFileConstant.getPath() + fileName;

            FileType fileType = FileType.get(fileName);
            log.debug("realFileName {} ,FileViewType {}", realFileName, fileType);

            String[] tmp = realFileName.split("@");
            ImageParamInnerVO imageParamVO = null;


            if (tmp.length == 2) {
                imageParamVO = new ImageParamInnerVO(tmp[1], fileType.getType());
                realFileName = tmp[0] + "@" + imageParamVO.toString();
            }

            File file = new File(realFileName);

            if (fileType.isImage()) {

                if (tmp.length == 2 && !file.exists()) {
                    //新建
                    String imgFilePath = tmp[0];
                    File imgfile = new File(imgFilePath);
                    if (!imgfile.exists()) {
                        log.debug(imgFilePath + "文件不存在");
                        return;
                    }
                    log.debug("imgFilePath {},param {}", imgFilePath, imageParamVO.toString());
                    Image image = new Image(imgfile);
                    log.debug("width {},height {}", image.getWidth(), image.getHeight());
                    log.debug("imageParamVO.toString() {}", imageParamVO.toString());
                    if (IntUtil.isZero(imageParamVO.getHeight()) && IntUtil.isNotZero(imageParamVO.getWidth())) {
                        int height = (int) ((float) image.getHeight() / image.getWidth() * imageParamVO.getWidth());
                        imageParamVO.setHeight(height);
                    }

                    if (IntUtil.isZero(imageParamVO.getWidth()) && IntUtil.isNotZero(imageParamVO.getHeight())) {
                        int width = (int) ((float) image.getWidth() / image.getHeight() * imageParamVO.getHeight());
                        imageParamVO.setWidth(width);
                    }

                    log.debug("imageParamVO.width {},imageParamVO.height {}", imageParamVO.getWidth(), imageParamVO.getHeight());

                    if (IntUtil.isNotZero(imageParamVO.getHeight()) && IntUtil.isNotZero(imageParamVO.getWidth())) {

                        if (imageParamVO.isWh()) {
                            //先判断裁剪的比例是不是符合要求
                            if ((float) image.getWidth() / image.getHeight() < (float) imageParamVO.getWidth() / imageParamVO.getHeight()) {
                                int height = (int) ((float) imageParamVO.getHeight() / imageParamVO.getWidth() * image.getWidth());
                                if (height < image.getHeight()) {
                                    log.debug("cutTo width:{},height:{}", image.getWidth(), height);
                                    image.cutTo(image.getWidth(), height);
                                }
                            } else {
                                int width = (int) ((float) imageParamVO.getWidth() / imageParamVO.getHeight() * image.getHeight());
                                if (width < image.getWidth()) {
                                    log.debug("cutTo width:{},height:{}", width, image.getHeight());
                                    image.cutTo(width, image.getHeight());
                                }

                            }
                        }

                        boolean alpha = false;
                        if ("PNG".equalsIgnoreCase(imageParamVO.getType())) {
                            alpha = true;
                        }

                        image.zoomTo(imageParamVO.getWidth(), imageParamVO.getHeight(), alpha);
                    }

                    if (IntUtil.isNotZero(imageParamVO.getQuality())) {
                        image = new Image(image.getJPEGBytes(imageParamVO.getQuality() / 100.0f));
                    }
                    log.debug("width {},height {}", image.getWidth(), image.getHeight());
                    file.createNewFile();
                    FileOutputStream fos = null;
                    fos = new FileOutputStream(file);
                    fos.write(image.getBytes(fileType.getType()));
                    fos.close();
                    fos = null;
                }
            }

            if (!file.exists()) {
                log.debug(realFileName + "文件不存在");
                return;
            }

            log.debug(realFileName);

            send(response, file, fileType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    private void send(HttpServletResponse response, File file, FileType fileType) {


        ServletOutputStream stream = null;
        BufferedInputStream fif = null;
        try {
            stream = response.getOutputStream();
            response.reset();
            response.setContentType(fileType.getContentType());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            fif = new BufferedInputStream(new FileInputStream(file));
            int d;
            byte[] buf = new byte[10240];
            while ((d = fif.read(buf)) != -1) {
                stream.write(buf, 0, d);
            }
            stream.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (fif != null) {
                    fif.close();
                }

            } catch (Exception e11) {
            }
        }
    }

}
