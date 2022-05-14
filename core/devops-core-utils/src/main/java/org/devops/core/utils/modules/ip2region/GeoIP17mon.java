package org.devops.core.utils.modules.ip2region;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://github.com/17mon/java
 * https://www.ipip.net/ip.html
 */
public class GeoIP17mon implements GeoIPResolver {
    private static final Logger LOG = LoggerFactory.getLogger(GeoIP17mon.class);

    private static boolean enableFileWatch = true; // 是否自动检查IP数据库的更新

    private static int offset;
    private static int[] index = new int[256];
    private static ByteBuffer dataBuffer;
    private static ByteBuffer indexBuffer;
    private static Long lastModifyTime = 0L;
    private static File ipFile;
    private static ReentrantLock lock = new ReentrantLock();

    // 单例
    private static GeoIP17mon instance;

    /**
     * Private constructor.
     */
    private GeoIP17mon() {
    }

    // 单例，加载IP数据库文件
    public synchronized static GeoIP17mon getInstance(String ipdb) {
        if (null == instance) {
            try {
                ipFile = new File(ipdb);
                LOG.info("using IP library file : {}", ipFile.getAbsolutePath());
                load(); // 加载IP数据库文件
                if (enableFileWatch) {
                    watch();
                }
            } catch (Exception e) {
                LOG.error("load IP library error. Cause:", e);
                throw new RuntimeException("load IP library error", e);
            }
            instance = new GeoIP17mon();
        }
        return instance;
    }

    /**
     * 根据IP地址查询地理位置信息，位置信息以UTF8编码。
     *
     * @param ip IP地址
     * @return IP所属的地理位置信息，位置信息以UTF8编码
     */
    @Override
    public GeoIPResult searchUTF8(String ip) {
        String[] result = null;
        byte[] areaBytes = instance.find(ip);
        if (null != areaBytes) {
            result = new String(areaBytes, StandardCharsets.UTF_8).split("\t");
        }
        return new GeoIPResult(result);
    }

    /**
     * 根据IP地址查询地理位置信息，位置信息以GBK编码。
     *
     * @param ip IP地址
     * @return IP所属的地理位置信息，位置信息以GBK编码
     */
    public GeoIPResult searchGBK(String ip) {
        String[] result = null;
        byte[] areaBytes = instance.find(ip);
        if (null != areaBytes) {
            try {
                result = new String(areaBytes, "GBK").split("\t");
            } catch (UnsupportedEncodingException e) {
                LOG.error("Cannot encode geo result using GBK charsets.", e);
                throw new RuntimeException("Cannot encode geo result using GBK charsets.", e);
            }
        }
        return new GeoIPResult(result);
    }

    public byte[] find(String ip) {
        int ip_prefix_value = new Integer(ip.substring(0, ip.indexOf(".")));
        long ip2long_value = ip2long(ip);
        int start = index[ip_prefix_value];
        int max_comp_len = offset - 1028;
        long index_offset = -1;
        int index_length = -1;
        byte b = 0;
        for (start = start * 8 + 1024; start < max_comp_len; start += 8) {
            if (int2long(indexBuffer.getInt(start)) >= ip2long_value) {
                index_offset = bytesToLong(b, indexBuffer.get(start + 6), indexBuffer.get(start + 5),
                        indexBuffer.get(start + 4));
                index_length = 0xFF & indexBuffer.get(start + 7);
                break;
            }
        }

        byte[] areaBytes;
        lock.lock();
        try {
            dataBuffer.position(offset + (int) index_offset - 1024);
            areaBytes = new byte[index_length];
            dataBuffer.get(areaBytes, 0, index_length);
        } finally {
            lock.unlock();
        }

        // return new String(areaBytes).split("\t");
        return areaBytes;
    }

    private static void watch() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                long time = ipFile.lastModified();
                if (time > lastModifyTime) {
                    lastModifyTime = time;
                    load();
                }
            }
        }, 1000L, 5000L, TimeUnit.MILLISECONDS);
    }

    private static void load() {
        lastModifyTime = ipFile.lastModified();
        FileInputStream fin = null;
        lock.lock();
        try {
            dataBuffer = ByteBuffer.allocate(Long.valueOf(ipFile.length()).intValue());
            fin = new FileInputStream(ipFile);
            int readBytesLength;
            byte[] chunk = new byte[4096];
            while (fin.available() > 0) {
                readBytesLength = fin.read(chunk);
                dataBuffer.put(chunk, 0, readBytesLength);
            }
            dataBuffer.position(0);
            int indexLength = dataBuffer.getInt();
            byte[] indexBytes = new byte[indexLength];
            dataBuffer.get(indexBytes, 0, indexLength - 4);
            indexBuffer = ByteBuffer.wrap(indexBytes);
            indexBuffer.order(ByteOrder.LITTLE_ENDIAN);
            offset = indexLength;

            int loop = 0;
            while (loop++ < 256) {
                index[loop - 1] = indexBuffer.getInt();
            }
            indexBuffer.order(ByteOrder.BIG_ENDIAN);
        } catch (IOException ioe) {
            LOG.error("An error occurs when loading IP library. Cause: ", ioe);
            throw new RuntimeException("An error occurs when loading IP library. Cause:", ioe);
        } finally {
            lock.unlock();
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                LOG.error("Close IP library error. Cause: ", e);
            }
        }
    }

    private long bytesToLong(byte a, byte b, byte c, byte d) {
        return int2long((((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff)));
    }

    private int str2Ip(String ip) {
        String[] ss = ip.split("\\.");
        int a, b, c, d;
        a = Integer.parseInt(ss[0]);
        b = Integer.parseInt(ss[1]);
        c = Integer.parseInt(ss[2]);
        d = Integer.parseInt(ss[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    private long ip2long(String ip) {
        return int2long(str2Ip(ip));
    }

    private long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }
}
