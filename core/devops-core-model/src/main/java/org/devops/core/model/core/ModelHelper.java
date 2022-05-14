package org.devops.core.model.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.BooleanUtils;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.ColumnAutoId;
import org.devops.core.model.annotation.Table;
import org.devops.core.model.annotation.TableIndex;
import org.devops.core.model.annotation.TableKey;
import org.devops.core.model.autoid.AutoIdGenerator;
import org.devops.core.model.autoid.ModelAutoIdFactory;
import org.devops.core.model.dto.TableField;
import org.devops.core.model.interfaces.BaseModelDao;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.DoubleUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelHelper {

	private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

	public static Map<String,Method> mapGetValueMethod = new HashMap<>();
	public static Map<String,Method> mapSetValueMethod = new HashMap<>();
	
	/**
	 * "abc" -> "Abc"
	 * @param str
	 * @return
	 */
	public static String toUCase(String str) {
		return ("" + str.charAt(0)).toUpperCase() + str.substring(1);
	}
	
	/**
	 * "Abc" -> "abc"
	 * @param str
	 * @return
	 */
	public static String toLCase(String str) {
		return ("" + str.charAt(0)).toLowerCase() + str.substring(1);
	}
	
	/**
	 * "abc_xyz" -> "AbcXyz"
	 * @param str
	 * @return
	 */
	public static String toHHCase(String str) {
		StringBuilder sb = new StringBuilder();
		for(String s : str.split("_")) {
			sb.append(toUCase(s));
		}
		return sb.toString();
	}
	
	/**
	 * "abc_xyz" -> "abcXyz"
	 * @param str
	 * @return
	 */
	public static String toLHCase(String str) {
		StringBuilder sb = new StringBuilder();
		for(String s : str.split("_")) {
			sb.append(toUCase(s));
		}
		return toLCase(sb.toString());
	}
	
	/**
	 * "AbcXye" -> "abc_xyz"
	 * @param str
	 * @return
	 */
	public static String toUUCase(String str) {
		str = str.replaceAll("([A-Z][a-z])", "_$1");
		StringBuilder sb = new StringBuilder();
		for(String s : str.split("_")) {
			if("".equals(s.trim())){
				continue;
			}
			sb.append("_").append(toLCase(s));
		}
		return sb.toString().substring(1);
	}
	
	public static String getTableName(Class<?> clazz){
		Table table = AnnotationUtils.findAnnotation(clazz, Table.class);
		String tableName = "";
		if(table == null){
			tableName = toUUCase(clazz.getSimpleName());
		}else{
			tableName = table.value().split(",")[0];
		}
		return tableName;
	}
	
	public static String getNameByAnnotation(Class<?> clazz,String field){
        List<Field> liField = BeanUtil.getAllField(clazz);
        for(Field f : liField){
        		Column column = AnnotationUtils.findAnnotation(f,Column.class);
        		if(column == null){
	        	 	JSONField jsonField = AnnotationUtils.findAnnotation(f,JSONField.class);	
	                if(jsonField == null){
	                	continue;    
	                }
					jsonField.name();
					if(jsonField.name().equals(field)){
	                    return f.getName();
	                }
        		}else{
					column.name();
					if(column.name().equals(field)){
	                    return f.getName();
	                }
        		}
        }
        return field;
	}
	
	public static String getAliasNameByAnnotation(Class<?> clazz,String field){
        List<Field> liField = BeanUtil.getAllField(clazz);
        for(Field f : liField){
        		Column column = AnnotationUtils.findAnnotation(f,Column.class);
        		if(column == null){
	        	 	continue;
        		}else{
					column.name();
					if(column.name().equals(field)){
	                    return f.getName();
	                }
        		}
        }
        return field;
	}
	
	public static String toUfirst(String name) {
		String first = name.substring(0, 1).toUpperCase();
		String rest = name.substring(1, name.length());
		return first + rest;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> convert(Object source,Class<T> clazz){
		if(source == null) return null;
		if(source instanceof List){
			List list = (List)source;
			if(list.size() != 0){
				
				//将 boolean 转成 数字
				for(Object o : list){
					if(o instanceof Map){
						Map<String,Object> tmpMap = (Map<String,Object>)o;
						for(String key : tmpMap.keySet()){
							if(tmpMap.get(key) instanceof Boolean){
								if(BooleanUtils.isFalse((Boolean)tmpMap.get(key))){
									tmpMap.put(key,(byte)0);
								}else{
									tmpMap.put(key,(byte)1);
								}
							}
						}
					}
				}
				
				Object tmpO = list.get(0);
				Map<String,String> alias = new HashMap<String,String>();
				if(tmpO instanceof Map){
					Map<String,Object> tmpMap = (Map<String,Object>)tmpO;
					for(String key : tmpMap.keySet()){
						String realName = getAliasNameByAnnotation(clazz,key);
						if(StringUtil.isEmpty(realName) || realName.equals(key)){
							continue;
						}
						alias.put(key, realName);
					}
				}
				
				for(String key : alias.keySet()){
					for(Object o : (List)source){
						if(o instanceof Map){
							Map<String,Object> tmp = (Map<String,Object>)o;
							Object value = tmp.get(key);
							tmp.remove(key);
							tmp.put(alias.get(key), value);
						}
					}
				}
				
			}
		}
		return FastJsonUtils.getBeanList(FastJsonUtils.toJsonString(source), clazz);
	}
	

	
	//将Object转换成指定类型
	@SuppressWarnings("unchecked")
	public static <T> T convertSingle(Object source,Class<T> clazz)
	{
		if(source == null){
			return null;
		}
		Class<?> sourceClazz = source.getClass();
		if(sourceClazz.equals(clazz)){
			return (T)source;
		}
		try {
			if(clazz.equals(String.class)){
				return (T)source.toString();
			}
			Method valueOf = clazz.getMethod("valueOf", String.class);
			return (T)valueOf.invoke(null, source.toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	public static Map<String,Object> formatCols(String cols,Object value)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		if(cols.contains("?")){
			String[] arrCols = cols.split(",");
			for (String arrCol : arrCols) {
				String col = arrCol.split("=")[0].replaceAll("\\s", "");
				String methodName = "get" + toUCase(col);
				try {
					Method method = value.getClass().getMethod(methodName);
					map.put(col, method.invoke(value));
				} catch (Exception e) {
				}

			}
		}else{
			map.put(cols, value);
		}
		return map;
	}
	
	public static String getSetSQLParametric(Object updateModel,List<String> includeFields,List<TableField> liTableField,boolean ignorePriKey,List<List<Object>> listParameter){
		
		List<String> fields = new ArrayList<String>();
		
		if(liTableField != null) {
			for(TableField tableField : liTableField){
				if("PRI".equalsIgnoreCase(tableField.getKey()) && "auto_increment".equalsIgnoreCase(tableField.getExtra()) && ignorePriKey){
					continue;
				}
				if(!(updateModel instanceof List)){
					if(getValue(updateModel,tableField.getField()) == null){
						continue;
					}
				}
				if(includeFields.isEmpty()){
					fields.add(tableField.getField());
				}else{
					if(includeFields.contains(tableField.getField())){
						fields.add(tableField.getField());
					}
				}
			}
		}
		
		List<String> setValue = new ArrayList<String>();
		List<Object> listParam = new ArrayList<Object>();
		listParameter.add(listParam);
		for(String field : fields){
			Object v = getValue(updateModel,field);
			boolean isString = isString(updateModel,field, liTableField);
			if(v == null){
				setValue.add("`"+field+"` = ?");
				listParam.add(null);
			}else{
				if(v instanceof String){
					if(!isString && isOperator(v.toString())){
						//将v切分成 column 和 value
						String[] columnValue = spliteOperator(v.toString());
						setValue.add("`"+field+"` = " + columnValue[0]+" "+columnValue[1]+" ?");
						listParam.add(DoubleUtil.toDouble(columnValue[2].trim()));
						continue;
					}
				}
				setValue.add("`"+field+"` = ?");
				listParam.add(v);
			}
		}
		
		return ListUtil.join(setValue);
		
	}
	
	public static String getSetSQL(Object updateModel,List<String> includeFields,List<TableField> liTableField,boolean ignorePriKey){
		
		List<String> fields = new ArrayList<String>();
		
		if(liTableField != null) {
			for(TableField tableField : liTableField){
				if("PRI".equalsIgnoreCase(tableField.getKey()) && "auto_increment".equalsIgnoreCase(tableField.getExtra()) && ignorePriKey){
					continue;
				}
				if(!(updateModel instanceof List)){
					if(getValue(updateModel,tableField.getField()) == null){
						continue;
					}
				}
				if(includeFields.isEmpty()){
					fields.add(tableField.getField());
				}else{
					if(includeFields.contains(tableField.getField())){
						fields.add(tableField.getField());
					}
				}
			}
		}
		
		List<String> setValue = new ArrayList<String>();
		
		for(String field : fields){
			Object v = getValue(updateModel,field);
			boolean isString = isString(updateModel,field, liTableField);
			if(v == null){
				setValue.add("`"+field+"` = null");
			}else{
				if(v instanceof String){
					if(!isString && isOperator(v.toString())){
						setValue.add("`"+field+"` = " + v.toString());
						continue;
					}
				}
				setValue.add("`"+field+"` = " + filter(v,false).trim());
			}
		}
		
		return ListUtil.join(setValue);
		
	}
	
	@SuppressWarnings("rawtypes")
	public static Object getValue(Object source,String field){
		if(source == null) return null;
		Object value = null;
		if(source instanceof Map){
			value = ((Map)source).get(field); 
		}else{
			try {
				String key = source.getClass().getName() + "." + field;
				Method method = mapGetValueMethod.get(key);
				
				if(method == null) {
					PropertyDescriptor pd = null;
					try{
						field = getNameByAnnotation(source.getClass(),field);
						pd = new PropertyDescriptor(field,source.getClass());
					}catch(Exception e){}
					if(pd == null) {
						try{
							pd = new PropertyDescriptor(field,source.getClass());
						}catch(Exception e){}
					}
					if(pd == null) {
						pd = new PropertyDescriptor(toLHCase(field),source.getClass());
					}
					method = pd.getReadMethod();
					mapGetValueMethod.put(key, method);
				}
				
				value = method.invoke(source);
			} catch (Exception e) {
				return null;
			}
		}
		return value;
	}
	
	public static void setValue(Object source,String field, Object value){
		if(source == null) return;
		try {
			String key = source.getClass().getName() + "." + field;
			Method method = mapSetValueMethod.get(key);
			
			if(method == null) {
				PropertyDescriptor pd = null;
				try{
					field = getNameByAnnotation(source.getClass(),field);
					pd = new PropertyDescriptor(field,source.getClass());
				}catch(Exception e){}
				if(pd == null) {
					try{
						pd = new PropertyDescriptor(field,source.getClass());
					}catch(Exception e){}
				}
				if(pd == null) {
					pd = new PropertyDescriptor(toLHCase(field),source.getClass());
				}
				method = pd.getWriteMethod();
				mapSetValueMethod.put(key, method);
			}
			method.invoke(source, value);
		} catch (Exception e) {
			return;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isInstance(List list,Class<?> clazz) {
		if(list == null || list.size() == 0) {
			return false;
		}
		return clazz.isInstance(list.get(0));
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Object> getValueList(List list,String field){
		List<Object> result = new ArrayList<>();
		for(Object o : list) {
			result.add(getValue(o, field));
		}
		return result;
	}
	
	public static boolean isString(Object source,String field, List<TableField> liTableField){
		if(source == null) return false;
		if(source instanceof Map){
			for(TableField tableField : liTableField) {
				if(tableField.getField().equalsIgnoreCase(field)) {
					if(tableField.getType().contains("char") || tableField.getType().contains("text")) {
						return true;
					}
				}
			}
			return false; 
		}else{
			try {
				field = getNameByAnnotation(source.getClass(),field);
				PropertyDescriptor pd = new PropertyDescriptor(field,source.getClass());
				Method method = pd.getReadMethod();
				return method.getReturnType().equals(String.class);
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	public static boolean isOperator(String value) {
		return value.matches("^\\s*`?[\\w]+`?\\s*?[\\+\\-\\/\\*]\\s*?[\\d]+(\\.\\d+)?\\s*$");
	}
	
	public static String[] spliteOperator(String value) {
		Pattern p = Pattern.compile("(^\\s*`?[\\w]+`?\\s*?)([\\+\\-\\/\\*])\\s*?([\\d]+(\\.\\d+)?)\\s*$");
		Matcher m = p.matcher(value);
		String[] result = new String[3];
		if(m.find()){
			result[0] = m.group(1).trim();
			result[1] = m.group(2).trim();
			result[2] = m.group(3).trim();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setLastInsertId(Object source,String priKey,Long lastId){
		if(source == null) return;
		if(!(source instanceof List)){
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(source);
			source = tmp;
		}
		
		
		for(Object o : (List)source){
			if(o instanceof Map){
				((Map)o).put(priKey, lastId);
			}else{
				try {
					priKey = getNameByAnnotation(o.getClass(),priKey);
					PropertyDescriptor pd = new PropertyDescriptor(priKey,o.getClass());
					Method method = pd.getWriteMethod();
					Field field = BeanUtil.getField(o.getClass(),priKey);
					Object value = lastId;
					
					Class<?> paramType = field.getType();
					if(!paramType.equals(String.class)) {
						Method valueOf = paramType.getMethod("valueOf",String.class);
						value = valueOf.invoke(null,lastId.toString());
					}
					method.invoke(o,value);
				} catch (Exception e) {
					return;
				}
			}
			lastId++;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setAutoId(Object source, Map<String, AutoIdGenerator<?>> mapAutoId) {
		if(source == null || mapAutoId == null || mapAutoId.size() == 0) return;
		if(!(source instanceof List)){
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(source);
			source = tmp;
		}
		
		for(Object o : (List)source){
			if(o instanceof Map){
				for(String fieldName : mapAutoId.keySet()) {
					if(((Map)o).get(fieldName) == null) {
						((Map)o).put(fieldName, mapAutoId.get(fieldName).nextId());
					}
				}
				
			}else{
				for(String fieldName : mapAutoId.keySet()) {
					try {
						PropertyDescriptor pd = new PropertyDescriptor(fieldName,o.getClass());
						Method readMethod = pd.getReadMethod();
						Object v = readMethod.invoke(o);
						if(v != null) {
							continue;
						}
						Method writeMethod = pd.getWriteMethod();
						Field field = BeanUtil.getField(o.getClass(),fieldName);
						Object value = mapAutoId.get(fieldName).nextId();
						
						Class<?> paramType = field.getType();
						if(!paramType.equals(String.class)) {
							Method valueOf = paramType.getMethod("valueOf",String.class);
							value = valueOf.invoke(null,value.toString());
						}
						writeMethod.invoke(o,value);
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
	}


	/** 替换特殊字符 防止sql语句注入*/
	public static String filter(Object value,boolean noMarks)
	{
		if(value == null){
			return "null";
		}
		if(value instanceof String){
			String tmpStr = value.toString().replace("\\", "\\\\").replace("'", "\\'").replace("\r", "").replace("\n", "");
			if(noMarks){
				return " "+tmpStr+" ";
			}
			return " '"+tmpStr+"' ";
		}else if(value instanceof Date){
			return " '"+DateUtil.getDateTimeFormat((Date)value)+"' ";
		}
		return " "+value.toString()+" ";
	}
	
	@SuppressWarnings("rawtypes")
	public static String getInsertValueParametric(Object insertModel,List<String> includeFields,List<TableField> liTableField,boolean ignorePriKey,List<List<Object>> listParameter)
	{
		List<String> values = new ArrayList<String>();
		
		List<String> fields = new ArrayList<String>();
		
		Map<String,TableField> mapTableField = new HashMap<String,TableField>();
		
		for(TableField tableField : liTableField){
			if("PRI".equalsIgnoreCase(tableField.getKey()) && "auto_increment".equalsIgnoreCase(tableField.getExtra()) && ignorePriKey){
				continue;
			}
			mapTableField.put(tableField.getField(), tableField);
			if(!(insertModel instanceof List)){
				if(getValue(insertModel,tableField.getField()) == null){
					continue;
				}
			}
			if(includeFields.isEmpty()){
				fields.add(tableField.getField());
			}else{
				if(includeFields.contains(tableField.getField())){
					fields.add(tableField.getField());
				}
			}
		}
		
		if(!(insertModel instanceof List)){
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(insertModel);
			insertModel = tmp;
		}
		
		
		for(Object o : (List)insertModel){
			
			List<Object> liParam = new ArrayList<Object>();
			listParameter.add(liParam);
			
			List<String> value = new ArrayList<String>();
			for(String field : fields){
				Object v = getValue(o,field);
				if(v == null){
					TableField tableField = mapTableField.get(field);
					if(tableField.getNull() != null && "NO".equalsIgnoreCase(tableField.getNull())
							&& tableField.getDefault() != null
							){
						//value.add(filter(tableField.getDefault(),false).trim());
						liParam.add(tableField.getDefault());
					}else{
						//value.add("null");
						liParam.add(null);
					}
				}else{
					//value.add(filter(v,false).trim());
					liParam.add(v);
				}
				value.add("?");
			}
			
			values.add("("+ListUtil.join(value)+")");
			
		}
		
		return ListUtil.join(values);
	}
	
	/** 获取值*/
	@SuppressWarnings("rawtypes")
	public static String getInsertValue(Object insertModel,List<String> includeFields,List<TableField> liTableField,boolean ignorePriKey)
	{
		List<String> values = new ArrayList<String>();
		
		List<String> fields = new ArrayList<String>();
		
		Map<String,TableField> mapTableField = new HashMap<String,TableField>();
		
		for(TableField tableField : liTableField){
			if("PRI".equalsIgnoreCase(tableField.getKey()) && "auto_increment".equalsIgnoreCase(tableField.getExtra()) && ignorePriKey){
				continue;
			}
			mapTableField.put(tableField.getField(), tableField);
			if(!(insertModel instanceof List)){
				if(getValue(insertModel,tableField.getField()) == null){
					continue;
				}
			}
			if(includeFields.isEmpty()){
				fields.add(tableField.getField());
			}else{
				if(includeFields.contains(tableField.getField())){
					fields.add(tableField.getField());
				}
			}
		}
		
		if(!(insertModel instanceof List)){
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(insertModel);
			insertModel = tmp;
		}
		
		
		for(Object o : (List)insertModel){
			
			List<String> value = new ArrayList<String>();
			for(String field : fields){
				Object v = getValue(o,field);
				if(v == null){
					TableField tableField = mapTableField.get(field);
					if(tableField.getNull() != null && "NO".equalsIgnoreCase(tableField.getNull())
							&& tableField.getDefault() != null
							){
						value.add(filter(tableField.getDefault(),false).trim());
					}else{
						value.add("null");
					}
				}else{
					value.add(filter(v,false).trim());
				}
			}
			
			values.add("("+ListUtil.join(value)+")");
			
		}
		
		return ListUtil.join(values);
	}
	
	/** 获取列名*/
	public static String getInsertColumn(Object insertModel,List<String> includeFields,List<TableField> liTableField,boolean ignorePriKey)
	{
		List<String> cloumn = new ArrayList<String>();
		
		for(TableField tableField : liTableField){
			if("PRI".equalsIgnoreCase(tableField.getKey()) && "auto_increment".equalsIgnoreCase(tableField.getExtra()) && ignorePriKey){
				continue;
			}
			if(!(insertModel instanceof List)){
				if(getValue(insertModel,tableField.getField()) == null){
					continue;
				}
			}
			if(includeFields.isEmpty()){
				cloumn.add("`"+tableField.getField()+"`");
			}else{
				if(includeFields.contains(tableField.getField())){
					cloumn.add("`"+tableField.getField()+"`");
				}
			}
		}
		return "("+ListUtil.join(cloumn)+")";
	}
	public static List<TableField> getTableFields(Class<?> clazz){
		List<TableField> liTableField = new ArrayList<TableField>();
		List<Field> liField = BeanUtil.getAllField(clazz);
		for(Field field : liField){
			TableField tableField = new TableField();
			liTableField.add(tableField);
			
			Column column = AnnotationUtils.findAnnotation(field,Column.class);
			if(column == null){
				continue;
			}
			String name = field.getName();
			Column columnField = AnnotationUtils.findAnnotation(field,Column.class);
			if(columnField != null){
				name = columnField.name();
			}
			
			if(column.priKey()){
				tableField.setKey("PRI");
			}
			tableField.setComment(column.comment());
			tableField.setDefault(column.def());
			tableField.setExtra("");
			tableField.setField(name);
			tableField.setType(column.jdbcType());
			tableField.setNull(column.notNull()?"NO":"YES");
		}
		return liTableField;
	}
	
	public static String getCreateSQL(Class<?> clazz){
		return getCreateSQL(clazz,"");
	}
	public static String getCreateSQL(Class<?> clazz,String tableName){
		Table table = AnnotationUtils.findAnnotation(clazz, Table.class);
		
		List<String> liColumn = new ArrayList<String>();
		List<Field> liField = BeanUtil.getAllField(clazz);
		String priKeyColumn = "";
		String autoIncrementBeginAt = "";
		for(Field field : liField){
			Column column = AnnotationUtils.findAnnotation(field,Column.class);
			if(column == null){
				continue;
			}
			String name = field.getName();
			Column columnField = AnnotationUtils.findAnnotation(field,Column.class);
			if(columnField != null){
				name = columnField.name();
			}
			
			String extra = column.extra();
			
			if(StringUtil.isEmpty(extra)){
				extra = column.autoIncrement()?"AUTO_INCREMENT":"";
			}
			
			if(column.priKey()){
				priKeyColumn = "PRIMARY KEY (`"+name+"`)";
				if(column.autoIncrementBeginAt() > 1) {
					autoIncrementBeginAt = "AUTO_INCREMENT=" + column.autoIncrementBeginAt();
				}
			}
			
			String notNull = column.notNull()?"NOT NULL":"NULL";
			if(column.priKey()){
				notNull = "NOT NULL";
			}
			
			String def = StringUtil.isNotEmpty(column.def())?"DEFAULT "+column.def():"";
			
			if(!column.priKey() && StringUtil.isEmpty(def) && !column.notNull()){
				def = "DEFAULT NULL";
			}
			
			liColumn.add(String.format("`%s` %s %s %s %s %s COMMENT '%s'", 
					name,
					column.jdbcType(),
					(StringUtil.isNotEmpty(column.charset())?"CHARACTER SET "+column.charset():""),
					(notNull),
					(StringUtil.isNotEmpty(extra)?extra.toUpperCase():""),
					(def),
					column.comment()
				));
		}
		if(StringUtil.isNotEmpty(priKeyColumn)){
			liColumn.add(priKeyColumn);
		}
		
		TableKey tableKey = AnnotationUtils.findAnnotation(clazz, TableKey.class);
		if(tableKey != null) {
			// 如果有联合键
			if(tableKey.type().equals(TableKey.TYPE_UNIQUE_KEY)) {
				String[] arrKeys = tableKey.column();
				for(String keys : arrKeys) {
					if(StringUtil.isEmpty(keys)) {
						continue;
					}
					List<String> liKey = Arrays.asList(keys.split(","));
					liColumn.add(String.format("UNIQUE KEY `%s_UN` (%s)",ListUtil.join(liKey,"_"),ListUtil.join(ListUtil.replaceKeys(liKey, "`", "`"))));
				}
			}
		}
		
		TableIndex tableIndex = AnnotationUtils.findAnnotation(clazz, TableIndex.class);
		if(tableIndex != null) {
			// 如果有索引
			String[] arrKeys = tableIndex.column();
			for(String keys : arrKeys) {
				if(StringUtil.isEmpty(keys)) {
					continue;
				}
				List<String> liKey = Arrays.asList(keys.split(","));
				liColumn.add(String.format("KEY `%s_IDX` (%s) USING %s",ListUtil.join(liKey,"_"),ListUtil.join(ListUtil.replaceKeys(liKey, "`", "`")),tableIndex.type()));
			}
		}
		
		String strColumn = ListUtil.join(liColumn,",");
		String sql = "CREATE TABLE `%s`( %s ) ENGINE=%s %s DEFAULT CHARSET=%s COMMENT='%s'";
		if(StringUtil.isEmpty(tableName)){
			tableName = table.value();
		}
		sql = String.format(sql, tableName,strColumn,table.engine(),autoIncrementBeginAt,table.charset(),table.comment());
		
		
		
		return sql;
	}
	
	public static Map<String,AutoIdGenerator<?>> getMapAutoId(Class<?> clazz,String tableName) {
		Map<String,AutoIdGenerator<?>> result = new HashMap<>();
		List<Field> liField = BeanUtil.getAllField(clazz);
		for(Field field : liField){
			ColumnAutoId columnAutoId = AnnotationUtils.findAnnotation(field,ColumnAutoId.class);
			if(columnAutoId == null) {
				continue;
			}
			result.put(field.getName(), ModelAutoIdFactory.createAutoIdGenerator(columnAutoId.value(),tableName + "." + field.getName()));
		}
		return result;
	}
	
	public static String getAddColumnSQL(Class<?> clazz,Map<String,TableField> mapTableField){
		return getAddColumnSQL(clazz,mapTableField,"");
	}
	public static String getAddColumnSQL(Class<?> clazz,Map<String,TableField> mapTableField,String tableName){
		Table table = AnnotationUtils.findAnnotation(clazz,Table.class);
		if(StringUtil.isEmpty(tableName)){
			tableName = table.value();
		}
		List<String> liSQL = new ArrayList<String>();
		for(String key : mapTableField.keySet()){
			TableField tableField = mapTableField.get(key);
			liSQL.add(String.format("ALTER TABLE `%s` ADD %s;", tableName,
					String.format("`%s` %s %s %s COMMENT '%s'", 
							tableField.getField(),
							tableField.getType(),
					("NO".equalsIgnoreCase(tableField.getNull())?"NOT NULL":""),
					(StringUtil.isNotEmpty(tableField.getDefault())?"DEFAULT "+tableField.getDefault():""),
					tableField.getComment()
				)));
		}
		return ListUtil.join(liSQL,"");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getInitData(Class<T> clazz){
		try {
			Method method = clazz.getMethod("init");
			Object result = method.invoke(null);
			if(result != null){
				return (List<T>)result;
			}
		} catch (Exception e) {
			
		} 
		return null;
	}
	
	public static String getInitSQL(Class<?> clazz){
		try {
			Method method = clazz.getMethod("initSQL");
			Object result = method.invoke(null);
			if(result != null){
				return result.toString();
			}
		} catch (Exception e) {
			
		} 
		return null;
	}
	
	public static <T> List<T> getBeanList(Class<T> clazz){
		long currentTime = System.currentTimeMillis();
		while(SpringContextUtil.getContext() == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if(System.currentTimeMillis() - currentTime > 60 * 60 * 1000){
				return null;
			}
		}
		try{
			return SpringContextUtil.getBeanList(clazz);
		}catch(Exception e){
			return null;
		}
	}
	
	public static <T> T getBean(Class<T> clazz){
		long currentTime = System.currentTimeMillis();
		while(SpringContextUtil.getContext() == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if(System.currentTimeMillis() - currentTime > 60 * 60 * 1000){
				return null;
			}
		}
		return SpringContextUtil.getBean(clazz);
	}
	
	public static <T> T getBean(String prefix, Class<T> clazz){
		long currentTime = System.currentTimeMillis();
		while(SpringContextUtil.getContext() == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if(System.currentTimeMillis() - currentTime > 60 * 60 * 1000){
				return null;
			}
		}
		
		T bean = SpringContextUtil.getBean(prefix, clazz);
		if(bean != null) {
			return bean;
		}
		
		if(prefix.contains("_")) {
			prefix = StringUtil.toLHCase(prefix.toLowerCase());
		}
		
		return SpringContextUtil.getBean(prefix, clazz);
	}
	
	public static BaseModelDao getBaseModelDao(String modelName){
		long currentTime = System.currentTimeMillis();
		while(SpringContextUtil.getContext() == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if(System.currentTimeMillis() - currentTime > 60 * 60 * 1000){
				return null;
			}
		}
		return (BaseModelDao) SpringContextUtil.getBean(modelName);
	}
	
	public static void invokeWithThreadSingle(final Object obj,String methodName,final Object ...args){
		Class<?> clazz = obj.getClass();
		Class<?>[] parameterTypes = null;
		if(args != null){
			parameterTypes = new Class<?>[args.length];
			for(int i=0; i< args.length; i++){
				parameterTypes[i] = args[i].getClass();
			}
		}
		
		
		
		Method method = null;
		try{
			method = clazz.getDeclaredMethod(methodName, parameterTypes);
		}catch(Exception e){
			for (Method m : clazz.getMethods()) {
				if (m.getName().equals(methodName)) {
					if (parameterTypes != null && m.getParameterCount() != parameterTypes.length) {
						continue;
					}
					method = m;
					break;
				}
			}
			if (method == null) {
				log.error("[Exception 出错啦!]",e);
				return;
			}
		}

		Method finalMethod = method;
		EXECUTOR.execute(() -> {
			try{
				finalMethod.invoke(obj, args);
			}catch(Exception e){
				log.error("[Exception 出错啦!]",e);
			}
		});
	}
	
	public static void invokeWithThread(List<?> listObj,String methodName,final Object... args){
		if(CollectionUtils.isEmpty(listObj)){
			return;
		}
		for(final Object obj : listObj){
			invokeWithThreadSingle(obj,methodName,args);
		}
	}
}
