package org.devops.mjar.live.polyv.pojo.param;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;

/**
 * @author bigboss
 * @date 2021/7/26 16:04
 * @description：添加频道商品
 */
@Data
public class PolyvChannelCreateProductParameter extends ChannelSignBean {

    @SignIgnore
    private PolyvChannelCreateProductParameterBody body = new PolyvChannelCreateProductParameterBody();

    @Data
    public static class PolyvChannelCreateProductParameterBody extends BaseBean {
        /**
         * 商品名称，必填，长度范围：1-60
         */
        private String name;
        /**
         * 商品上下架状态
         * 1：上架状态
         * 2：下架状态
         */
        private Integer status;
        /**
         *
         商品链接类型
         10：通用链接
         11：多平台链接
         */
        private Integer linkType;

        /**
         * 通用链接，商品链接，链接地址长度为1-500
         * 当请求参数linkType=10时，该参数必填
         * 当请求参数linkType=11时，该参数为非必填
         */
        @VerifyField(ignore = true)
        private String link;

        /**
         * 通用链接，商品链接，链接地址长度为1-500
         * 当请求参数linkType=10时，该参数必填
         * 当请求参数linkType=11时，该参数为非必填
         */
        @VerifyField(ignore = true)
        private String pcLink;

        /**
         * 多平台链接，移动web端链接
         */
        @VerifyField(ignore = true)
        private String mobileLink;

        /**
         * 多平台链接，小程序内页面路径及参数（对于path属性，
         * 所声明的页面路径必须添加.html后缀，如pages/home/index.html）
         */
        @VerifyField(ignore = true)
        private String wxMiniprogramLink;

        /**
         * 多平台链接，小程序端原始Id
         */
        @VerifyField(ignore = true)
        private String wxMiniprogramOriginalId;

        /**
         * 多平台链接，移动App端链接
         */
        @VerifyField(ignore = true)
        private String mobileAppLink;

        /**
         * 多平台链接，移动APP-安卓系统原生页面链接
         */
        @VerifyField(ignore = true)
        private String androidLink;

        /**
         * 多平台链接，移动APP-苹果系统原生页面链接
         */
        @VerifyField(ignore = true)
        private String iosLink;

        /**
         * 自定义参数，类型json对象，需要转为string
         */
        @VerifyField(ignore = true)
        private String params;

        /**
         * 产品描述
         */
        @VerifyField(ignore = true)
        private String productDesc;

        /**
         * 产品特色，卖点标签，可以多个，json字段，如：["tab1", "tab2"]
         */
        @VerifyField(ignore = true)
        private String features;

        /**
         * 普通商品参数，商品封面链接，商品类型为普通商品时必传，长度范围：1-500
         */
        private String cover;

        /**
         * 普通商品参数，商品实际价格，商品类型为普通商品时必传，保留两位小数，如：0.01
         */
        @VerifyField(ignore = true)
        private Float realPrice;

        /**
         *普通商品参数，商品原价，保留两位小数，如：0.01
         */
        @VerifyField(ignore = true)
        private Float price;

        /**
         * 金融商品参数，按钮显示文案
         */
        @VerifyField(ignore = true)
        private String btnShow;

        /**
         * 金融商品参数，产品收益率/价格
         */
        @VerifyField(ignore = true)
        private String yield;

        /**
         *商品类型，默认为普通商品
         * normal：普通商品
         * finance：金融商品
         */
        @VerifyField(ignore = true)
        private String productType;
    }

    public static final class PolyvChannelCreateProductParameterBuilder extends ParameterBuilder<PolyvChannelCreateProductParameter>{
        private PolyvChannelCreateProductParameter polyvChannelCreateProductParameter;

        private PolyvChannelCreateProductParameterBuilder() {
            polyvChannelCreateProductParameter = new PolyvChannelCreateProductParameter();
        }

        public static PolyvChannelCreateProductParameterBuilder aPolyvChannelCreateProductParameter() {
            return new PolyvChannelCreateProductParameterBuilder();
        }

        public PolyvChannelCreateProductParameterBuilder withName(String name) {
            polyvChannelCreateProductParameter.getBody().setName(name);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withStatus(Integer status) {
            polyvChannelCreateProductParameter.getBody().setStatus(status);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withLinkType(Integer linkType) {
            polyvChannelCreateProductParameter.getBody().setLinkType(linkType);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withLink(String link) {
            polyvChannelCreateProductParameter.getBody().setLink(link);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withPcLink(String pcLink) {
            polyvChannelCreateProductParameter.getBody().setPcLink(pcLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withMobileLink(String mobileLink) {
            polyvChannelCreateProductParameter.getBody().setMobileLink(mobileLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withWxMiniprogramLink(String wxMiniprogramLink) {
            polyvChannelCreateProductParameter.getBody().setWxMiniprogramLink(wxMiniprogramLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withWxMiniprogramOriginalId(String wxMiniprogramOriginalId) {
            polyvChannelCreateProductParameter.getBody().setWxMiniprogramOriginalId(wxMiniprogramOriginalId);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withMobileAppLink(String mobileAppLink) {
            polyvChannelCreateProductParameter.getBody().setMobileAppLink(mobileAppLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withAndroidLink(String androidLink) {
            polyvChannelCreateProductParameter.getBody().setAndroidLink(androidLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withIosLink(String iosLink) {
            polyvChannelCreateProductParameter.getBody().setIosLink(iosLink);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withParams(String params) {
            polyvChannelCreateProductParameter.getBody().setParams(params);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withProductDesc(String productDesc) {
            polyvChannelCreateProductParameter.getBody().setProductDesc(productDesc);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withFeatures(String features) {
            polyvChannelCreateProductParameter.getBody().setFeatures(features);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withCover(String cover) {
            polyvChannelCreateProductParameter.getBody().setCover(cover);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withRealPrice(Float realPrice) {
            polyvChannelCreateProductParameter.getBody().setRealPrice(realPrice);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withPrice(Float price) {
            polyvChannelCreateProductParameter.getBody().setPrice(price);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withBtnShow(String btnShow) {
            polyvChannelCreateProductParameter.getBody().setBtnShow(btnShow);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withYield(String yield) {
            polyvChannelCreateProductParameter.getBody().setYield(yield);
            return this;
        }

        public PolyvChannelCreateProductParameterBuilder withProductType(String productType) {
            polyvChannelCreateProductParameter.getBody().setProductType(productType);
            return this;
        }

        @Override
        public PolyvChannelCreateProductParameter build() {
            return polyvChannelCreateProductParameter;
        }
    }
}
