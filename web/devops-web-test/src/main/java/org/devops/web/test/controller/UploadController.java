package org.devops.web.test.controller;

import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.operator.requester.PolyvAppUploadImageFileRequester;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUploadImageFileParameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2021/12/20 14:24
 * @description
 */
@RestController
public class UploadController {


    @PostMapping(value="/v1/upload.do")
    public Object upload(@RequestPart("file") MultipartFile file){
        PolyvAppClient appClient = PolyvClientBuilder.standardAppClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .build();

        PolyvAppUploadImageFileRequester uploadImageRequest = appClient.uploadImageRequest();
        uploadImageRequest.parameter()
                .withFile(file)
                .withType(PolyvAppUploadImageFileParameter.Type.SPLASH_IMAGE);

        PolyvApiResult<List<String>> execute = uploadImageRequest.execute();
        return execute;
    }


}
