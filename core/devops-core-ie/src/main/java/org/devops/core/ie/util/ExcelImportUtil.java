package org.devops.core.ie.util;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.devops.core.ie.annotation.Excel;
import org.devops.core.ie.annotation.ExcelCell;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 导入工具
 *
 * @author xhz
 */
@Slf4j
public class ExcelImportUtil {

    public static <T> List<T> parseList(String path, Class<T> clazz) {
        return parseList(path, clazz, 0, 1);
    }

    public static <T> List<T> parseList(String path, Class<T> clazz, int titleRowIndex, int contentStartIndex) {
        if (!path.matches("^.*\\.xlsx?$")) return null;
        boolean isXlsx = false;
        if (path.matches("^.*\\.xlsx$")) {
            isXlsx = true;
        }
        Workbook workbook = null;
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            if (isXlsx) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
            return parseList(workbook, clazz, titleRowIndex, contentStartIndex);
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                    workbook.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static <T> List<T> parseListByHttp(String url, Class<T> clazz) {
        return parseListByHttp(url, clazz, 0, 1);
    }

    public static <T> List<T> parseListByHttp(String url, Class<T> clazz, int titleRowIndex, int contentStartIndex) {
        if (!url.matches("^.*\\.xlsx?$")) return null;
        boolean isXlsx = false;
        if (url.matches("^.*\\.xlsx$")) {
            isXlsx = true;
        }
        Workbook workbook = null;
        InputStream is = null;
        try {
            is = HttpUtils.download(url);
            if (isXlsx) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
            return parseList(workbook, clazz, titleRowIndex, contentStartIndex);
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                    workbook.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static <T> List<T> parseList(InputStream is, String fileName, Class<T> clazz) {
        return parseList(is, fileName, clazz, 0, 1);
    }

    public static <T> List<T> parseList(InputStream is, String fileName, Class<T> clazz, int titleRowIndex, int contentStartIndex) {
        if (!fileName.matches("^.*\\.xlsx?$")) return null;
        boolean isXlsx = false;
        if (fileName.matches("^.*\\.xlsx$")) {
            isXlsx = true;
        }
        Workbook workbook = null;
        try {
            if (isXlsx) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
            return parseList(workbook, clazz, titleRowIndex, contentStartIndex);
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static <T> List<T> parseList(Workbook workbook, Class<T> clazz) {
        return parseList(workbook, clazz, 0, 1);
    }

    public static <T> List<T> parseList(Workbook workbook, Class<T> clazz, int titleRowIndex, int contentStartIndex) {
        contentStartIndex = Math.max(contentStartIndex, 0);
        titleRowIndex = Math.max(titleRowIndex, 0);
        String sheetName = null;
        //获取sheetName
        Excel excel = AnnotationUtils.findAnnotation(clazz, Excel.class);
        if (excel != null) {
            sheetName = excel.sheet();
        }
        Sheet sheet;
        if (StringUtil.isEmpty(sheetName)) {
            sheet = workbook.getSheetAt(0);
        } else {
            sheet = workbook.getSheet(sheetName);
        }
        List<T> result = new ArrayList<>();
        if (sheet != null) {
            Row titleRow = sheet.getRow(titleRowIndex);
            for (int i = contentStartIndex; i <= sheet.getLastRowNum(); i++) {
                Row row = null;
                try {
                    row = sheet.getRow(i);
                } catch (Exception e) {
                }
                if (row == null) {
                    continue;
                }
                T rowData = parseRow(titleRow, row, clazz);
                if (rowData == null) {
                    break;
                }
                result.add(rowData);
            }

        }
        return result;
    }

    private static int getColumn(Row titleRow, String value) {
        for (int i = titleRow.getFirstCellNum(); i < titleRow.getLastCellNum(); i++) {
            Cell cell = ExcelUtil.getOrCreateCell(titleRow, i);
            try {
                if (cell.getStringCellValue().equals(value)) {
                    return i;
                }
            } catch (Exception e) {

            }
        }
        return -1;
    }

    public static <T> T parseRow(Row titleRow, Row row, Class<T> clazz) {
        if (Map.class.isAssignableFrom(clazz)) {
            return (T) parseRowToMap(titleRow, row, clazz);
        } else {
            return parseRowToBean(titleRow, row, clazz);
        }
    }

    private static <T> T parseRowToBean(Row titleRow, Row row, Class<T> clazz) {
        List<Field> liField = BeanUtil.getAllField(clazz);
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
        boolean hasValue = false;
        for (Field field : liField) {
            ExcelCell excelCell = AnnotationUtils.findAnnotation(field, ExcelCell.class);
            if (excelCell == null) {
                continue;
            }
            try {
                String position = excelCell.position();
                int[] arrPosition = ExcelUtil.splitPosition(position);
                int column = arrPosition[0];
                if (column == -1) {
                    String title = excelCell.title();
                    if (StringUtil.isEmpty(title)) {
                        continue;
                    }
                    column = getColumn(titleRow, title);
                    if (column == -1) {
                        continue;
                    }
                }
                if (Collection.class.isAssignableFrom(field.getType())) {
                    ParameterizedType genericType = (ParameterizedType)field.getGenericType();
                    Type[] actualTypeArguments = genericType.getActualTypeArguments();
                    Type actualTypeArgument = actualTypeArguments[0];
                    Collection collection;
                    if (Set.class.isAssignableFrom(field.getType())) {
                        collection = BeanUtils.instantiateClass(HashSet.class);
                    }else {
                        collection = BeanUtils.instantiateClass(ArrayList.class);
                    }
                    for (int i = column; i < row.getLastCellNum(); i++) {
                        Cell cell = ExcelUtil.getOrCreateCell(row, i);
                        Object v = ExcelUtil.convertCellValue(cell, ((Class<?>) actualTypeArgument));
                        if (StringUtil.isNotEmpty(v)) {
                            hasValue = true;
                            collection.add(v);
                        }else {
                            break;
                        }
                    }
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(),obj.getClass());
                        Method method = pd.getWriteMethod();
                        method.invoke(obj, collection);
                    }catch (Exception ignored) {
                    }
                }else {
                    Cell cell = ExcelUtil.getOrCreateCell(row, column);
                    Object v = ExcelUtil.readCell(cell, obj, field);
                    if (StringUtil.isNotEmpty(v)) {
                        hasValue = true;
                    }
                }
            } catch (Exception ignored) {
            }
        }
        if (!hasValue) {
            return null;
        }
        return obj;
    }

    private static <T> T parseRowToMap(Row titleRow, Row row, Class<T> clazz) {
        Map map = (Map) BeanUtils.instantiateClass(clazz);
        boolean hasValue = false;
        Iterator<Cell> titleIterator = titleRow.cellIterator();
        while (titleIterator.hasNext()) {
            Cell next = titleIterator.next();
            String title = next.getStringCellValue();
            String cellValue = null;
            Cell cell = row.getCell(next.getColumnIndex());
            if (cell != null) {
                cellValue = cell.getStringCellValue();
                if (cellValue != null && cellValue.length() > 0) {
                    hasValue = true;
                }
            }
            map.put(title, cellValue);
        }

        if (!hasValue) {
            return null;
        }
        return (T) map;
    }

}
