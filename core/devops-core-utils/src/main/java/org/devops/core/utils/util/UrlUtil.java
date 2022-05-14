package org.devops.core.utils.util;

import java.util.HashMap;
import java.util.Map;

public class UrlUtil {

    /**
     * 给定一个带http的完整的URL，把参数解析出来
     *
     * @param url
     * @return
     */
    public static Map<String, String> uriToMap(String url) {
        Map<String, String> param = new HashMap<String, String>();
        for (String s : url.split("&")) {
            String[] kv = s.split("=");
            if (kv.length == 2) {
                param.put(kv[0], kv[1]);
            }
        }
        return param;
    }

    /**
     * @param param
     * @return
     */
    public static String mapToUri(Map<String, String> param) {
        if (param.isEmpty()) {
            return "";
        }
        StringBuilder uri = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (uri.length() > 0) {
                uri.append("&");
            }
            uri.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return uri.toString();
    }

}
