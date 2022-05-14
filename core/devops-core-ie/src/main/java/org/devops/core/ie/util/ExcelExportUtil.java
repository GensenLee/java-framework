package org.devops.core.ie.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.devops.core.ie.annotation.Excel;
import org.devops.core.ie.annotation.ExcelCell;
import org.devops.core.ie.annotation.ExcelRow;
import org.devops.core.ie.annotation.ExcelSheet;
import org.devops.core.ie.annotation.ExcelStyle;
import org.devops.core.ie.annotation.ExcelTemplate;
import org.devops.core.ie.annotation.ExcelTitle;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;
import org.springframework.core.annotation.AnnotationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出工具
 * @author xhz
 *
 */
@Slf4j
public class ExcelExportUtil {

	private static final int pageSize = 60000;

	@SuppressWarnings("rawtypes")
	private static Map<String,CellStyle> createCellStyle(Workbook workbook,Object source,Map<String,CellStyle> mapCellStyle){
		Class<?> clazz = source.getClass();
		List<Field> liField = BeanUtil.getAllField(clazz);
		for(Field field : liField){
			ExcelStyle excelStyle = AnnotationUtils.findAnnotation(field, ExcelStyle.class);
			if(excelStyle == null) {
				continue;
			}
			try {
				field.setAccessible(true);
				Object value = field.get(source);
				if(value != null && value instanceof Map) {
					for(Object key : ((Map)value).keySet()) {
						if(key.toString().equals("*")){
							mapCellStyle.put("*", (CellStyle)((Map)value).get(key));
							continue;
						}
						int[] intPosition = ExcelUtil.splitPosition(key.toString());
						if(intPosition[0] == -1 || intPosition[1] == -1){
							continue;
						}
						mapCellStyle.put(intPosition[0] + "," + intPosition[1], (CellStyle)((Map)value).get(key));
					}
					continue;
				}

				List<String> liPosition = new ArrayList<String>();

				List<String> liOrgPostion = Arrays.asList(excelStyle.position());

				int type = 0;
				if(liOrgPostion.size() == 1){
					ExcelCell excelCell = AnnotationUtils.findAnnotation(field, ExcelCell.class);
					if(excelCell != null && StringUtil.isNotEmpty(excelCell.position())) {
						if(excelCell.position().matches(".*?\\$\\{R\\}.*?")){
							String key = getFieldKey(field);
							liOrgPostion = new ArrayList<String>();
							liOrgPostion.add(key);
							type = 1;
						}else{
							liOrgPostion = new ArrayList<String>();
							liOrgPostion.add(excelCell.position());
						}
					}else{
						ExcelRow excelRow = AnnotationUtils.findAnnotation(field, ExcelRow.class);
						if(excelRow != null && StringUtil.isNotEmpty(excelRow.position())){
							liOrgPostion = new ArrayList<String>();
							liOrgPostion.add(excelRow.position());
						}
					}
				}

				for(String pos : liOrgPostion) {
					if(type == 0){
						int[] intPosition = ExcelUtil.splitPosition(pos);
						if(intPosition[0] == -1 || intPosition[1] == -1){
							continue;
						}
						liPosition.add(intPosition[0] + "," + intPosition[1]);
					}else{
						liPosition.add(pos);
					}

				}

				if(liPosition.isEmpty()) {
					liPosition.add("*");
				}
				CellStyle cellStyle = null;
				if(value != null && value instanceof CellStyle){
					cellStyle = (CellStyle)value;
				}

				if(cellStyle == null) {
					cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(excelStyle.alignment());
					cellStyle.setVerticalAlignment(excelStyle.verticalAlignment());
					cellStyle.setWrapText(excelStyle.wrapText());
					if(excelStyle.border()){
						cellStyle.setBorderBottom(BorderStyle.THIN);
						cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);

						cellStyle.setBorderLeft(BorderStyle.THIN);
						cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

						cellStyle.setBorderRight(BorderStyle.THIN);
						cellStyle.setRightBorderColor(IndexedColors.BLACK.index);

						cellStyle.setBorderTop(BorderStyle.THIN);
						cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
					}

					Font font = workbook.createFont();
					font.setFontName(excelStyle.fontName());
					font.setFontHeight((short) (excelStyle.fontHeight() * 20));
					font.setColor(excelStyle.fontColor());

					cellStyle.setFont(font);
				}
				for(String pos : liPosition){
					mapCellStyle.put(pos, cellStyle);
				}
			} catch (Exception e) {
			}
		}
		return mapCellStyle;
	}

	private static CellStyle getFileCellStyle(Field field,Map<String,CellStyle> mapCellStyle){
		String key = getFieldKey(field);
		return mapCellStyle.get(key);
	}

	private static CellStyle getCellStyle(String position,Map<String,CellStyle> mapCellStyle){
		CellStyle cellStyle = null;
		int[] positionTemplate = ExcelUtil.splitPosition(position);
		String key = positionTemplate[0]+","+positionTemplate[1];
		cellStyle = mapCellStyle.get(key);
		if(cellStyle == null) {
			cellStyle = mapCellStyle.get("*");
		}
		return cellStyle;
	}

	private static String getFieldKey(Field field){
		String key = field.getDeclaringClass().getName() + "." + field.getName();
		return key;
	}

	private static CellStyle setCellStyle(Cell cell,Field field,Map<String,CellStyle> mapCellStyle){
		String key = getFieldKey(field);
		CellStyle cellStyle = mapCellStyle.get(key);
		if(cellStyle != null) {
			cell.setCellStyle(cellStyle);
			return cellStyle;
		}
		key = cell.getColumnIndex() + "," + cell.getRowIndex();
		cellStyle = mapCellStyle.get(key);
		if(cellStyle == null) {
			cellStyle = mapCellStyle.get("*");
		}

		if(cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		return cellStyle;
	}

	private static void writeTitle(Object source, Sheet sheet, Field field, ExcelTitle excelTitle,Map<String,CellStyle> mapCellStyle){

		String position = excelTitle.position();

		if(StringUtil.isNotEmpty(position) && excelTitle.value().length > 0){
			//如果不为空,就写入标题
			int[] postionFormat = ExcelUtil.splitPosition(position);

			if(postionFormat[0] == -1 || postionFormat[1] == -1){
				return;
			}
			Row row = ExcelUtil.getOrCreateRow(sheet,postionFormat[1]);

			int pos = 0;
			for(int i=0; i< excelTitle.value().length;i++){
				int span = 1;
				try{
					span = excelTitle.span()[i];
				}catch(Exception e){

				}
				int columnPosition = postionFormat[0] + i + pos;
				Cell cell = ExcelUtil.getOrCreateCell(row, columnPosition);
				CellStyle cellStyle = setCellStyle(cell,field,mapCellStyle);
				cell.setCellValue(excelTitle.value()[i]);
				if(span != 1){
					pos += span - 1;
					CellRangeAddress cra = new CellRangeAddress(postionFormat[1], postionFormat[1], columnPosition, columnPosition + span - 1);
					sheet.addMergedRegion(cra);
					if(cellStyle != null && !BorderStyle.NONE.equals(cellStyle.getBorderBottomEnum())){
						RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet); // 下边框
						RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框
						RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 有边框
						RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet); // 上边框
					}
				}

				try{
					double width = excelTitle.width()[i];
					sheet.setColumnWidth(columnPosition, (int)((width + 0.72) * 256));
				}catch(Exception e){

				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static int calcSpanRow(Object source,Field field){
		Class<?> clazz = source.getClass();
		int maxSpan = 1;
		ExcelCell excelCell = AnnotationUtils.findAnnotation(field, ExcelCell.class);
		if(excelCell != null){
			String spanRow = excelCell.spanRow();
			if(spanRow.matches("\\d+")) {
				int span = IntUtil.toInt(spanRow);
				if(span > maxSpan){
					maxSpan = span;
				}
			}else{
				try {
					Field targetField = clazz.getDeclaredField(spanRow);
					targetField.setAccessible(true);
					Object value = targetField.get(source);
					if(value != null && value instanceof List){
						int span = 0;
						for(Object item : ((List)value)) {
							span += calcRow(item);
						}
						if(span > maxSpan){
							maxSpan = span;
						}
					}
				} catch (Exception e) {
				}
			}
		}
		return maxSpan;
	}

	@SuppressWarnings("rawtypes")
	private static int calcSpanColumn(Object source,Field field){
		Class<?> clazz = source.getClass();
		int maxSpan = 1;
		ExcelCell excelCell = AnnotationUtils.findAnnotation(field, ExcelCell.class);
		if(excelCell != null){
			String spanColumn = excelCell.spanColumn();
			if(spanColumn.matches("\\d+")) {
				int span = IntUtil.toInt(spanColumn);
				if(span > maxSpan){
					maxSpan = span;
				}
			}else{
				try {
					Field targetField = clazz.getDeclaredField(spanColumn);
					targetField.setAccessible(true);
					Object value = targetField.get(source);
					if(value != null && value instanceof List){
						int span = ((List)value).size();
						if(span > maxSpan){
							maxSpan = span;
						}
					}
				} catch (Exception e) {
				}
			}
		}
		return maxSpan;
	}

	private static int calcRow(Object source){
		Class<?> clazz = source.getClass();
		List<Field> liField = BeanUtil.getAllField(clazz);

		int maxSpan = 1;
		for(Field field : liField){
			int span = calcSpanRow(source, field);
			if(span > maxSpan){
				maxSpan = span;
			}
		}
		return maxSpan;
	}

	@SuppressWarnings("rawtypes")
	private static void writeRow(Object source,Workbook workbook, Sheet sheet, Field field,ExcelRow excelRow, String position,Map<String,CellStyle> mapCellStyle,CellStyle cellStyle){
		if(StringUtil.isEmpty(position)){
			return;
		}
		//如果不为空,就写入标题
		int[] postionFormat = ExcelUtil.splitPosition(position);
		if(postionFormat[0] == -1 || postionFormat[1] == -1){
			return;
		}
		field.setAccessible(true);
		Object value;
		try {
			value = field.get(source);
			if(value == null){
				return;
			}
			if(cellStyle == null){
				cellStyle = getCellStyle(position,mapCellStyle);
			}
			if(value instanceof List){
				String[] tmpPosition = ExcelUtil.splitPostionStr(position);

				int span = 0;
				for(Object item : (List)value){
					createCellStyle(workbook, item, mapCellStyle);
					String pos = tmpPosition[0] + (IntUtil.toInt(tmpPosition[1]) + span);
					int rowCount = calcRow(item);
					if(excelRow.insert() && sheet.getLastRowNum() > IntUtil.toInt(tmpPosition[1]) + span - 1){
						sheet.shiftRows(IntUtil.toInt(tmpPosition[1]) + span - 1, sheet.getLastRowNum(), rowCount,true,false);
					}
//					for(int r = 0; r<rowCount; r++) {
//						map.put(IntUtil.toInt(tmpPosition[1]) - 1 + r,sheet.createRow(IntUtil.toInt(tmpPosition[1]) - 1 + r));
//					}
					writeCell(item,workbook,sheet,pos,mapCellStyle,cellStyle);
					span += rowCount;
				}
			}else{
				createCellStyle(workbook, value, mapCellStyle);
				writeCell(value,workbook,sheet,position,mapCellStyle,cellStyle);
			}
		} catch (Exception e) {

		}
	}

	private static void writeCell(Object source,Workbook workbook, Sheet sheet,String position,Map<String,CellStyle> mapCellStyle,CellStyle cellStyle){
		if(StringUtil.isEmpty(position) || source == null){
			return;
		}
		Class<?> clazz = source.getClass();
		List<Field> liField = BeanUtil.getAllField(clazz);
		for(Field field : liField){
			try{
				writeField(source, mapCellStyle, workbook, sheet, field, position,cellStyle);
			}catch(Exception e){
			}
		}
	}


	private static void writeCell(Object source, Sheet sheet, Field field,ExcelCell excelCell, String position,Map<String,CellStyle> mapCellStyle,CellStyle cellStyle){
		if(StringUtil.isEmpty(position)){
			return;
		}
		//如果不为空,就写入标题
		int[] postionFormat = ExcelUtil.splitPosition(position);

		if(postionFormat[0] == -1 || postionFormat[1] == -1){
			return;
		}
		Row row = ExcelUtil.getOrCreateRow(sheet,postionFormat[1]);
		Cell cell = ExcelUtil.getOrCreateCell(row, postionFormat[0]);
		CellStyle fieldCellStyle = getFileCellStyle(field,mapCellStyle);
		if(fieldCellStyle != null){
			cellStyle = fieldCellStyle;
		}
		if(cellStyle == null){
			cellStyle = setCellStyle(cell,field,mapCellStyle);
		}else{
			cell.setCellStyle(cellStyle);
		}
		field.setAccessible(true);
		if(excelCell.height() > 0 ){
			row.setHeightInPoints((float)excelCell.height());
		}
		Object value;
		try {
			value = field.get(source);
			cell.setCellValue(value.toString());
			int spanColumn = calcSpanColumn(source, field) - 1;
			int spanRow = calcSpanRow(source, field) - 1;
			if(spanColumn != 0 || spanRow != 0) {
				CellRangeAddress cra = new CellRangeAddress(postionFormat[1], postionFormat[1] + spanRow, postionFormat[0], postionFormat[0] + spanColumn);
				if(cellStyle != null) {
					if(cellStyle != null && !BorderStyle.NONE.equals(cellStyle.getBorderBottomEnum())){
						RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet); // 下边框
						RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框
						RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 有边框
						RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet); // 上边框
					}
				}

				sheet.addMergedRegion(cra);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private static void doWriteCell(Object source, Sheet sheet, Field field, ExcelCell excelCell,
									String position, Map<String,CellStyle> mapCellStyle, CellStyle cellStyle){
		if(StringUtil.isEmpty(position)){
			return;
		}
		//如果不为空,就写入标题
		int[] postionFormat = ExcelUtil.splitPosition(position);

		if(postionFormat[0] == -1 || postionFormat[1] == -1){
			return;
		}
		Row row = ExcelUtil.getOrCreateRow(sheet,postionFormat[1]);
		if(excelCell.height() > 0 ){
			row.setHeightInPoints((float)excelCell.height());
		}

		int spanColumn = calcSpanColumn(source, field) - 1;
		int spanRow = calcSpanRow(source, field) - 1;
		field.setAccessible(true);
		Object value = null;
		try {
			value = field.get(source);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage());
			return;
		}
		if (value == null) {
			return;
		}

		CellStyle fieldCellStyle = getFileCellStyle(field,mapCellStyle);

		if(fieldCellStyle != null){
			cellStyle = fieldCellStyle;
		}

		Object[] valueArr = null;
		if (field.getType().isArray()) {
			valueArr = (Object[]) value;
		} else if (Collection.class.isAssignableFrom(field.getType())){
			valueArr = ((Collection) value).toArray(new Object[]{});
		} else{
			valueArr = new Object[]{value};
		}
		int rowIndex = postionFormat[1];
		int colIndex = postionFormat[0];
		for (Object writeValue : valueArr) {
			if (writeValue == null) {
				writeValue = "";
			}

			Cell cell = ExcelUtil.getOrCreateCell(row, colIndex);
			if(cellStyle == null){
				cellStyle = setCellStyle(cell,field,mapCellStyle);
			}else{
				cell.setCellStyle(cellStyle);
			}
			if (spanRow != 0 && spanColumn != 0) {
				CellRangeAddress cra = new CellRangeAddress(rowIndex, rowIndex + spanRow, colIndex, colIndex + spanColumn);
				if (cellStyle != null) {
					if (cellStyle != null && !BorderStyle.NONE.equals(cellStyle.getBorderBottomEnum())) {
						RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet); // 下边框
						RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框
						RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 有边框
						RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet); // 上边框
					}
				}
				sheet.addMergedRegion(cra);
				colIndex += spanColumn;
			}else {
				colIndex += 1;
			}

			if (writeValue instanceof Date) {
				String dateFormat = excelCell.dateFormat();
				if (StringUtil.isNotEmpty(dateFormat)) {
					cell.setCellValue(DateUtil.getDateFormat((Date) writeValue, dateFormat));
				}else {
					cell.setCellValue(DateUtil.getDateTimeFormat((Date) writeValue));
				}
			}else {
				cell.setCellValue(String.valueOf(writeValue));
			}
		}
	}

	private static void writeField(Object source, Map<String,CellStyle> mapCellStyle,Workbook  workbook,Sheet sheet, Field field,String parentPosition,CellStyle cellStyle){

		ExcelTitle excelTitle = AnnotationUtils.findAnnotation(field, ExcelTitle.class);
		if(excelTitle != null) {
			// 写标题
			writeTitle(source, sheet, field, excelTitle, mapCellStyle);
			return;
		}

		ExcelRow excelRow = AnnotationUtils.findAnnotation(field, ExcelRow.class);
		if(excelRow != null) {
			String position = excelRow.position();
			position = ExcelUtil.mergePosition(position,parentPosition);
			writeRow(source, workbook, sheet, field,excelRow, position, mapCellStyle,cellStyle);
			return;
		}

		ExcelCell excelCell = AnnotationUtils.findAnnotation(field, ExcelCell.class);
		if(excelCell != null) {
			String position = excelCell.position();
			position = ExcelUtil.mergePosition(position,parentPosition);
//			writeCell(source, sheet, field, excelCell,position, mapCellStyle,cellStyle);
			doWriteCell(source, sheet, field, excelCell,position, mapCellStyle,cellStyle);
			return;
		}
	}

	public static Workbook exportXlsx(Object source){
		Workbook workbook = getWorkbook(source);
		if(workbook == null) {
			workbook = createXlxs();
		}
		return export(workbook,source);
	}

	public static Workbook exportXlsx(XSSFWorkbook workbook,Object source){
		return export(workbook,source);
	}

	/**
	 * @description 导出报表
	 * @author xhz
	 * @date 2017年8月9日 下午9:46:40
	 * @param source
	 * @return
	 * @lastModifier
	 */
	public static Workbook exportXls(Object source){
		Workbook workbook = getWorkbook(source);
		if(workbook == null) {
			workbook = createXls();
		}
		return export(workbook,source);
	}

	public static Workbook exportXls(HSSFWorkbook workbook,Object source){
		return export(workbook,source);
	}

	public static HSSFWorkbook createXls(){
		return new HSSFWorkbook();
	}

	public static XSSFWorkbook createXlxs(){
		return new XSSFWorkbook();
	}

	private static Workbook getWorkbook(Object source){
		if(source == null) return null;
		Class<?> clazz = source.getClass();
		ExcelTemplate excelTemplate = AnnotationUtils.findAnnotation(clazz, ExcelTemplate.class);
		if(excelTemplate == null){
			return null;
		}
		boolean isXlsx = false;
		if(excelTemplate.value().matches("^.*\\.xlsx$")) {
			isXlsx = true;
		}
		Workbook workbook = null;
		InputStream is = null;
		try {
			if(excelTemplate.value().matches("classpath:.*")){
				is =  clazz.getResourceAsStream(excelTemplate.value().replace("classpath:", ""));
				if(is != null){
					if(isXlsx) {
						workbook = new XSSFWorkbook(is);
					}else {
						workbook = new HSSFWorkbook(is);
					}
					return workbook;
				}
			}else{
				is = new FileInputStream(excelTemplate.value());
				if(isXlsx) {
					workbook = new XSSFWorkbook(is);
				}else {
					workbook = new HSSFWorkbook(is);
				}
				return workbook;

			}
		} catch (Exception e) {
			return null;
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Workbook export(Workbook workbook ,Object source){
		if(source == null) return null;

		Class<?> clazz = source.getClass();
		String sheetName = "Sheet1";
		// 默认操作第一工作簿
		if (workbook.getNumberOfSheets() >= 1) {
			sheetName = workbook.getSheetName(0);
		}

		//获取sheetName
		Excel excel = AnnotationUtils.findAnnotation(clazz, Excel.class);

		if(excel != null){
			sheetName = excel.sheet();
		}

		List<Field> liField = BeanUtil.getAllField(clazz);
		for(Field field : liField){
			ExcelSheet excelSheet = AnnotationUtils.findAnnotation(field, ExcelSheet.class);
			if(excelSheet == null) {
				continue;
			}
			try {
				sheetName = excelSheet.value();
				field.setAccessible(true);
				Object tmpName = field.get(source);
				if(tmpName != null){
					sheetName = tmpName.toString();
				}
			} catch (Exception e) {
			}
			break;
		}

		try{
			Map<String,CellStyle> mapCellStyle = new HashMap<>();
			createCellStyle(workbook, source,mapCellStyle);

			Field rowField = null;
			for(Field field : liField){
				try{
					ExcelRow excelRow = AnnotationUtils.findAnnotation(field, ExcelRow.class);
					if(excelRow != null) {
						rowField = field;
						break;
					}
				}catch(Exception e){
				}
			}

			if(rowField == null) {
				// 导出单个
				export(workbook, sheetName, 0, liField, source, mapCellStyle);
			} else {
				rowField.setAccessible(true);
				Object olist = rowField.get(source);
				if(olist == null || !(olist instanceof List) || ((List)olist).size() <= pageSize) {
					export(workbook, sheetName, 0, liField, source, mapCellStyle);
				} else {
					// 导出多个sheet

					List list = (List)olist;
					int start = 0;
					int index = 0;
					while(start < list.size()) {
						List tmp = ListUtil.limit(list, start, pageSize);
						rowField.set(source, tmp);

						export(workbook, sheetName + "_" + start + "-" + ((start + tmp.size()) - 1), index, liField, source, mapCellStyle);

						start += pageSize;
						tmp.clear();
						index++;
					}

					rowField.set(source, list);
				}
			}

			return workbook;
		}catch(Exception e){
		}

		return null;
	}

	public static void export(Workbook workbook,String sheetName,int sheetIndex, List<Field> liField,Object source,Map<String,CellStyle> mapCellStyle) {
		Sheet sheet = null;
		if (workbook.getNumberOfSheets() > 0 && sheetIndex == 0) {
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}
		}else if(workbook.getNumberOfSheets() == sheetIndex){
			sheet = workbook.createSheet(sheetName);
		} else {
			sheet = workbook.getSheetAt(sheetIndex);
			workbook.setSheetName(sheetIndex, sheetName);
		}

		for(Field field : liField){
			try{
				writeField(source, mapCellStyle, workbook,sheet, field, "",null);
			}catch(Exception e){
			}
		}
	}


	public static Workbook exportByTemplate(Object source,String path){
		if(source == null || !path.matches("^.*\\.xlsx?$")) return null;
		boolean isXlsx = false;
		if(path.matches("^.*\\.xlsx$")) {
			isXlsx = true;
		}
		Workbook workbook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			if(isXlsx) {
				workbook = new XSSFWorkbook(is);
			}else {
				workbook = new HSSFWorkbook(is);
			}
			return export(workbook, source);
		} catch (Exception e) {
			return null;
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String saveAsFileAndClose(Workbook workbook,String path) {
		if(workbook == null || !path.matches("^.*\\.xlsx?$")) return "";
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
			workbook.close();
			return path;
		} catch (Exception e) {
		}
		return "";

	}

	public static ByteArrayOutputStream saveAsOutputStreamAndClose(Workbook workbook) {
		if(workbook == null) return null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);
			workbook.close();
			return os;
		} catch (Exception e) {
		}
		return null;
	}

	public static ByteArrayInputStream saveAsInputStreamAndClose(Workbook workbook) {
		if(workbook == null) return null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			workbook.write(os);
			workbook.close();
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			os.close();
			return is;
		} catch (Exception e) {
		}
		return null;
	}

	public static void exportToWebAndClose(HttpServletRequest request,HttpServletResponse response,Workbook workbook,String fileName) {
		OutputStream outputStream = null;
		try {
			response.reset();
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes(StandardCharsets.UTF_8); // name.getBytes("UTF-8")处理safari的乱码问题
			fileName = new String(bytes , StandardCharsets.ISO_8859_1); // 各浏览器基本都支持ISO编码
			response.setHeader("Content-disposition" ,String.format("attachment;filename=\"%s\"" ,fileName)); // 文件名外的双引号处理firefox的空格截断问题
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.setContentType("application/msexcel;charset=utf-8");

			//写入byte
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		}catch (Exception e) {
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
			if(workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
