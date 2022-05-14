package org.devops.core.utils.util;

import java.util.Random;


public class RandomUtil {

    private static final int four_gum_length = 4;

    private static final int six_gum_length = 6;

    /**
     *
     */
    private static final char[] ENCODING_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X',
            'Y', 'Z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
            'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x',
            'y', 'z'
    };

    /**
     * 生成四位随机数
     * @return
     */
    public static String getFourRandNumber() {
        Random random = new Random(System.nanoTime());
        return StringUtil.fixed(Math.abs(random.nextInt()) % 9999, four_gum_length);
    }

    /**
     * 生成六位随机数
     * @return
     */
    public static String getSixRandNumber() {
        Random random = new Random(System.nanoTime());
        return StringUtil.fixed(Math.abs(random.nextInt()) % 999999, six_gum_length);
    }

    /**
     * 生成六位随机字符
     * @return
     */
    public static String getSixRandChar(){
        Random random = new Random(System.nanoTime());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < six_gum_length; i++) {
            int k = Math.abs(random.nextInt()) % ENCODING_CHARS.length;
            sb.append(ENCODING_CHARS[k]);
        }
        return sb.toString();
    }

    /**
     * 生成四位随机字符
     * @return
     */
    public static String getFourRandChar(){
        Random random = new Random(System.nanoTime());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < four_gum_length; i++) {
            int k = Math.abs(random.nextInt()) % ENCODING_CHARS.length;
            sb.append(ENCODING_CHARS[k]);
        }
        return sb.toString();
    }

    /**
     * 生成指定长度字符串
     * @param len
     * @return
     */
    public static String getRandChars(long len){
        if (len <= 0) {
            return "";
        }
        Random random = new Random(System.nanoTime());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int k = Math.abs(random.nextInt()) % ENCODING_CHARS.length;
            sb.append(ENCODING_CHARS[k]);
        }
        return sb.toString();
    }
}
