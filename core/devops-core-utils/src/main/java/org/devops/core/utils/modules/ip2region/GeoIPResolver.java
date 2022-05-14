package org.devops.core.utils.modules.ip2region;

/**
 * ip地址解析
 */
public interface GeoIPResolver {

    /**
     * 解析ip地址,返回地址信息
     *
     * @param ip ip地址
     * @return 解析结果
     */
    GeoIPResult searchUTF8(String ip);

}
