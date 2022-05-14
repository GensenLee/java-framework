package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.InfoFieldsType;
import org.devops.mjar.live.polyv.enums.WatchAuthType;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 观看条件设置
 */
@EnableMjarLivePolyv
public class WatchConditionJunitTest  extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 查询频道观看条件
     */
    @Test
    public void searchViewCondition() {
        PolyvChannelSearchViewConditionRequester requester = channelClient().searchViewConditionRequest();
        PolyvApiResult<List<PolyvChannelViewCondition>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道观看条件
     * trial user pay price over limit	=> 试用账号的付费观看金额超过限制
     */
    @Test
    public void uploadViewCondition() {
        PolyvChannelUpdateViewConditionRequester requester = channelClient().updateViewConditionRequest();
        ///第一个
        PolyvChannelAuthSetting setting1 = new PolyvChannelAuthSetting();
        setting1.setRank(1);
        setting1.setEnabled(EnableSetting.Y);
        setting1.setAuthType(WatchAuthType.PAY);
        setting1.setPayAuthTips("hello");
        setting1.setPrice((float) 0.1);
        //第二个
        PolyvChannelAuthSetting.InfoField field = new PolyvChannelAuthSetting.InfoField();
        field.setName("hrllo");
        field.setType(InfoFieldsType.TEXT);
        List<PolyvChannelAuthSetting.InfoField> fields = new ArrayList<>();
        fields.add(field);

        PolyvChannelAuthSetting setting2= new PolyvChannelAuthSetting();
        setting2.setRank(1);
        setting2.setEnabled(EnableSetting.Y);
        setting2.setAuthType(WatchAuthType.INFO);
        setting2.setInfoFields(fields);


        List<PolyvChannelAuthSetting> setting = new ArrayList<>();
        setting.add(setting1);
        setting.add(setting2);
        requester.parameter().withAuthSettings(setting);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道外部授权设置
     */
    @Test
    public void updateAuthExtrnal() {
        PolyvChannelUpdateAuthExternalRequester requester = channelClient().updateAuthExternalRequest();
        requester.parameter().withExternalUri("www.baidu.com");
        PolyvApiResult<List<PolyvChannelAuthExternal>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道自定义授权设置
     */
    @Test
    public void updateOauthCustom() {
        PolyvChannelUpdateOauthCustomRequester requester = channelClient().updateOauthCustomRequest();
        requester.parameter().withExternalUri("www.baidu.com");
        PolyvApiResult<List<PolyvChannelAuthExternal>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道白名单
     */
    @Test
    public void searchWhiteLst() {
        PolyvChannelSearchWhiteLstRequester requester = channelClient().searchWhiteLstRequest();
        requester.parameter().withRank(1).withPage(1).withPageSize(10);
        PolyvApiResult<PolyvPaginator<PolyvChannelWhiteInfo>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 增加频道单个白名单
     */
    @Test
    public void addWhiteLst() {
        PolyvChannelAddWhiteListRequester requester = channelClient().addWhiteListRequest();
        requester.parameter().withCode("3344").withName("bigbos3s").withRank(1);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 批量导入频道白名单
     */
    @Test
    public void uploadWhiteList() throws Exception {
        PolyvChannelBatchUploadWhiteListRequester requester = channelClient().uploadWhiteListRequest();
        requester.parameter().withRank(1).withFile(
                new MockMultipartFile("q(1)", new FileInputStream(new File("C:\\Users\\Admin\\Downloads\\WhiteListTemplate (1).xls")))
        );
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道单个白名单
     */
    @Test
    public void updateWhiteList() {
        PolyvChannelUpdateWhiteListRequester requester = channelClient().updateWhiteListRequest();
        requester.parameter().withOldCode("3344").withCode("123334").withName("2211222").withRank(1);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除频道白名单
     */
    @Test
    public void deleteWhiteList() {
        PolyvChannelDeleteWhiteListRequester requester = channelClient().deleteWhiteListRequest();
        requester.parameter().withIsClear("N").withCode("1234").withRank(1);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *
     */
    @Test
    public void DownLoadWhiteList() {
//        @GetMapping( "/download" )
//        public ResponseEntity<byte[]> download() {
//            PolyvChannelDownLoadWhiteListRequester requester = channelClient().downLoadWhiteListRequest();
//            requester.parameter().withRank(1);
//            ResponseEntity<byte[]> entity = requester.execute();
//            return entity;
//
//        }
    }

    /**
     * 查询频道登记观看设置的字段信息
     */
    @Test
    public void searchData() {
        PolyvChannelSearchRecordFieldRequester requester = channelClient().searchRecordFieldRequest();
        requester.parameter().withRank(1);
        PolyvApiResult<List<PolyvChannelAuthSetting.InfoField>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}

