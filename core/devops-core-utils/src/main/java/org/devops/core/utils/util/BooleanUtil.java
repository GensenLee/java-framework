package org.devops.core.utils.util;

public class BooleanUtil {

    public static boolean isTrue(String b){
        if ("true".equals(b.trim())){
            return true;
        }
        return false;
    }
    
    public static boolean toBoolean(Boolean b){
        if(b == null) {
        	return false;
        }
        return b.booleanValue();
    }
}
