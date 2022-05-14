package org.devops.iweb.auth.helper;

import org.devops.core.utils.util.RSAEncryptUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IWebAuthRsaHelper {
	
	@Value("${iweb.auth.password.publicKey:}")
	private String publicKey;
	
	@Value("${iweb.auth.password.privateKey:}")
	private String privateKey;

	/**
	 * 公钥对passwd加密
	 * @param passwd
	 * @return
	 */
	public String encrypt(String passwd) {
		try {
			return RSAEncryptUtils.encrypt(passwd, publicKey);
		} catch (Exception e) {
			return passwd;
		}
	}
	
	/**
	 * 私钥对passwd解密
	 * @param passwd
	 * @return
	 */
	public String decrypt(String passwd) {
		try {
			return RSAEncryptUtils.decrypt(passwd, privateKey);
		} catch (Exception e) {
			return passwd;
		}
	}
}
