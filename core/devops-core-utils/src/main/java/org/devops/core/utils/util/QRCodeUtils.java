package org.devops.core.utils.util;

import com.google.common.base.Preconditions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：essence
 * 类 名 称：ZxingUtils
 * 类 描 述：通过谷歌zxing 生成二维码
 * 创建时间：2020/8/5 14:05
 * 创 建 人：dengfr
 */
public class QRCodeUtils {
    private static final int width = 300;// 默认二维码宽度
    private static final int height = 300;// 默认二维码高度
    private static final String format = "png";// 默认二维码文件格式
    private static final Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();// 二维码参数

    static {
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());// 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN, 1);// 二维码与图片边距
    }

    /**
     * 返回一个 BufferedImage 对象
     *
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     */
    public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 将二维码图片输出到一个流中
     *
     * @param content 二维码内容
     * @param stream  输出流
     * @param width   宽
     * @param height  高
     */
    public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
    }


    /**
     * 将二维码图片输出到一个流中
     *
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     * @throws WriterException
     * @throws IOException
     */
    public static ByteArrayOutputStream writeToStream(String content, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        return outputStream;
    }

    /**
     * 生成二维码图片文件
     *
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     */
    public static void createQRCode(String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        //toPath() 方法由 jdk1.7 及以上提供
        MatrixToImageWriter.writeToPath(bitMatrix, format, new File(path).toPath());
    }

    public static void generateQRCode(String url, HttpServletResponse response) throws Exception {
        Preconditions.checkArgument(StringUtil.isEmpty(url), "连接地址为空");
        generateQRCode(url, response, height, width);
    }

    public static void generateQRCode(String url, HttpServletResponse response, int height, int width) throws Exception {
        Preconditions.checkArgument(StringUtil.isEmpty(url), "连接地址为空");
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, height, width, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    /**
     * 字符串总宽度
     *
     * @param g
     * @param str
     * @return
     */
    public static int getStringLength(Graphics g, String str) {
        char[] strcha = str.toCharArray();
        int strWidth = g.getFontMetrics().charsWidth(strcha, 0, str.length());
        return strWidth;
    }

    /**
     * 每一行字符的个数
     *
     * @param strnum
     * @param rowWidth
     * @param strWidth
     * @return
     */
    public static int getRowStrNum(int strnum, int rowWidth, int strWidth) {
        int rowstrnum = 0;
        rowstrnum = (rowWidth * strnum) / strWidth;
        return rowstrnum;
    }

    /**
     * 字符行数
     *
     * @param strWidth
     * @param rowWidth
     * @return
     */
    public static int getRows(int strWidth, int rowWidth) {
        int rows = 0;
        if (strWidth % rowWidth > 0) {
            rows = strWidth / rowWidth + 1;
        } else {
            rows = strWidth / rowWidth;
        }
        return rows;
    }

    /**
     * 字符高度
     *
     * @param g
     * @return
     */
    public static int getStringHeight(Graphics g) {
        int height = g.getFontMetrics().getHeight();
        return height;
    }

    /**
     * 开始绘制文字
     *
     * @param g
     * @param strContent 内容
     * @param locX       x轴位置
     * @param locY       y轴位置
     * @param font
     * @param rowWidth   每一行字符串宽度
     */
    public static void drawStringWithFontStyleLineFeed(Graphics g, String strContent, int locX, int locY, Font font, int rowWidth, Color color) {
        g.setFont(font);
        g.setColor(color);
        //获取字符串 字符的总宽度
        int strWidth = getStringLength(g, strContent);
        //每一行字符串宽度
//        int rowWidth=100;
        //获取字符高度
        int strHeight = getStringHeight(g);
        //字符串总个数
        if (strWidth > rowWidth) {
            int rowstrnum = getRowStrNum(strContent.length(), rowWidth, strWidth);
            int rows = getRows(strWidth, rowWidth);
            String temp = "";
            for (int i = 0; i < rows; i++) {
                //获取各行的String
                if (i == rows - 1) {
                    //最后一行
                    temp = strContent.substring(i * rowstrnum, strContent.length());
                } else {
                    temp = strContent.substring(i * rowstrnum, i * rowstrnum + rowstrnum);
                }
                if (i > 0) {
                    //第一行不需要增加字符高度，以后的每一行在换行的时候都需要增加字符高度
                    locY = locY + strHeight;
                }
                g.drawString(temp, locX, locY);
            }
        } else {
            //直接绘制
            g.drawString(strContent, locX, locY);
        }

    }


    public static void main(String[] args) throws Exception {
//        OutputStream outputStream=new FileOutputStream(new File("/Users/dengfr/test/qr.png"));
//        QRCodeUtils.writeToStream("www.baidu.com",outputStream,width,height);
//        QRCodeUtils.createQRCode("https://www.baidu.com/", "/Users/dengfr/test/qr.png", width, height);
        ByteArrayOutputStream byteArrayOutputStream = writeToStream("cid=5464f5s4f6s4f5s6f46s4f&k=ads", 500, 500);
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\data\\tmp\\qr.png");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());

    }
}
