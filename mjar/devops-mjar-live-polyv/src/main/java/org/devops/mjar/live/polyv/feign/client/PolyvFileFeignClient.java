package org.devops.mjar.live.polyv.feign.client;

import org.devops.core.utils.util.BeanUtil;
import org.devops.mjar.live.polyv.feign.annotation.PolyvFeignClient;
import org.devops.mjar.live.polyv.feign.annotation.PolyvPostMapping;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppImageUploadParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUploadImageFileParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/10/28 18:01
 * @description：文件相关
 */
@PolyvFeignClient(name = "PolyvFileFeignClient")
public interface PolyvFileFeignClient {

    /**
     * 图片上传
     * https://help.polyv.net/index.html#/live/api/web/setting/uploadimage
     *
     * @param file
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/common/upload-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PolyvApiResult<List<String>> _uploadImage(@RequestParam PolyvAppImageUploadParameter param, @RequestPart("file") MultipartFile file );

    default PolyvApiResult<List<String>> uploadImage(PolyvAppUploadImageFileParameter param) {
        return _uploadImage(BeanUtil.copy(param, PolyvAppImageUploadParameter.class), param.getFile());
    }
}
