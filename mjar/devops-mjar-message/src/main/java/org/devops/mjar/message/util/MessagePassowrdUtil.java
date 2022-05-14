package org.devops.mjar.message.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.devops.core.utils.util.Base64Util;
import org.devops.core.utils.util.StringUtil;

public class MessagePassowrdUtil {

	private static final String DES = "DES";
	
	/**
    * 获取秘钥对象
    * @return
    * @throws Exception
    */
   private static final SecretKey getSecretKeyFactory(String token) throws Exception {
	   if(StringUtil.isEmpty(token)){
		   token = "MESSAGE";
	   }
	   token = StringUtil.addBlankWord(token, 8);
       SecretKeyFactory des = SecretKeyFactory.getInstance(DES);
       SecretKey secretKey = des.generateSecret(new DESKeySpec(token.getBytes()));
       return secretKey;
   }
	
	public static String decode(String token,String content){
		try {
			Cipher cipher = Cipher.getInstance(DES);
	        SecretKey secretKey = getSecretKeyFactory(token);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        return new String(cipher.doFinal(Base64Util.decode(content)));
		}catch (Exception e) {
		}
        return content;
		
	}
	
	public static String encode(String token,String content){
		try {
			Cipher cipher = Cipher.getInstance(DES);
			SecretKey secretKey = getSecretKeyFactory(token);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        return  Base64Util.encode(cipher.doFinal(content.toString().getBytes()));
		} catch (Exception e) {
		}
        return content;
	}
}
