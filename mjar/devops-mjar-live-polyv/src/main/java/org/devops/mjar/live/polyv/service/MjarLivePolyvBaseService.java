package org.devops.mjar.live.polyv.service;

import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.helper.PolyvSignHelper;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.*;
import org.devops.core.utils.vo.HttpResult;
import org.devops.mjar.live.polyv.pojo.model.PolyvChatList;
import org.devops.mjar.live.polyv.pojo.model.PolyvUrl;
import org.devops.mjar.live.core.exception.LiveApiRuntimeException;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUpdateSsoTokenParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelChatListParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelClientStartUrlParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelDirectPlayUrlParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelGuestPlayUrlParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelStartUrlParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateAccountTokenParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateIntroduceMenuParameter;
import org.devops.mjar.live.polyv.pojo.param.PolyvChannelUpdateTokenParameter;
import org.devops.mjar.live.core.properties.LiveApiHostProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/3/8 15:54
 * @description：本地业务逻辑
 */
@Service
public class MjarLivePolyvBaseService {

    @Value("${polyv.host.api:http://api.polyv.net}")
    private String polyvHost;

    /**
     * 独立授权观看
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getDirectPlayUrl(PolyvChannelDirectPlayUrlParameter param) {
        String nicknameEncode;
        try {
            nicknameEncode = URLEncoder
                    .encode(
                            Base64.getEncoder().encodeToString(param.getNickname().getBytes(StandardCharsets.UTF_8.name())),
                            StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new LiveApiRuntimeException("昵称转码失败");
        }
        String sign = MD5.getMD5(param.getSecretKey() + param.getViewerid() + param.getSecretKey() + param.getTimestamp());
        if (StringUtil.isEmpty(param.getBaseHost())) {
            LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
            param.setBaseHost(properties.getLive());
        }
        if (!param.getBaseHost().endsWith(CommonConstant.SLANTING_MARK)) {
            param.setBaseHost(param.getBaseHost() + CommonConstant.SLANTING_MARK);
        }

        StringBuilder builder = new StringBuilder(param.getBaseHost());
        builder.append("watch/")
                .append(param.getChannelId())
                .append("?userid=")
                .append(param.getViewerid())
                .append("&ts=")
                .append(param.getTimestamp())
                .append("&sign=")
                .append(sign)
                .append("&nickname=")
                .append(nicknameEncode)
                .append("&avatar=")
                .append(param.getAvatar());
        return PolyvApiResult.getSuccess(new PolyvUrl(builder.toString()));
    }

    /**
     * 嘉宾链接
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getGuestPlayUrl(PolyvChannelGuestPlayUrlParameter param) {
        if (StringUtil.isEmpty(param.getBaseHost())) {
            LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
            param.setBaseHost(properties.getStart());
        }
        if (!param.getBaseHost().endsWith(CommonConstant.SLANTING_MARK)) {
            param.setBaseHost(param.getBaseHost() + CommonConstant.SLANTING_MARK);
        }

        String builder = param.getBaseHost() + "web-start/guest?channelId=" +
                param.getAccount();
        return PolyvApiResult.getSuccess(new PolyvUrl(builder));
    }


    /**
     * 助教链接
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getAssistantPlayUrl(PolyvChannelGuestPlayUrlParameter param) {
        if (StringUtil.isEmpty(param.getBaseHost())) {
            LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
            param.setBaseHost(properties.getStart());
        }
        if (!param.getBaseHost().endsWith(CommonConstant.SLANTING_MARK)) {
            param.setBaseHost(param.getBaseHost() + CommonConstant.SLANTING_MARK);
        }

        String builder = param.getBaseHost() + "teacher.html?account=" + param.getAccount();
        return PolyvApiResult.getSuccess(new PolyvUrl(builder));
    }


    /**
     * 开播链接
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getHostStartUrl(PolyvChannelStartUrlParameter param) {
        if (StringUtil.isEmpty(param.getBaseHost())) {
            LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
            param.setBaseHost(properties.getStart());
        }
        if (!param.getBaseHost().endsWith(CommonConstant.SLANTING_MARK)) {
            param.setBaseHost(param.getBaseHost() + CommonConstant.SLANTING_MARK);
        }
        String builder = param.getBaseHost() + "web-start/?channelId=" + param.getChannelId();
        return PolyvApiResult.getSuccess(new PolyvUrl(builder));
    }

    private final static String CLIENT_START_URL = "polyvlive://start?pdn=api.polyv.net&channelId=%s&passwd=%s";

    /**
     * 客户端启动链接
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getClientStartUrl(PolyvChannelClientStartUrlParameter param) {
        String account = param.getAccount();
        if (StringUtil.isEmpty(account)) {
            account = param.getChannelId();
        }
        String format = String.format(CLIENT_START_URL, account, param.getPassword());
        PolyvUrl polyvUrl = new PolyvUrl(format);
        return PolyvApiResult.getSuccess(polyvUrl);
    }

    /**
     * 修改直播介绍菜单
     */
    public PolyvApiResult<String> updateIntroductMenu(PolyvChannelUpdateIntroduceMenuParameter param) {
        Map<String, Object> map = new HashMap<>();
        map.put("appId", param.getAppId());
        map.put("timestamp", param.getTimestamp());
        map.put("content", param.getContent());
        map.put("menuType", param.getMenuType());
        map.put("sign", param.getSign());
        String httpUrl = String.format(polyvHost + "/live/v2/channelSetting/%s/%s/set-menu", param.getUserId(), param.getChannelId());
        HttpResult httpResult = HttpUtils.doHttpPost(httpUrl, map);
        return PolyvApiResult.getSuccess(httpResult.readResponseAsString());
    }

    /**
     * 账号后台登录
     *
     * @return
     */
    public PolyvApiResult<PolyvUrl> getUserLoginUrl(PolyvAppUpdateSsoTokenParameter param) {
        String userId = param.getUserId();
        String token = param.getToken();
        if (StringUtil.isEmpty(userId) && StringUtil.isEmpty(token)) {
            return new PolyvApiResult<>();
        }

        LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
        String start = properties.getStart();
        String builder = start + "/v2/sso/userLogin.do?userId=" + userId + "&token=" + token;
        PolyvUrl polyvUrl = new PolyvUrl(builder);
        return PolyvApiResult.getSuccess(polyvUrl);
    }

    /**
     * 频道后台登录
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getChannelLoginUrl(PolyvChannelUpdateTokenParameter param) {
        String channelId = param.getChannelId();
        String token = param.getToken();
        if (StringUtil.isEmpty(channelId) && StringUtil.isEmpty(token)) {
            return new PolyvApiResult<>();
        }

        LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
        String start = properties.getStart();
        String redirect = start + "/web-start/?channelId=" + channelId;
        String builder = start + "/teacher/auth-login?channelId=" + channelId + "&token=" + token + "&redirect=" + redirect;
        PolyvUrl polyvUrl = new PolyvUrl(builder);
        return PolyvApiResult.getSuccess(polyvUrl);
    }

    /**
     * 子频道后台登录
     *
     * @param param
     * @return
     */
    public PolyvApiResult<PolyvUrl> getAccountLoginUrl(PolyvChannelUpdateAccountTokenParameter param) {
        String accountId = param.getAccountId();
        String token = param.getToken();
        if (StringUtil.isEmpty(accountId) && StringUtil.isEmpty(token)) {
            return new PolyvApiResult<>();
        }
        LiveApiHostProperties properties = SpringContextUtil.getBean(LiveApiHostProperties.class);
        String start = properties.getStart();
        String builder = start + "/teacher/auth-login?channelId=" + accountId + "&token=" + token;
        PolyvUrl polyvUrl = new PolyvUrl(builder);
        return PolyvApiResult.getSuccess(polyvUrl);
    }

    /**
     * 获取聊天室在线列表
     */
    public PolyvChatList getChatOnLineList(PolyvChannelChatListParameter parameter) {
        Map<String, Object> map = new HashMap<>();
        map.put("roomId", parameter.getRoomId());
        if (parameter.getPage() != null) {
            map.put("page", parameter.getPage());
        }
        if (parameter.getLen() != null) {
            map.put("len", parameter.getLen());
        }
        if (parameter.getCallback() != null) {
            map.put("callback", parameter.getCallback());
        }
        if (parameter.getToGetSubRooms() != null) {
            map.put("toGetSubRooms", parameter.getToGetSubRooms());
        }

        String sign = PolyvSignHelper.sign(map, parameter.getSign());
        map.put("sign", sign);

        String response = HttpUtils.doGet("https://apichat.polyv.net/front/userlistExternal", map);
        System.out.println(response);
        String encrypted = "fd1206646ea5e702852157";
        try {
            byte[] bytes = new AES().decrypt(response.getBytes(), response.substring(0, 16).getBytes(), response.substring(0, 16).getBytes());
            System.out.println(bytes.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);

        return new PolyvChatList();
    }


}
