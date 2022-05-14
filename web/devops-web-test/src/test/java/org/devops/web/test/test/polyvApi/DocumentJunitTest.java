package org.devops.web.test.test.polyvApi;

import org.apache.http.entity.ContentType;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.DocumentType;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDocumentDeleteRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDocumentListRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDocumentStatusRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelDocumentUploadRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentInfo;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentStatus;
import org.devops.mjar.live.polyv.pojo.model.PolyvDocumentUpload;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDocumentUploadParameter;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 文档管理
 */
@EnableMjarLivePolyv
public class DocumentJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 上传文档到某个频道
     */
    @Test
    public void uploadDocument() throws Exception {
        PolyvChannelDocumentUploadRequester requester = channelClient().documentUploadRequest();

        File file = new File("C:\\Users\\VinceK\\Desktop\\test.ppt");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        requester.parameter()
                .withFile(multipartFile)
                .withDocName("test")
                .withCallbackUrl("http://www.baidu.com")
//                .withUrl("https://kovingter.oss-cn-shenzhen.aliyuncs.com/test.doc")
                .withType(PolyvChannelDocumentUploadParameter.Type.COMMON);
        PolyvApiResult<PolyvDocumentUpload> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道已上传文档列表
     */
    @Test
    public void getDocumentList() {
        PolyvChannelDocumentListRequester requester = channelClient().documentListRequest();
        PolyvApiResult<PolyvPaginator<PolyvDocumentInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道文档转码状态
     */
    @Test
    public void getDocumentStatus() {
        PolyvChannelDocumentStatusRequester requester = channelClient().documentStatusRequest();
        requester.parameter()
                .withFileId("ac6c9b8e88e7d222f5df2b733e77780f2419208common");
        PolyvApiResult<List<PolyvDocumentStatus>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocument() {
        PolyvChannelDocumentDeleteRequester requester = channelClient().documentDeleteRequest();
        requester.parameter()
                .withFileId("a1fd37ef14167b994478508ce6e8a9352419208common")
                .withType(DocumentType.NEW);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
