package org.devops.web.test.test.polyvApi;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.annotation.EnableMjarLivePolyv;
import org.devops.mjar.live.polyv.client.PolyvChannelClient;
import org.devops.mjar.live.polyv.client.PolyvClientBuilder;
import org.devops.mjar.live.polyv.enums.EnableSetting;
import org.devops.mjar.live.polyv.operator.requester.*;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelProduct;
import org.devops.mjar.live.polyv.pojo.model.PolyvChannelProductEnabled;
import org.devops.mjar.live.polyv.pojo.model.PolyvPaginator;
import org.junit.Test;

/**
 * 商品库
 */
@EnableMjarLivePolyv
public class ShopJunitTest extends AbstractJUnit4ServiceAction {

    private PolyvChannelClient channelClient(){
        return PolyvClientBuilder.standardChannelClient()
                .withAppId("fzu9jlakze")
                .withUserId("bf95ea5c05")
                .withAppSecret("91fc3d7946444bb4bb098bc497c52018")
                .withChannelId("2490359")
                .build();
    }

    /**
     * 添加频道商品
     */
    @Test
    public void addProduct() {
        PolyvChannelCreateProductRequester requester = channelClient().createProductRequest();
        requester.parameter()
                .withName("bigboss")
                .withStatus(1)
                .withLinkType(11)
                .withBtnShow("测试")
                .withProductDesc("bigboss第二个商品")
                .withYield("10%")
                .withFeatures("[\"tab1\",\"tab2\"]")
                .withProductType("finance");

        PolyvApiResult<PolyvChannelProduct> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道商品库开关状态
     */
    @Test
    public void checkProductEnable() {
        PolyvChannelCheckProductEnableRequester requester = channelClient().checkProductEnableRequest();
        PolyvApiResult<PolyvChannelProductEnabled> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 查询频道商品列表
     */
    @Test
    public void searchProductList() {
        PolyvChannelSearchProductListRequester requester = channelClient().searchProductListRequest();
        requester.parameter().withPage(1).withSize(10);
        PolyvApiResult<PolyvPaginator<PolyvChannelProduct>> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 推送频道商品库商品
     */
    @Test
    public void pushProduct() {
        PolyvChannelPushProductRequester requester = channelClient().pushProductRequest();
        requester.parameter().withProductId(41958);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道商品信息
     */
    @Test
    public void updateProduct() {
        PolyvChannelUpdateProductRequester requester = channelClient().updateProductRequest();
        requester.parameter()
                .withName("bigboss")
                .withProductId(41958)
                .withStatus(1)
                .withLinkType(11)
                .withBtnShow("11223322")
                .withProductDesc("bigboss第er个商品")
                .withYield("10%")
                .withFeatures("[\"tab1\",\"tab2\"]")
                .withProductType("finance");

        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     *  修改频道商品库开关状态
     */
    @Test
    public void updateProductEnabled() {
        PolyvChannelUpdateProductEnabledRequester requester = channelClient().updateProductEnabledRequest();
        requester.parameter().withEnabled(EnableSetting.N);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道商品库上下架状态
     */
    @Test
    public void updateProductShelf() {
        PolyvChannelUpdateProductShelfRequester requester = channelClient().updateProductShelfRequest();
        requester.parameter().withShelf(1).withProductId(41958);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 修改频道商品库列表顺序
     */
    @Test
    public void updateProductSort() {
        PolyvChannelUpdateProductSortRequester requester = channelClient().updateProductSortRequest();
        requester.parameter().withType(10).withProductId(41958);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

    /**
     * 删除频道商品
     */
    @Test
    public void deleteProduct() {
        PolyvChannelDeleteProductRequester requester = channelClient().deleteProductRequest();
        requester.parameter().withProductId(42419);
        PolyvApiResult<String> apiResult = requester.execute();
        System.out.println(FastJsonUtils.toJsonString(apiResult));
    }

}
