package org.devops.core.utils.util;


import org.devops.core.utils.exception.CommonRuntimeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Base64Util {
   private static final char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

   private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
   
   /** *//** 
    * 文件读取缓冲区大小 
    */  
   private static final int CACHE_SIZE = 1024;  

   private Base64Util() {
   }

   /**
    * 将字节数组编码为字符串
    *
    * @param data
    */
   public static String encode(byte[] data) {
       StringBuffer sb = new StringBuffer();
       int len = data.length;
       int i = 0;
       int b1, b2, b3;

       while (i < len) {
           b1 = data[i++] & 0xff;
           if (i == len) {
               sb.append(base64EncodeChars[b1 >>> 2]);
               sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
               sb.append("==");
               break;
           }
           b2 = data[i++] & 0xff;
           if (i == len) {
               sb.append(base64EncodeChars[b1 >>> 2]);
               sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
               sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
               sb.append("=");
               break;
           }
           b3 = data[i++] & 0xff;
           sb.append(base64EncodeChars[b1 >>> 2]);
           sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
           sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
           sb.append(base64EncodeChars[b3 & 0x3f]);
       }
       return sb.toString();
   }

   /**
    * 将base64字符串解码为字节数组
    *
    * @param str
    */
   public static byte[] decode(String str) {
       byte[] data = str.getBytes();
       int len = data.length;
       ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
       int i = 0;
       int b1, b2, b3, b4;

       while (i < len) {

           /* b1 */
           do {
               b1 = base64DecodeChars[data[i++]];
           } while (i < len && b1 == -1);
           if (b1 == -1) {
               break;
           }

           /* b2 */
           do {
               b2 = base64DecodeChars[data[i++]];
           } while (i < len && b2 == -1);
           if (b2 == -1) {
               break;
           }
           buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

           /* b3 */
           do {
               b3 = data[i++];
               if (b3 == 61) {
                   return buf.toByteArray();
               }
               b3 = base64DecodeChars[b3];
           } while (i < len && b3 == -1);
           if (b3 == -1) {
               break;
           }
           buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

           /* b4 */
           do {
               b4 = data[i++];
               if (b4 == 61) {
                   return buf.toByteArray();
               }
               b4 = base64DecodeChars[b4];
           } while (i < len && b4 == -1);
           if (b4 == -1) {
               break;
           }
           buf.write((int) (((b3 & 0x03) << 6) | b4));
       }
       return buf.toByteArray();
   }
   
   /** *//** 
    * <p> 
    * 文件转换为二进制数组 
    * </p> 
    *  
    * @param filePath 文件路径 
    * @return 
    * @throws Exception 
    */  
   public static byte[] fileToByte(String filePath) throws Exception {  
       byte[] data = new byte[0];  
       File file = new File(filePath);  
       FileInputStream in = null;
       ByteArrayOutputStream out = null;
       if (file.exists()) {  
    	   try{
	           in = new FileInputStream(file);  
	           out = new ByteArrayOutputStream(2048);  
	           byte[] cache = new byte[CACHE_SIZE];  
	           int nRead = 0;  
	           while ((nRead = in.read(cache)) != -1) {  
	               out.write(cache, 0, nRead);  
	               out.flush();  
	           }  
	           data = out.toByteArray();  
    	   }catch(Exception e) {
    		   throw e;
    	   }finally {
    		   if(out != null) {
    			   out.close();  
    		   }
    		   if(in != null) {
    			   in.close();  
    		   }
    	   }
        }  
       return data;  
   }  
     
   /** *//** 
    * <p> 
    * 二进制数据写文件 
    * </p> 
    *  
    * @param bytes 二进制数据 
    * @param filePath 文件生成目录 
    */  
   public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {  
       InputStream in = new ByteArrayInputStream(bytes);     
       File destFile = new File(filePath);  
       if (!destFile.getParentFile().exists()) {  
           destFile.getParentFile().mkdirs();  
       }  
       boolean isSuccess = destFile.createNewFile();
       if(!isSuccess) {
    	   throw new CommonRuntimeException("创建文件失败");
       }
       OutputStream out = null;
       try{
	       out = new FileOutputStream(destFile);  
	       byte[] cache = new byte[CACHE_SIZE];  
	       int nRead = 0;  
	       while ((nRead = in.read(cache)) != -1) {     
	           out.write(cache, 0, nRead);  
	           out.flush();  
	       }  
       }catch(Exception e) {
		   throw e;
	   }finally {
		   if(out != null) {
			   out.close();  
		   }
		   if(in != null) {
			   in.close();  
		   }
	   }
   }  
   
   /**
    * 输入流转byte字节
    * @param inStream
    * @return
    * @throws IOException
    */
   public static byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
}
