package org.devops.core.utils.modules.ip2region;

import lombok.Data;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.util.ListUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 百度在线ip库
 */
public class GeoIPBaidu implements GeoIPResolver {

    private final String QUERY_URL = "https://opendata.baidu.com/api.php?co=&resource_id=6006&oe=utf8&query=";

    @Override
    public GeoIPResult searchUTF8(String ip) {
        String url = QUERY_URL + ip;

        String result = HttpUtils.doGet(url, Collections.emptyMap());
        ResponseDTO bean = FastJsonUtils.getBean(result, ResponseDTO.class);
        if (bean == null) {
            return null;
        }

        List<DataDTO> list = bean.getData();
        if (ListUtil.isNull(list)) {
            return null;
        }

        DataDTO dataDTO = list.get(0);
        GeoIPResult geoIPResult = new GeoIPResult();

        // TODO: 2022/5/13 切割省市
        geoIPResult.setCity(dataDTO.getLocation());

        return geoIPResult;
    }

    @Data
    public static class ResponseDTO implements Serializable {
        private String status;
        private String setCacheTime;
        private List<DataDTO> data;
    }

    @Data
    public static class DataDTO implements Serializable {
        private String extendedLocation;
        private String originQuery;
        private String appinfo;
        private int dispType;
        private String fetchkey;
        private String location;
        private String origip;
        private String origipquery;
        private String resourceid;
        private int roleId;
        private int shareImage;
        private int showLikeShare;
        private String showlamp;
        private String titlecont;
        private String tplt;

    }
}
