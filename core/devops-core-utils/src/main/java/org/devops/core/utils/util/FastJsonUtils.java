package org.devops.core.utils.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * fastjson工具类
 */
public class FastJsonUtils {

    private static Logger logger = LoggerFactory.getLogger(FastJsonUtils.class);

    /**
     * 功能描述：把JSON数据转换成普通字符串列表
     *
     * @param jsonData JSON数据
     * @return
     * @throws Exception
     */
    public static List<String> getStringList(String jsonData) throws Exception {
        return JSON.parseArray(jsonData, String.class);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return
     */
    public static <T> T getBean(String jsonData, Class<T> clazz) {
        try {
            if (jsonData == null || jsonData.equals("")) {
                return null;
            }
            return JSON.parseObject(jsonData, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(String jsonData, TypeReference<T> type) {
        try {
            if (jsonData == null || jsonData.equals("")) {
                return null;
            }
            return JSON.parseObject(jsonData, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param jsonData
     * @param field
     * @param clazz
     * @return
     * @description 功能描述：把JSON数据里面的某个字段转换成指定的java对象
     * @author xhz
     * @date 2017年7月10日 下午6:14:40
     * @lastModifier
     */
    public static <T> T getBean(String jsonData, String field, Class<T> clazz) {
        return getBean(getString(jsonData, field), clazz);
    }

    public static Object getBean(String jsonData, Type type) {
        try {
            if (jsonData == null || jsonData.equals("")) {
                return null;
            }
            return JSON.parseObject(jsonData, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将object转换成map
     *
     * @param o
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMap(Object o) {
        return JSONObject.toJavaObject(JSONObject.parseObject(JSON.toJSONString(o)), Map.class);
    }

    public static Map<String, Object> getMap(String str) {
        return JSONObject.toJavaObject(JSONObject.parseObject(str), Map.class);
    }

    /**
     * @param jsonData
     * @param field
     * @return
     * @description
     * @author xhz
     * @date 2017年7月10日 下午6:10:44
     * @lastModifier
     */
    @SuppressWarnings("rawtypes")
    public static String getString(String jsonData, String field) {
        Map mapBean = getBean(jsonData, Map.class);
        if (mapBean == null) {
            return "";
        }
        Object o = mapBean.get(field);
        if (o == null) {
            return "";
        }
        return toJsonString(o);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return
     * @throws Exception
     */
    public static <T> List<T> getBeanList(String jsonData, Class<T> clazz) {
        try {
            return JSON.parseArray(jsonData, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的java对象列表
     *
     * @param jsonData JSON数据
     * @return
     */
    public static List<Map<String, Object>> getBeanMapList(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 功能描述：把javaBean 转换成字符串
     *
     * @param obj
     * @return
     * @author zhangxh
     */
    public static String toJsonString(Object obj) {
        if (obj == null)
            return "";
        return JSON.toJSONString(obj);
    }


    /**
     * 功能描述：把javaBean 转换成字符串
     *
     * @param obj
     * @return
     * @author zhangxh
     */
    public static String toJsonString(Object obj, SerializerFeature... features) {
        if (obj == null)
            return "";
        return JSON.toJSONString(obj, features);
    }

    /**
     * 功能描述：把javaBean 转换成字符串
     *
     * @param obj
     * @return
     * @author zhangxh
     */
    public static String toJsonStringSingleQuotes(Object obj) {
        if (obj == null)
            return "";
        return JSON.toJSONString(obj, SerializerFeature.UseSingleQuotes);
    }

    public static String toJsonStringDisableCircle(Object obj) {
        if (obj == null)
            return "";
        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 将java 字符串转化为指定类型的java List对象
     *
     * @param str
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonStringToBeanList(String str, Class<T> clazz) {
        try {
            Object obj = JSON.parse(str);
            return JSON.parseArray(obj.toString(), clazz);
        } catch (Exception ex) {
            logger.error("转换异常！", ex);
            return JSON.parseArray(str, clazz);
        }
    }

}
