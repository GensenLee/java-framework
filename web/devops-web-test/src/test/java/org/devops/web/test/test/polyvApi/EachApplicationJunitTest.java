package org.devops.web.test.test.polyvApi;

import com.google.common.collect.Lists;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvAppClient;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.QuestionaireType;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelAddReceiveInfoRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelQuestionnaireAddRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelQuestionnaireEndRequester;
import org.devops.mjar.live.polyv.operator.requester.PolyvChannelQuestionnaireListRequester;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelQuestionBasic;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelQuestions;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.devops.mjar.live.polyv.pojo.model.PolyvQuestionnaireReview;
import org.junit.Test;

/**
 * 互动应用
 */
@EnableMjarLivePolyv
public class EachApplicationJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    private PolyvAppClient appClient(){
        return PolyvClientBuilder.standardAppClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .build();
    }

    /**
     * 新增或修改频道问卷
     */
    @Test
    public void addQuestionnaire() {
        PolyvChannelQuestionnaireAddRequester requester = channelClient().questionnaireAddRequest();
        PolyvChannelQuestions questions = new PolyvChannelQuestions();
        questions.setName("test");
        questions.setType(QuestionaireType.C);
        questions.setOptions(Lists.newArrayList("A", "B", "C", "D"));
        requester.parameter()
                .withQuestions(Lists.newArrayList(questions))
                .withQuestionnaireTitle("bigboss");
        PolyvApiResult<PolyvChannelQuestionBasic> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 手动结束问卷
     */
    @Test
    public void endQuestionnaire() {
        PolyvChannelQuestionnaireEndRequester requester = appClient().questionnaireEndRequest();
        requester.parameter()
                .withChannelIds("2419208");
        PolyvApiResult<Object> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道问卷列表
     */
    @Test
    public void searchQuestionaireList() {
        PolyvChannelQuestionnaireListRequester requester = channelClient().questionnaireListRequest();
        requester.parameter().withPage(1).withPageSize(20);
        PolyvApiResult<PolyvPaginator<PolyvQuestionnaireReview>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 提交中奖者信息
     */
    @Test
    public void addReceiveInfo() {
        PolyvChannelAddReceiveInfoRequester requester = channelClient().addReceiveInfoRequest();
        requester.parameter()
                .withLotteryId("")
                .withWinnerCode("")
                .withViewerId("");
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
