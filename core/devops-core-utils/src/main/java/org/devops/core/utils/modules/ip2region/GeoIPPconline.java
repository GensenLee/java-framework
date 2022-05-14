package org.devops.core.utils.modules.ip2region;

import lombok.Data;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.util.StreamUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.HttpResult;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 *太平洋
 */
public class GeoIPPconline implements GeoIPResolver{

    private static final String QUERY_URL = "http://whois.pconline.com.cn/ipJson.jsp?json=true&ip=";

    @Override
    public GeoIPResult searchUTF8(String ip) {
        String url = QUERY_URL + ip;

        HttpResult httpResult = HttpUtils.doHttpGet(url);
        String result = null;
        try {
            result = StreamUtil.copyToString(httpResult.getResponse(), Charset.forName("GBK"));
        } catch (IOException ignored) {
        }

        if (StringUtil.isEmpty(result)) {
            return null;
        }
        ResponseDTO bean = FastJsonUtils.getBean(result, ResponseDTO.class);
        if (bean == null) {
            return null;
        }
        GeoIPResult geoIPResult = new GeoIPResult();
        geoIPResult.setCity(bean.city);
        geoIPResult.setProvince(bean.pro);
        return geoIPResult;
    }


    @Data
    public static class ResponseDTO implements Serializable {
        private String ip;
        private String pro;
        private String proCode;
        private String city;
        private String cityCode;
        private String region;
        private String regionCode;
        private String addr;
        private String regionNames;
        private String err;
    }

}
