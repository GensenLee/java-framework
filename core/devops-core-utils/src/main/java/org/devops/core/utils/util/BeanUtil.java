package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description 复制属性专用
 * @author xhz
 * @date 2017年4月24日 下午2:24:22
 */
@Slf4j
public class BeanUtil {

	/**
	 * 深克隆
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
		// 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(object);
		// 将流序列化成对象
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}


	/**
	 * @Description 单个复制,浅复制，不复制父级元素
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:38
	 * @param source
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static <T> T copyNODeep(Object source,Class<T> clazz)
	{
		if(source == null){
			return null;
		}
		try {
			T target = clazz.newInstance();
			copyNODeep(source,target);
			return target;
		} catch (Exception e) {
			log.error("copyNODeep", e);
		} 
		return null;
		
	}
	
	/**
	 * @author xhz
	 * @param source
	 * @param target
	 * @param <T>
	 */
	public static <T> void copyNODeep(Object source,Object target) {
		if(source == null || target == null) return; 
		
		Class<?> clazz = target.getClass();
		Class<?> sourceClazz = source.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Field srouceField = getField(sourceClazz,field.getName());
			if(srouceField == null){
				continue;
			}
			field.setAccessible(true);
			srouceField.setAccessible(true);
			try {
				field.set(target, srouceField.get(source));
			} catch (Exception e) {
			} 
		}
	}
	/**
	 * @Description 单个复制
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:38
	 * @param source
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static <T> T copy(Object source,Class<T> clazz)
	{
		if(source == null){
			return null;
		}
		try {
			T target = clazz.newInstance();
			copy(source,target);
			return target;
		} catch (Exception e) {
			log.error("copy", e);
		} 
		return null;
		
	}
	
	/**
	 * @Description 获取所有的成员变量，包括父类
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:49
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	public static List<Field> getAllField(Class<?> clazz){
		
		List<Field> liField = new ArrayList<Field>();
		while(true){
			Field[] fields = clazz.getDeclaredFields();
			for(Field field :fields){
				liField.add(field);
			}
			clazz = clazz.getSuperclass();
			if(clazz == null){
				break;
			}
		}
		return liField;
	}
	
	/**
	 * @Description 获取属性，包括父类
	 * @author xhz
	 * @date 2017年4月24日 下午2:25:36
	 * @param clazz
	 * @param name
	 * @return
	 * @lastModifier
	 */
	public static Field getField(Class<?> clazz,String name){
		Field field = null;
		while(true){
			try {
				try{
					field = clazz.getDeclaredField(name);
				}catch(Exception ee) {
					field = clazz.getDeclaredField(StringUtil.toLHCase(name));
				}
				break;
			} catch (Exception ignored) {
			}
			clazz = clazz.getSuperclass();
			if(clazz == null){
				break;
			}
		}
		return field;
	}
	
	/**
	 * @Description 复制所有属性
	 * @author xhz
	 * @date 2017年4月24日 下午2:24:06
	 * @param source
	 * @param target
	 * @lastModifier
	 */
	public static void copy(Object source,Object target){
		if(source == null || target == null) return; 
		Class<?> clazz = target.getClass();
		Class<?> sourceClazz = source.getClass();
		List<Field> liFiled = getAllField(clazz);
		for(Field field : liFiled){
			Field srouceField = getField(sourceClazz,field.getName());
			if(srouceField == null){
				continue;
			}
			field.setAccessible(true);
			srouceField.setAccessible(true);
			try {
				field.set(target, srouceField.get(source));
			} catch (Exception e) {
			} 
		}
	}
	
	/**
	 * @Description 批量复制属性
	 * @author xhz
	 * @date 2017年4月24日 下午2:26:06
	 * @param source
	 * @param clazz
	 * @return
	 * @lastModifier
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> copy(List source,Class<T> clazz){
		List<T> result = new ArrayList<T>();
		if(source == null || source.isEmpty()){
			return result;
		}
		for(Object o : source){
			T e = copy(o,clazz);
			result.add(e);
		}
		return result;
	}
	
	public static void copyFromExits(Object source, Object target) {
		if(source == null || target == null) return;

		Class<?> clazz = target.getClass();
		Class<?> sourceClazz = source.getClass();

		List<Field> liFiled = getAllField(clazz);
		for(Field field : liFiled){
			Field srouceField = getField(sourceClazz,field.getName());
			if(srouceField == null){
				continue;
			}
			field.setAccessible(true);
			srouceField.setAccessible(true);
			try {
				if(null != srouceField.get(source)) {
					field.set(target, srouceField.get(source));
				}
			} catch (Exception e) {
			}
		}
	}
	
	public static void initCreate(Object obj,long user_id){
		initModify(obj,user_id);
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("creator",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, user_id);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
	
	
	
	public static void initModify(Object obj,long user_id){
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifier",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, user_id);
			}
		} catch (Exception e) {}
		
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}
	}
	
	public static void initModify(Object obj,String name){
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyName",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, name);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("modifyTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
	
	public static void initCreate(Object obj,String name){
		initModify(obj,name);
		Class<?> clazz = obj.getClass();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createName",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, name);
			}
		} catch (Exception e) {}
		
		try {
			PropertyDescriptor pd = new PropertyDescriptor("createTime",clazz);
			Method writeMethod = pd.getWriteMethod();
			if(writeMethod != null){
				writeMethod.invoke(obj, new Date());
			}
		} catch (Exception e) {}	
		
	}
	
	public static boolean isNotBaseType(Object o){
		if(o == null){
			return false;
		}
		Class<?> clazz = o.getClass();
		if(clazz.equals(String.class) || clazz.equals(BigDecimal.class) || clazz.equals(Date.class)
			|| clazz.equals(Integer.class) || clazz.equals(int.class)
			|| clazz.equals(Double.class) || clazz.equals(double.class)
			|| clazz.equals(Long.class) || clazz.equals(long.class)
			|| clazz.equals(Float.class) || clazz.equals(float.class)
			|| clazz.equals(Byte.class) || clazz.equals(byte.class)
			|| clazz.equals(char.class)
		){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isType(Object o,String name,Class<?> clazz){
		if(o == null){
			return false;
		}
		if(o instanceof Map){
			Object tmp = ((Map)o).get(name);
			if(tmp == null){
				return false;
			}
			return tmp.getClass().equals(clazz);
		}else{
			Field field = getField(o.getClass(),name);
			if(field == null){
				return false;
			}
			return field.getType().equals(clazz);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object getValue(Object source,String field){
		if(source == null) return null;
		Object value = null;
		if(source instanceof Map){
			value = ((Map)source).get(field); 
		}else{
			try {
				PropertyDescriptor pd = new PropertyDescriptor(field,source.getClass());
				Method method = pd.getReadMethod();
				value = method.invoke(source);
			} catch (Exception e) {
				return null;
			}
		}
		return value;
	}
	
	public static Method findMethod(Class<?> clazz,String name) {
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			if(method.getName().equals(name)) {
				return method;
			}
		}
		return null;
	}

}
