package org.devops.core.utils.modules.ip2region;

/**
 * ip库类型
 */
public enum IpGeoLib {

    IP2REGION() {
        @Override
        public GeoIPResolver getInstance() {
            return GeoIP2Region.getInstance("META-INF/ip/ip2region.db");
        }
    },
    PCONLINE() {
        @Override
        public GeoIPResolver getInstance() {
            return new GeoIPPconline();
        }
    };
//    BAIDU() {
//        @Override
//        public GeoIPResolver getInstance() {
//            return new GeoIPBaidu();
//        }
//    };

    public abstract GeoIPResolver getInstance();

}
