package org.devops.core.utils.util;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GENSEN
 * @date 2020/10/30 15:19
 * @description：csv工具
 */
@Slf4j
public class CsvUtil {


    private static String preprocessContent(String cellContent) {
        if (cellContent == null) {
            return "";
        }
        cellContent = cellContent.trim();
        cellContent = cellContent.replaceAll("\\ufeff", "");
        cellContent = cellContent.replace("\n", "");
        cellContent = cellContent.replace("\r", "");
//        cellContent = cellContent.replace(String.valueOf('\001'), ",");
        cellContent = new String(cellContent.getBytes(), StandardCharsets.UTF_8);
        return cellContent;
    }

    /**
     * @param content
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> read(String content, Class<T> clazz){
        return read(content, clazz, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER);
    }

    /**
     * opencsv版本
     *
     * @param content
     * @param clazz
     * @param separator
     * @param quotechar
     * @param <T>
     * @return
     */
    public static <T> List<T> read(String content, Class<T> clazz, char separator, char quotechar) {
        CSVReader csvReader = new CSVReader(new StringReader(content), separator, quotechar);
        List<String[]> arrList = null;
        try {
            arrList = csvReader.readAll();
        } catch (IOException e) {
            log.error("[error] " + e.getMessage());
        } finally {
            try {
                csvReader.close();
            } catch (IOException ignored) {
            }
        }
        if (ListUtil.isNull(arrList)) {
            return null;
        }
        String[] namesArr = arrList.remove(0);
        if (arrList.isEmpty()) {
            return null;
        }
        JSONArray names = new JSONArray(namesArr);

        List<T> list = new ArrayList<>();
        for (String[] arr : arrList) {
            list.add(JSON.parseObject(new JSONArray(arr).toJSONObject(names).toString(), clazz));
        }
        return list;
    }

    public static ByteArrayInputStream write(List<?> list) {
        return write(list, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER);
    }


    /**
     * @param list
     * @param separator
     * @param quotechar
     * @return
     */
    public static ByteArrayInputStream write(List<?> list, char separator, char quotechar) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (ListUtil.isNull(list)) {
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
        Class<?> clazz = list.get(0).getClass();
        List<Field> fieldList = BeanUtil.getAllField(clazz);
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream), separator, quotechar);
        int fieldCount = fieldList.size();
        String[] titleArr = new String[fieldCount];
        for (Field field : fieldList) {
            titleArr[fieldList.indexOf(field)] = field.getName();
        }
        csvWriter.writeNext(titleArr);
        for (Object o : list) {
            String[] valueArr = new String[fieldCount];
            for (Field field : fieldList) {
                Object objectValue = null;
                field.setAccessible(true);
                try {
                    objectValue = field.get(o);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccessException", e);
                } finally {
                    field.setAccessible(false);
                }
                if (objectValue == null) {
                    continue;
                }
                String value;
                if (field.getType() == Date.class) {
                    value = DateUtil.getDateTimeFormat((Date) objectValue);
                } else {
                    value = objectValue.toString();
                }
                valueArr[fieldList.indexOf(field)] = preprocessContent(value);
            }
            csvWriter.writeNext(valueArr);
        }
        try {
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            log.error("org.devops.core.utils.util.CsvUtil.write(java.util.List<?>, char, char)",e);
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
