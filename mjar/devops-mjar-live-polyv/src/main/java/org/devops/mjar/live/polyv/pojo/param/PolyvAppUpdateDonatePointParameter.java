package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.polyv.pojo.model.PolyvDonateGoods;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2021/3/5 11:03
 * @description：打算设置更新
 */

@Data
public class PolyvAppUpdateDonatePointParameter extends AppSignBean {

    @SignIgnore
    /**
     * 积分打赏设置开关，取值Y/N
     */
    private String donatePointEnabled;

    @SignIgnore
    @VerifyField(ignore = true)
    /**
     * 积分查询接口URL
     */
    private String queryPointUrl;

    @SignIgnore
    @VerifyField(ignore = true)
    /**
     * 积分更新接口URL
     */
    private String updatePointUrl;

    @SignIgnore
    /**
     * 接口请求失败错误提示
     */
    private String requestFailTips;

    @SignIgnore
    /**
     * 打赏积分不足提示
     */
    private String pointNotEnoughTips;

    @SignIgnore
    /**
     * 积分的单位
     */
    private String pointUnit;

    @SignIgnore
    /**
     * 积分打赏道具列表
     */
    private List<PolyvDonateGoods> goods;


    public static final class PolyvAppUpdateDonatePointParameterBuilder extends ParameterBuilder<PolyvAppUpdateDonatePointParameter> {
        private PolyvAppUpdateDonatePointParameter polyvAppUpdateDonatePointParameter;

        private PolyvAppUpdateDonatePointParameterBuilder() {
            polyvAppUpdateDonatePointParameter = new PolyvAppUpdateDonatePointParameter();
        }

        public static PolyvAppUpdateDonatePointParameterBuilder aPolyvAppUpdateDonatePointParameter() {
            return new PolyvAppUpdateDonatePointParameterBuilder();
        }

        public PolyvAppUpdateDonatePointParameterBuilder withDonatePointEnabled(EnableSetting donatePointEnabled) {
            polyvAppUpdateDonatePointParameter.setDonatePointEnabled(donatePointEnabled.getValue());
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withQueryPointUrl(String queryPointUrl) {
            polyvAppUpdateDonatePointParameter.setQueryPointUrl(queryPointUrl);
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withUpdatePointUrl(String updatePointUrl) {
            polyvAppUpdateDonatePointParameter.setUpdatePointUrl(updatePointUrl);
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withRequestFailTips(String requestFailTips) {
            polyvAppUpdateDonatePointParameter.setRequestFailTips(requestFailTips);
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withPointNotEnoughTips(String pointNotEnoughTips) {
            polyvAppUpdateDonatePointParameter.setPointNotEnoughTips(pointNotEnoughTips);
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withPointUnit(String pointUnit) {
            polyvAppUpdateDonatePointParameter.setPointUnit(pointUnit);
            return this;
        }

        public PolyvAppUpdateDonatePointParameterBuilder withGoods(List<PolyvDonateGoods> goods) {
            polyvAppUpdateDonatePointParameter.setGoods(goods);
            return this;
        }

        @Override
        public PolyvAppUpdateDonatePointParameter build() {
            return polyvAppUpdateDonatePointParameter;
        }
    }
}
