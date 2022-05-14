package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author bigboss
 */
@Data
public class PolyvChannelProduct extends BaseBean {


    private Integer productId;

    private String userId;

    private String channelId;

    /**
     * 商品名称
     */
    private String name;

    private Float price;
    /**
     * 商品封面链接
     */
    private String cover;

    /**
     * 通用链接，商品链接
     */
    private String link;
    /**
     * 商品上下架状态
     * 1：上架状态
     * 2：下架状态
     */
    private Integer status;

    private Long createdTime;

    private Long lastModified;

    private Integer rank;

    /**
     * 普通商品参数，商品实际价格，商品类型为普通商品时必传，保留两位小数，如：0.01
     */
    private Float realPrice;

    private String type;

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
     *商品类型，默认为普通商品
     * normal：普通商品
     * finance：金融商品
     */
    @VerifyField(ignore = true)
    private String productType;

    /**
     * 金融商品参数，按钮显示文案
     */
    @VerifyField(ignore = true)
    private String btnShow;


    /**
     * 产品特色，卖点标签，可以多个，json字段，如：["tab1", "tab2"]
     */
    @VerifyField(ignore = true)
    private String features;

    /**
     * 金融商品参数，产品收益率/价格
     */
    @VerifyField(ignore = true)
    private String yield;

    /**
     * 产品描述
     */
    @VerifyField(ignore = true)
    private String productDesc;


}
