package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.modules.ip2region.GeoIP2Region;
import org.devops.core.utils.modules.ip2region.GeoIPResolver;
import org.devops.core.utils.modules.ip2region.GeoIPResult;
import org.devops.core.utils.modules.ip2region.IpGeoLib;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


@Slf4j
public class IPUtil {

    public static String localIP = "";

    public synchronized static String getLocalIP() {
        if (localIP == null || "".equals(localIP.trim())) {
            InetAddress localHostExactAddress = getLocalHostExactAddress();
            if (localHostExactAddress != null) {
                localIP = localHostExactAddress.getHostAddress();
                System.out.println("LocalIP: " + localIP);
            }
        }
        return localIP;
    }

    private static InetAddress getLocalHostExactAddress() {
        try {
            InetAddress candidateAddress = null;
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface iface = networkInterfaces.nextElement();
                if (!iface.getName().contains("docker") && !iface.getName().contains("lo")) {
                    // 该网卡接口下的ip会有多个，也需要一个个的遍历，找到自己所需要的
                    for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                        InetAddress inetAddr = inetAddrs.nextElement();
                        // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                        if (!inetAddr.isLoopbackAddress()) {
                            if (inetAddr.isSiteLocalAddress()) {
                                // 如果是site-local地址，就是它了 就是我们要找的
                                // ~~~~~~~~~~~~~绝大部分情况下都会在此处返回你的ip地址值~~~~~~~~~~~~~
                                return inetAddr;
                            }
                            // 若不是site-local地址 那就记录下该地址当作候选
                            if (candidateAddress == null) {
                                candidateAddress = inetAddr;
                            }
                        }
                    }
                }
            }
            // 如果出去loopback回环地之外无其它地址了，那就回退到原始方案吧
            return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
        } catch (Exception e) {
            log.error("getLocalHostExactAddress Exception", e);
        }
        return null;
    }

    /**
     * ip地址转成long型数字
     * 将IP地址转化成整数的方法如下：
     * 1、通过String的split方法按.分隔得到4个长度的数组
     * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
     *
     * @param strIp
     * @return
     */
    public static long ipv4ToLong(String strIp) {
        String[] ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    /**
     * 根据long值获取ip v4地址：xx.xx.xx.xx
     *
     * @param longIP IP的long表示形式
     * @return IP V4 地址
     */
    public static String longToIpv4(long longIP) {
        final StringBuilder sb = new StringBuilder();
        // 直接右移24位
        sb.append(longIP >> 24 & 0xFF);
        sb.append(CommonConstant.CHAR_POINT);
        // 将高8位置0，然后右移16位
        sb.append(longIP >> 16 & 0xFF);
        sb.append(CommonConstant.CHAR_POINT);
        sb.append(longIP >> 8 & 0xFF);
        sb.append(CommonConstant.CHAR_POINT);
        sb.append(longIP & 0xFF);
        return sb.toString();
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @param lib
     */
    public synchronized static void switchIpGeoLib(IpGeoLib lib) {
        IP_RESOLVER = lib.getInstance();
    }

    private static GeoIPResolver IP_RESOLVER;

    static {
        IP_RESOLVER = GeoIP2Region.getInstance("META-INF/ip/ip2region.db");
    }

    /**
     * 使用ip2region库查询ip所在地址
     * 无需请求网络，支持 10w+/1s 查询量
     *
     * @return
     */
    public static GeoIPResult getIpRegion(String ip) {
        return IP_RESOLVER.searchUTF8(ip);
    }
}
