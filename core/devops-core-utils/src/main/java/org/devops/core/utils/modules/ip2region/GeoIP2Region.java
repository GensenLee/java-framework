package org.devops.core.utils.modules.ip2region;

import org.apache.commons.lang.StringUtils;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.util.StreamUtil;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/lionsoul2014/ip2region
 * <p>
 * 库更新地址 https://github.com/lionsoul2014/ip2region/blob/master/data/ip2region.db
 */
public class GeoIP2Region implements GeoIPResolver {

    private static Logger LOGGER = LoggerFactory.getLogger(GeoIP2Region.class);

    private static final Object LOCK = new Object();

    // 单例
    private static GeoIP2Region instance;

    private DbSearcher dbSearcher;

    private GeoIP2Region(DbSearcher dbSearcher) {
        this.dbSearcher = dbSearcher;
    }

    public static GeoIP2Region getInstance(String ipDBFile) {
        if (instance != null) {
            return instance;
        }
        synchronized (LOCK) {
            if (instance != null) {
                return instance;
            }
            LOGGER.info("using ip2region IP library file : {}", ipDBFile);
            InputStream inputStream = null;
            try {
                inputStream = ResourceUtil.readResourceAsStream(ipDBFile);
            } catch (Exception ignored) {
            }
            try {
                if (inputStream == null) {
                    inputStream = new FileInputStream(ipDBFile);
                }
                byte[] dbFileBytes = StreamUtil.copyToByteArray(inputStream);
                instance = new GeoIP2Region(new DbSearcher(new DbConfig(), dbFileBytes));
            } catch (Exception e) {
                throw new IllegalStateException("load ip2region error", e);
            }
        }
        return instance;
    }

    @Override
    public GeoIPResult searchUTF8(String ip) {
        String region = null;
        try {
            DataBlock dataBlock = dbSearcher.memorySearch(ip);
            // 中国|0|广东省|广州市|电信
            region = dataBlock.getRegion();
        } catch (IOException e) {
            throw new IllegalStateException(String.format("ip2region search ip[%s] error", ip), e);
        }

        return convertRegionResult(region);
    }

    private GeoIPResult convertRegionResult(String region) {
        String[] regionArr = StringUtils.split(region, "|");
        List<String> regionList = new ArrayList<>();
        if (isNotZero(regionArr, 0)) { // 国家，如中国
            regionList.add(regionArr[0]);
        }
        if (isNotZero(regionArr, 2)) { // 省份，如广东省
            regionList.add(StringUtils.replace(regionArr[2], "省", ""));
        }
        if (isNotZero(regionArr, 3)) { // 市，如广州市
            regionList.add(StringUtils.replace(regionArr[3], "市", ""));
        }
        return new GeoIPResult(regionList.toArray(new String[0]));
    }

    private boolean isNotZero(String[] regionArr, int index) {
        return regionArr.length > index && !StringUtils.equals("0", regionArr[index]);
    }

    public static void main(String[] args) {
        GeoIP2Region instance = GeoIP2Region.getInstance("D:\\data\\ip2region.db");
        GeoIPResult geoResult = instance.searchUTF8("117.136.32.51");
        System.out.println(geoResult);
    }
}
