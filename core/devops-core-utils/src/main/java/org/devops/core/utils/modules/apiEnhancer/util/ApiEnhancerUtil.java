package org.devops.core.utils.modules.apiEnhancer.util;

import org.devops.core.utils.util.MD5;
import org.junit.Ignore;
import org.springframework.core.annotation.AnnotationUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiEnhancerUtil {

	private ApiEnhancerUtil(){
		throw new AssertionError();
	}
	
	public static String encryptHashCode(Object obj) {
		return encryptHashCode(obj,"",new String[]{});
	}
	
	public static String encryptHashCode(Object obj,String[] exclude) {
		return encryptHashCode(obj,"",exclude);
	}
	
	//根据参数对象返回一个加密后的字符串
	@SuppressWarnings("rawtypes")
	public static String encryptHashCode(Object obj,String fieldKey,String[] exclude) {
		if(obj == null){
			return null;
		}
		
		if(exclude == null) {
			exclude = new String[] {};
		}
		
//		List<String> liExclude = Arrays.asList(exclude);
		
		List<String> liExclude = new ArrayList<>();
		for(String s : exclude) {
			if(s.contains(",")) {
				liExclude.addAll(Arrays.asList(s.split(",")));
			} else {
				liExclude.add(s);
			}
		}
		
		//属性名->属性值
		Map<String, String> paramMap = new HashMap<String, String>();
		
		if(obj instanceof List){
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) obj;
			for(int j = 0; j < list.size(); j++){
				paramMap.put(fieldKey+"_"+j, encryptHashCode(list.get(j),fieldKey+"_"+j,exclude));
			}
			return appendAndEncrypt(paramMap);
		}else if(obj instanceof Date){
			return String.valueOf(((Date) obj).getTime());
		}else if(obj instanceof Map){
			Map mapObj = (Map)obj;
			//存储bean对象所有属性名称
			ArrayList<String> propNameList = new ArrayList<String>();
			for(Object key : mapObj.keySet()) {
				// 过滤掉那些不需要的参数
				if(key.toString().equalsIgnoreCase("timestamp") 
						|| key.toString().equalsIgnoreCase("requestId")
						|| key.toString().equalsIgnoreCase("sign") || liExclude.contains(key.toString())) {
					continue;
				}
				propNameList.add(key.toString());
			}
			//对属性名称进行字典排序
			sortList(propNameList);
			//通过属性名称获取属性值
			for(String fieldName : propNameList){
				Object propVal;
				//执行get方法获得属性值
				propVal = mapObj.get(fieldName);
				if(propVal == null) {
					continue;
				}
				paramMap.put(fieldName, encryptHashCode(propVal,exclude));
			}
			return appendAndEncrypt(paramMap);
			
		}else if((!(obj instanceof Enum)) && obj instanceof Serializable && filterObj(obj)){
			//存储bean对象所有属性名称
			ArrayList<String> propNameList = new ArrayList<String>();
			//反射获取对象所有属性信息
			Class<? extends Object> clazz = obj.getClass();
			Field[] fields = getAllFields(clazz);
			for(int i = 0; i < fields.length; i++){
				// 过滤掉那些不需要的参数
				if(fields[i].getName().equalsIgnoreCase("timestamp") 
						|| fields[i].getName().equalsIgnoreCase("requestId")
						|| fields[i].getName().equalsIgnoreCase("sign") || liExclude.contains(fields[i].getName())) {
					continue;
				}
				propNameList.add(fields[i].getName());
			}

			//对属性名称进行字典排序
			sortList(propNameList);
			//通过属性名称获取属性值
			for(String fieldName : propNameList){
				PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(fieldName, clazz);
				} catch (IntrospectionException e) {
					continue;
				}
				
				Method getMethod = pd.getReadMethod();	//获得get方法
				if(getMethod == null){
					continue;
				}
				Object propVal;
				try {
					//执行get方法获得属性值
					propVal = getMethod.invoke(obj);
					if(propVal == null) {
						continue;
					}
					paramMap.put(fieldName, encryptHashCode(propVal,exclude));
				} catch (Exception e) {
				} 
			}
			
			return appendAndEncrypt(paramMap);
			
		}
		try {
			return obj.toString();
		}catch (Exception e) {
		}
		return String.valueOf(obj);
		
	}
	//过滤掉包装类和String类
	private static boolean filterObj(Object obj) {
		if(obj instanceof String || obj instanceof Byte || obj instanceof Boolean || obj instanceof Short 
				||obj instanceof Character || obj instanceof Integer || obj instanceof Long || obj instanceof Float
				||obj instanceof Double){
			return false;
		}
		return true;
	}

	//获取所有属性值，包括所继承的属性
	private static Field[] getAllFields(Class<? extends Object> clazz) {
		
		List<Field> fieldList = new ArrayList<Field>();
		
		String clazzName = clazz.getName();
		
		while(clazz != null && !"java.lang.Object".equals(clazzName)){
			Field[] fields = clazz.getDeclaredFields();  
			for(int i=0; i < fields.length; i++){
				Ignore ignore = AnnotationUtils.findAnnotation(fields[i],Ignore.class);
				if(ignore == null){
					fieldList.add(fields[i]);
				}
			}
			clazz = clazz.getSuperclass();
		}
		
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

	//拼接字符串并加密
	private static String appendAndEncrypt(Map<String, String> paramMap) {
		
		StringBuilder sb = new StringBuilder();
		String paramString = "";
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key+"="+value).append("&");
		}
		paramString = sb.toString();
		if(!paramString.equals("")){
			paramString = paramString.substring(0, paramString.length()-1);
		}else{
			paramString = "null";
		}
		return MD5.getMD5(paramString);
	}

	//字典排序
	private static void sortList(ArrayList<String> list) {
		
		if(list != null && !list.isEmpty()){
			Collections.sort(list, new Comparator<String>(){
				@Override
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});
		}
	}

}
