package org.devops.core.utils.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxh
 * @Description 和map相关的工具类
 * @date 2016-8-11 下午9:04:12
 */
public class MapUtil {
    /**
     * @param list
     * @param field
     * @return
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @lastModifier
     */
    @SuppressWarnings("unchecked")
    public static <T, T2> Map<T2, T> toMap(List<T> list, String field, Class<T2> cla) {
        Map<T2, T> result = new HashMap<T2, T>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        for (T o : list) {
            if (o == null) continue;
            Class<?> clazz = o.getClass();
            try {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field, clazz);
                } catch (Exception e) {
                    pd = new PropertyDescriptor(StringUtil.toLHCase(field), clazz);
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                if (cla.equals(String.class)) {
                    if (value != null) {
                        result.put((T2) value.toString(), o);
                    }
                } else {
                    Method valueOf = cla.getMethod("valueOf", String.class);
                    if (value == null) {
                        result.put((T2) valueOf.invoke(null, "0"), o);
                    } else {
                        result.put((T2) valueOf.invoke(null, value.toString()), o);
                    }
                }

            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * @param list
     * @param field
     * @return
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @lastModifier
     */
    public static <T> Map<Long, T> toMap(List<T> list, String field) {
        return toMap(list, field, Long.class);
    }

    public static <T> Map<Integer, T> toMapInteger(List<T> list, String field) {
        return toMap(list, field, Integer.class);
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 将list转换为map
     * @author zhangxh
     * @date 2016-8-11 下午9:28:05
     * @lastModifier
     */
    public static <T> Map<Long, T> toMapLong(List<T> list, String field) {
        return toMap(list, field, Long.class);
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 将list转换为map
     * @author chensongming
     * @date 2017年8月3日 下午4:39:34
     * @lastModifier
     */
    public static <T> Map<String, T> toMapString(List<T> list, String field) {
        return toMap(list, field, String.class);
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 将List 分组
     * @author xhz
     * @date 2016-9-1 下午2:42:51
     * @lastModifier
     */
    public static <T> Map<Integer, List<T>> toMapList(List<T> list, String field) {
        Map<Integer, List<T>> result = new HashMap<Integer, List<T>>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        for (T o : list) {
            Class<?> clazz = o.getClass();
            try {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field, clazz);
                } catch (Exception e) {
                    pd = new PropertyDescriptor(StringUtil.toLHCase(field), clazz);
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if (listValue == null) {
                    listValue = new ArrayList<T>();
                    result.put(IntUtil.toInt(value), listValue);
                }
                listValue.add(o);
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 将list分组
     * @author xhz
     * @date 2016-9-1 下午2:43:08
     * @lastModifier
     */
    public static <T> Map<String, List<T>> groupByString(List<T> list, String field) {
        Map<String, List<T>> result = new HashMap<String, List<T>>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        for (T o : list) {
            Class<?> clazz = o.getClass();
            try {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field, clazz);
                } catch (Exception e) {
                    pd = new PropertyDescriptor(StringUtil.toLHCase(field), clazz);
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if (listValue == null) {
                    listValue = new ArrayList<T>();
                    result.put(value == null ? "" : value.toString(), listValue);
                }
                listValue.add(o);
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * @param map
     * @param key
     * @return
     * @Description
     * @author xhz
     * @date 2016-9-1 下午3:08:25
     * @lastModifier
     */
    public static <T1, T2> List<T2> getList(Map<T1, List<T2>> map, T1 key) {
        List<T2> result = map.get(key);
        if (result == null) {
            result = new ArrayList<T2>();
        }
        return result;
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description
     * @author aikq
     * @date 2017年6月6日 上午9:32:41
     * @lastModifier
     */
    public static <T> Map<Long, List<T>> groupByLong(List<T> list, String field) {
        Map<Long, List<T>> result = new HashMap<Long, List<T>>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        for (T o : list) {
            Class<?> clazz = o.getClass();
            try {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field, clazz);
                } catch (Exception e) {
                    pd = new PropertyDescriptor(StringUtil.toLHCase(field), clazz);
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                List<T> listValue = result.get(value);
                if (listValue == null) {
                    listValue = new ArrayList<T>();
                    result.put(LongUtil.toLong(value), listValue);
                }
                listValue.add(o);
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 对象转为map
     *
     * @param source
     * @param includeFields
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMapResultIncludeFields(Object source, String... includeFields) {
        if (source == null || source instanceof List) {
            return null;
        }
        Map<String, Object> tmpMap = FastJsonUtils.getBean(FastJsonUtils.toJsonString(source), Map.class);
        Map<String, Object> result = new HashMap<>();
        if (includeFields != null && tmpMap != null) {
            for (String key : includeFields) {
                if (tmpMap.containsKey(key)) {
                    result.put(key, tmpMap.get(key));
                }
            }
        }
        return result;
    }


    /**
     * 对象转为map
     *
     * @param source
     * @param excludeFields
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMapResultExcludeFields(Object source, String... excludeFields) {
        if (source == null || source instanceof List) {
            return null;
        }
        Map<String, Object> result = FastJsonUtils.getBean(FastJsonUtils.toJsonString(source), Map.class);
        if (excludeFields != null && result != null) {
            for (String key : excludeFields) {
                result.remove(key);
            }
        }
        return result;
    }

    /**
     * 对象list转为map list
     *
     * @param source
     * @param includeFields
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Map<String, Object>> toMapListResultIncludeFields(List source, String... includeFields) {
        if (source == null) {
            return null;
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Object o : source) {
            result.add(toMapResultIncludeFields(o, includeFields));
        }
        return result;
    }

    /**
     * 对象list转为map list
     *
     * @param source
     * @param excludeFields
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Map<String, Object>> toMapListResultExcludeFields(List source, String... excludeFields) {
        if (source == null) {
            return null;
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Object o : source) {
            result.add(toMapResultExcludeFields(o, excludeFields));
        }
        return result;
    }
}
