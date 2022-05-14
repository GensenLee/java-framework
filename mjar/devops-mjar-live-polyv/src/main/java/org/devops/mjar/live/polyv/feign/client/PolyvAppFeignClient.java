package org.devops.mjar.live.polyv.feign.client;

import org.devops.mjar.live.polyv.feign.annotation.PolyvFeignClient;
import org.devops.mjar.live.polyv.feign.annotation.PolyvGetMapping;
import org.devops.mjar.live.polyv.feign.annotation.PolyvPostMapping;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.model.*;
import org.devops.mjar.live.polyv.pojo.param.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2020/10/21 11:47
 * @description：应用相关
 */
@PolyvFeignClient(name = "PolyvAppFeignClient")
public interface PolyvAppFeignClient {

    /**
     * 更新应用
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/app/update")
    PolyvApiResult<PolyvApp> update(@RequestParam Map<String, Object> param);

    /**
     * 设置状态
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/app/set-status")
    PolyvApiResult<String> setStatus(@RequestParam Map<String, Object> param);

    /**
     * 应用基础图片--修改
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/set-basic-img")
    PolyvApiResult setBase(@RequestParam Map<String, Object> param);

    /**
     * 设置用户微信分享自定义URL接口
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/set-weixin-share-api")
    PolyvApiResult setWeixinShareApi(@RequestParam Map<String, Object> param);

    /**
     * 获取用户微信分享自定义URL接口
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/get-weixin-share-api")
    PolyvApiResult<String> getWeixinShareApi(@RequestParam Map<String, Object> param);


    /*----------------------------------------------------------*/


    /**
     * 设置后台回放开关
     * 1、作用：能够控制单个/全部频道的回放开关，开启以及关闭。
     * 2、接口URL中的{userId}为 直播账号ID
     * 3、接口支持https协
     * https://dev.polyv.net/2017/liveproduct/l-api/zbglgn/lzhf/setplaybackenabled/
     *
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping("/live/v2/channelSetting/{userId}/setPlayBackEnabled")
    PolyvApiResult<Object> setPlayBackEnabled(PolyvAppUpdatePlayBackEnabledParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 设置云平台日志入库回调通知URL
     *
     * @param req
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/set-ccb-viewlog-callback")
    PolyvApiResult<Object> setViewLogCallbackUrl(PolyvAppUrlParameter req);


    /**
     * 查询云平台日志入库回调通知URL
     *
     * @param req
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/get-ccb-viewlog-callback")
    PolyvApiResult<String> getViewLogCallbackUrl(PolyvAppNoneParamParameter req);


    /**
     * 新增应用接口
     * http://dev.polyv.net/2019/uncategorized/add-app/
     *
     * @param req
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/app/add")
    PolyvApiResult<PolyvApp> create(PolyvAppCreateParameter req);

    /**
     * 查询多个频道的实时在线人数
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/gksj/get-realtipme-viewers/
     *
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/statistics/get-realtime-viewers")
    PolyvApiResult<List<PolyvChannelViewers>> getRealtimeViewerList(@RequestParam PolyvChannelsRealtimeViewerListParameter param);

    /**
     * 批量删除频道,每次最多100个
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-delete-channels/
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/basic/batch-delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> _batchDeleteChannel(@RequestParam Map<String, Object> param, @RequestBody Object body);

    default PolyvApiResult<Object> batchDeleteChannel(PolyvAppBatchDeleteChannelParameter param) {
        Map<String, Object> map = param.toMap();
        map.remove("channelIds");
        return _batchDeleteChannel(map, new HashMap<String, Object>(1) {{
            put("channelIds", param.getChannelIds());
        }});
    }

    /**
     * 批量创建channel
     * https://dev.polyv.net/2018/liveproduct/l-api/zbglgn/pdcz/batch-create-channels/
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/basic/batch-create", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<PolyvChannelList> _batchCreateChannel(@RequestParam Map<String, Object> param, @RequestBody Object body);

    default PolyvApiResult<PolyvChannelList> batchCreateChannel(PolyvAppBatchCreateChannelParameter param) {
        Map<String, Object> map = param.toMap();
        map.remove("channels");
        return _batchCreateChannel(map, new HashMap<String, Object>(1) {{
            put("channels", param.getChannels());
        }});
    }

    /**
     * 修改打赏设置
     *
     * @param param
     * @param body
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/donate/update-point", consumes = MediaType.APPLICATION_JSON_VALUE)
    PolyvApiResult<Object> _updateDonatePoint(@RequestParam Map<String, Object> param, @RequestBody Object body);

    default PolyvApiResult<Object> updateDonatePointSetting(PolyvAppUpdateDonatePointParameter param) {
        return _updateDonatePoint(param.toMap(), param);
    }

    /**
     * 查询打赏设置
     * https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/donate-get/
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/channel/donate/get")
    PolyvApiResult<PolyvDonatePointSettingModel> getDonatePointSetting(@RequestParam PolyvAppChannelDonatePointSettingParameter param);

    /**
     * 查询频道点赞数和观看次数
     * https://help.polyv.net/index.html#/live/api/web/info/live_likes
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v2/channels/live-likes")
    PolyvApiResult<List<PolyvLiveLikes>> getLiveLikes(@RequestParam PolyvChannelLiveLikesParameter param);

    /**
     * 查询多个频道概览统计数据
     * https://help.polyv.net/index.html#/live/api/channel/viewdata/channel_play_summary
     *
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/statistics/{userId}/channel_summary")
    PolyvApiResult<Object> getChannelSummary(@RequestParam PolyvAppStatisticsChannelSummaryParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 修改频道密码
     * https://help.polyv.net/index.html#/live/api/channel/operate/update_password
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/{userId}/passwdSetting")
    PolyvApiResult<Boolean> updateChannelPasswd(@RequestParam PolyvChannelUpdatePasswdParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 修改频道主持人姓名
     * https://help.polyv.net/index.html#/live/api/web/info/setpublisher
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channelSetting/{userId}/setPublisher")
    PolyvApiResult<String> updatePublisher(@RequestParam PolyvChannelUpdatePublisherParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 查询批量频道状态
     * https://help.polyv.net/index.html#/live/api/channel/state/get_live_status_list
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/channels/live-status")
    PolyvApiResult<List<PolyvLiveStatus>> getLiveStatus(@RequestParam PolyvChannelLiveStatusParameter param);

    /**
     * 删除直播分类
     * https://help.polyv.net/index.html#/live/api/account/delete_category
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/category/delete")
    PolyvApiResult<String> deleteChannelCategory(@RequestParam PolyvAppDeleteCategoryParameter param);

    /**
     * 修改直播分类名称
     * https://help.polyv.net/index.html#/live/api/account/update_category_name
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/category/update-name")
    PolyvApiResult<String> updateChannelCategoryName(@RequestParam PolyvAppUpdateCategoryNameParameter param);

    /**
     * 修改直播分类排序
     * https://help.polyv.net/index.html#/live/api/account/update_category_rank
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/category/update-rank")
    PolyvApiResult<String> updateChannelCategoryRank(@RequestParam PolyvAppUpdateCategoryRankParameter param);

    /**
     * 批量修改频道弹幕开关
     * https://help.polyv.net/index.html#/live/api/channel/operate/batch_update_danmu
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/basic/batchUpdateDanmu")
    PolyvApiResult<String> updateBatchDanmu(@RequestParam PolyvAppBatchUpdateDanmuParameter param);

    /**
     * 创建直播分类
     * https://help.polyv.net/index.html#/live/api/account/create_category
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/category/create")
    PolyvApiResult<PolyvCategory> createCategory(@RequestParam PolyvAppCreateCategoryParameter param);

    /**
     * 查询直播分类
     * https://help.polyv.net/index.html#/live/api/account/get_category_list
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/category/list")
    PolyvApiResult<List<PolyvCategory>> getCategoryList(@RequestParam PolyvAppNoneParamParameter param);

    /**
     * 批量导入严禁词
     * https://help.polyv.net/index.html#/live/api/chat/banned/add_badwords
     *
     * @param param
     * @param userId
     * @return
     */
    @PolyvPostMapping(value = "/live/v2/chat/{userId}/addBadWords")
    PolyvApiResult<List<PolyvBadWords>> addBadWords(@RequestParam PolyvAppAddBadWordsParameter param, @PathVariable(name = "userId") String userId);

    /**
     * 查询账号严禁词
     * https://help.polyv.net/index.html#/live/api/chat/banned/get_user_badword_list
     *
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/user/badword/list")
    PolyvApiResult<List<String>> getUserBadWord(@RequestParam PolyvAppNoneParamParameter param);

    /**
     * 删除账号严禁词
     * https://help.polyv.net/index.html#/live/api/chat/banned/delete_user_badword
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/badword/delete")
    PolyvApiResult<List<String>> deleteUserBadWord(@RequestParam PolyvAppBadWordDeleteParameter param);

    /**
     * 查询账号禁言列表
     * https://help.polyv.net/index.html#/live/api/chat/banned/get_user_banned_list
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v3/user/chat/banned-user/list")
    PolyvApiResult<List<PolyvBanned>> getUserBannedList(@RequestParam PolyvAppBannedListParameter param);

    /**
     * 账号设置禁言/解禁用户
     * https://help.polyv.net/index.html#/live/api/chat/banned/update_user_banned_viewer
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/chat/banned-user/update")
    PolyvApiResult<String> updateUserBannedViewer(@RequestParam PolyvAppUpdateBannedParameter param);

    /**
     * 手动结束问卷
     * https://help.polyv.net/index.html#/live/api/channel/operate/channels_stop_questionnaire
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/channel/questionnaire/end")
    PolyvApiResult<Object> endQuestionnaire(@RequestParam PolyvChannelQuestionnaireEndParameter param);

    /**
     * 设置账号单点登录token
     * https://help.polyv.net/index.html#/live/api/account/set_user_login_token
     * @param param
     * @return
     */
    @PolyvPostMapping(value = "/live/v3/user/set-sso-token")
    PolyvApiResult<Object> setSsoToken(@RequestParam PolyvAppUpdateSsoTokenParameter param);

    /**
     * 批量查询频道单个回放信息
     * https://help.polyv.net/index.html#/live/api/v4/channel/play-back/query-play-back-video-info
     * @param param
     * @return
     */
    @PolyvGetMapping(value = "/live/v4/channel/play-back/get")
    PolyvApiResult<List<PolyvPlayBackInfo>> getPlayBack(@RequestParam PolyvChannelPlayBackGetParameter param);

}
