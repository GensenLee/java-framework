package org.devops.core.ie.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.StringUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtil {

	public static Cell getOrCreateCell(Sheet sheet, String position){
		int[] pos = splitPosition(position);
		Row row = getOrCreateRow(sheet, pos[1]);
		return getOrCreateCell(row, pos[0]);
	}

	public static Row getOrCreateRow(Sheet sheet,int rowIndex){
		Row row = sheet.getRow(rowIndex);
		if(row != null) return row;
		return sheet.createRow(rowIndex);
	}
	
	public static Cell getOrCreateCell(Row row,int cellIndex){
		Cell cell = row.getCell(cellIndex);
		if(cell != null) return cell;
		return row.createCell(cellIndex);
	}
	
	public static int toInt(String source){
		if(!source.toUpperCase().matches("[A-Z]+")){
			return IntUtil.toInt(source);
		}
		int total = 0;
		for(String tmp : source.split("")){
			if(StringUtil.isEmpty(tmp)) continue;
			total = total * 26 + (tmp.toUpperCase().charAt(0)-64);
		}
		return total;
	}
	
	public static String[] splitPostionStr(String position){
		Matcher columnMatch = Pattern.compile("^([a-z]+)",Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(position);
		String strColumn = "";
		if(columnMatch.find()){
			strColumn = columnMatch.group(1);
		}
		Matcher rowMatch = Pattern.compile("([+-]?[0-9]+)$",Pattern.MULTILINE|Pattern.CASE_INSENSITIVE).matcher(position);
		String strRow = "";
		if(rowMatch.find()){
			strRow = rowMatch.group(1);
		}
		return new String[]{strColumn,strRow};
	}
	public static int[] splitPosition(String position){
		return splitPosition(position,0);
	}
	
	public static String mergePosition(String position,String parent){
		if(StringUtil.isEmpty(parent)){
			return position;
		}
		String[] strTmp = splitPostionStr(parent);
		
		if(position.matches("\\+\\d+\\$\\{R\\}")) {
			String tmpPos = position.replace("${R}", "").replace("+", "");
			int column = toInt(strTmp[0]);
			column += toInt(tmpPos) + 64;
			char c = (char)column;
			position = c+"${R}";
		}
		if(position.matches("-\\d+\\$\\{R\\}")) {
			String tmpPos = position.replace("${R}", "").replace("-", "");
			int column = toInt(strTmp[0]);
			column = column - toInt(tmpPos) + 64;
			char c = (char)column;
			position = c+"${R}";
		}
		
		position = position.replace("${C}", strTmp[0]);
		position = position.replace("${R}", strTmp[1]);
		return position;
	}
	
	public static int[] splitPosition(String position,int offset){
		String[] strPosition = splitPostionStr(position);
		int column = toInt(strPosition[0]);
		int row = -1;
		try{
			row = toInt(strPosition[1]);
		}catch(Exception e){}
		
		return new int[]{column-1,row-1 + offset};
	}
	
	public static Object readCell(Cell cell,Object obj,Field field) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(field.getName(),obj.getClass());
			Method method = pd.getWriteMethod();
			
			Object value = null;
			value = convertCellValue(cell, method.getParameterTypes()[0]);
			method.invoke(obj, value);
			return value;
		} catch (Exception e) {
		}
		return null;
	}

	public static Object convertCellValue(Cell cell, Class<?> targetType) throws Exception {
		Object value = null;
		switch (cell.getCellTypeEnum()) {
			case FORMULA:
				try {
					if (cell instanceof XSSFCell && StringUtil.isNotEmpty(((XSSFCell) cell).getRawValue())) {
						value = trans(((XSSFCell) cell).getRawValue(), targetType);
					}else {
						try {
							value = trans(cell.getNumericCellValue(), targetType);
						} catch (Exception exception) {
							value = trans(cell.getStringCellValue(), targetType);
						}
					}
				} catch (Exception exception) {
					value = trans(cell.getRichStringCellValue(), targetType);
				}
				break;
			case NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					value = cell.getDateCellValue();
				} else {
					value = trans(BigDecimal.valueOf(cell.getNumericCellValue()), targetType);
				}
				break;
			case BOOLEAN:
				value = trans(cell.getBooleanCellValue(), targetType);
				break;
			case STRING:
				value = trans(cell.getStringCellValue(), targetType);
				break;
			default:
				break;
		}
		return value;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static <T1, T2> T2 trans(T1 val, Class<T2> cls2) throws Exception {
		if(val.getClass().equals(cls2)){
			return (T2)val;
		}else if(cls2.equals(String.class)) {
			Class cls1 = getWrapperClass(val.getClass());
			Method method = cls1.getMethod("toString");
			String result = (String)method.invoke(val);
			if(result != null){
				result = result.replaceAll("\\.0*$", "");
			}
			return (T2)result;
		}else if (!cls2.equals(BigDecimal.class)) {
			Class cls1 = getWrapperClass(val.getClass());
			Method method = cls1.getMethod("toString");
			String v1 = (String)method.invoke(val);
			Method valueOf = cls2.getMethod("valueOf", String.class);
			if(valueOf != null){
				if(cls2.equals(Long.class) || cls2.equals(Integer.class) ){
					v1 = v1.replaceAll("(.*?)(\\..*)?", "$1");
				}
				return (T2)valueOf.invoke(null, v1);
			}else{
				cls1 = getWrapperClass(val.getClass());
				cls2 = getBaseClass(cls2);
				Constructor constructor = cls1.getConstructor(getBaseClass(val.getClass()));
				Object obj = constructor.newInstance(val);
				Method transMethod = cls1.getMethod(cls2.getName() + "Value", null);
				return (T2) transMethod.invoke(obj, null);
			}
			
			
		} else {
			Constructor constructor = BigDecimal.class.getConstructor(getBaseClass(val.getClass()));
			Object object = constructor.newInstance(val);
			return (T2) object;
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	private static Class getWrapperClass(Class cls) {
		if (cls.equals(int.class) || cls.equals(Integer.class)) {
			return Integer.class;
		} else if (cls.equals(byte.class) || cls.equals(Byte.class)) {
			return Byte.class;
		} else if (cls.equals(boolean.class) || cls.equals(Boolean.class)) {
			return Boolean.class;
		} else if (cls.equals(short.class) || cls.equals(Short.class)) {
			return Short.class;
		} else if (cls.equals(char.class) || cls.equals(Character.class)) {
			return Character.class;
		} else if (cls.equals(float.class) || cls.equals(Float.class)) {
			return Float.class;
		} else if (cls.equals(double.class) || cls.equals(Double.class)) {
			return Double.class;
		}  
		return cls;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private static Class getBaseClass(Class cls) {
		if (cls.equals(int.class) || cls.equals(Integer.class)) {
			return int.class;
		} else if (cls.equals(byte.class) || cls.equals(Byte.class)) {
			return byte.class;
		} else if (cls.equals(boolean.class) || cls.equals(Boolean.class)) {
			return boolean.class;
		} else if (cls.equals(short.class) || cls.equals(Short.class)) {
			return short.class;
		} else if (cls.equals(char.class) || cls.equals(Character.class)) {
			return Character.class;
		} else if (cls.equals(float.class) || cls.equals(Float.class)) {
			return float.class;
		} else if (cls.equals(double.class) || cls.equals(Double.class)) {
			return double.class;
		} 
		return cls;
	}
}
