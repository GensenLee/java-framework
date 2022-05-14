package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.enums.InfoFieldsType;
import org.devops.mjar.live.polyv.enums.WatchAuthType;
import lombok.Data;

import java.util.List;

@Data
public class PolyvChannelAuthSetting extends BaseBean {
    /**
     * 主要观看条件为1，次要观看条件为2
     */
    private Integer rank;

    /**
     * 是否开启条件观看
     * N：关闭
     * Y：开启
     */
    private String enabled;

    public void setEnabled(EnableSetting enabled) {
        if (enabled == null) {
            return;
        }
        this.enabled = enabled.getValue();
    }

    /**
     * pay：付费观看
     * code：验证码观看
     * phone：白名单观看
     * info：登记观看
     * custom：自定义授权观看
     * external：外部授权观看
     * direct：直接授权观看
     */
    private String authType;

    public void setAuthType(WatchAuthType authType) {
        if (authType == null) {
            return;
        }
        this.authType = authType.getCode();
    }

    /**
     * 当authType为pay时，设置参数，必填。欢迎语标题
     */
    private String payAuthTips;

    /**
     * authType为pay时，设置参数，必填。价格，单位为元
     */
    private Float price;

    /**
     * 当authType为pay时，设置参数，非必填。付费有效截止日期，格式：yyyy-MM-dd HH:mm
     */
    private String watchEndTime;

    /**
     * 当authType为pay时，设置参数，非必填。付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效
     */
    private Integer validTimePeriod;

    /**
     * 当authType为code时，设置参数，必填。验证码
     */
    private String authCode;

    /**
     * 当authType为code时，设置参数，非必填。提示文案
     */
    private String qcodeTips;

    /**
     * 当authType为code时，设置参数，非必填。公众号二维码地址
     */
    private String qcodeImg;

    /**
     * 当authType为phone时，设置参数，非必填。提示文案
     */
    private String authTips;

    /**
     * 当authType为info时，设置参数，必填。登记观看信息，上限为5个【详见infoFields字段说明】
     */
    private List<InfoField> infoFields;

    /**
     * 当authType为external时，设置参数，必填。SecretKey
     */
    private String externalKey;

    /**
     * 当authType为external时，设置参数，必填。自定义url
     */
    private String externalUri;

    /**
     * 当authType为external时，设置参数，非必填。跳转地址
     */
    private String externalRedirectUri;

    /**
     * 当authType为custom时，设置参数，必填。SecretKey
     */
    private String customKey;

    /**
     * 当authType为custom时，设置参数，必填。自定义url
     */
    private String customUri;

    /**
     * 当authType为direct时，设置参数，必填。直接授权SecretKey
     */
    private String directKey;

    /**
     * infoFields字段说明
     */
    @Data
    public static class InfoField {
        /**
         * 当authType为info时，设置参数，非必填。登记信息名，最多为8字符
         */
        private String name;

        /**
         * 当authType为info时，设置参数，必填。登记类型
         * name：姓名
         * text：文本
         * mobile：手机号码
         * number：数字
         * option：下拉选项
         */
        private String type;

        public void setType(InfoFieldsType type) {
            this.type = type.getCode();
        }

        /**
         * 当authType为info时，设置参数，非必填。下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符
         */
        private String options;

        /**
         * 当authType为info时，设置参数，非必填。文本框输入提示，最多为8字符
         */
        private String placeholder;

        /**
         * 当authType为info时，设置参数，非必填。短信验证开关，Y：开启，N：关闭
         */
        private String sms;

        public void setSms(EnableSetting sms) {
            if (sms == null) {
                return;
            }
            this.sms = sms.getValue();
        }
    }

}