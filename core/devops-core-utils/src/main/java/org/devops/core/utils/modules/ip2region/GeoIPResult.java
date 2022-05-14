package org.devops.core.utils.modules.ip2region;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.BaseBean;

/**
 * IP地理位置结果。
 */
@NoArgsConstructor
@Data
public class GeoIPResult extends BaseBean {

    private static final String COUNTRY_CHINA = "中国";

    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 是否属于中国
     */
    private boolean isChina;


    /**
     * @param results 查询IP库的结果数组
     */
    public GeoIPResult(String[] results) {
        if (null == results || 0 == results.length) {
            country = "未知";
            province = "未知";
            city = "未知";
            isChina = false;
        } else {
            country = results[0];
            if (COUNTRY_CHINA.equals(country)) {
                isChina = true;
            }
            if (results.length > 1) {
                province = results[1];
            }
            if (results.length > 2) {
                city = results[2];
            }
        }
    }
    public String toLocalString(){
        if (StringUtil.isEmpty(this.province)) {
            return this.country;
        }
        return this.province + CommonConstant.CROSS_MARK + this.city;
    }
}
