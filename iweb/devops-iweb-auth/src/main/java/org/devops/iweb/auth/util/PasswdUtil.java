package org.devops.iweb.auth.util;

import org.devops.core.utils.util.MD5;
import org.devops.core.utils.util.StringUtil;

public class PasswdUtil {

	public static final String PASSWD_TEMPLATE = "%s_w67uz!@#swkd5_%s";

	public static String getPasswd(String passwd,String passwdSuffix){
		if(StringUtil.isEmpty(passwdSuffix)){
			passwdSuffix = "";
		}
		return MD5.getMD5(String.format(PASSWD_TEMPLATE, passwd,passwdSuffix));
	}
}
