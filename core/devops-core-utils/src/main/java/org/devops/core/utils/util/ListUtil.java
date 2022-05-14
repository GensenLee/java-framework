package org.devops.core.utils.util;

import com.google.common.math.IntMath;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class ListUtil {

    /**
     * @param list
     * @return
     * @throws
     * @Title: getCurveValue
     * @date 2016年8月16日 下午4:48:33
     * @Description: 计算集合中的最大值和最小值，
     * 返回改后的最大值，间隔，最小值
     */
    public static List<Long> getCurveValue(List<Long> list) {
        List<Long> curveList = new ArrayList<Long>();

        Long maxValue = 0L;//集合中最大值
        Long avgValue = 0L;//间隔值
        Long minValue = 0L;//最小值

        if (ListUtil.isNotNull(list)) {
            maxValue = Collections.max(list) / 100;
        }

        if (maxValue > 10000) {
            maxValue += 1000;
        } else if (maxValue > 1000) {
            maxValue += 300;
        } else if (maxValue > 100) {
            maxValue += 50;
        } else if (maxValue > 10) {
            maxValue += 5;
        } else {
            maxValue = 10L;
        }

        avgValue = maxValue / 5;

        curveList.add(maxValue);
        curveList.add(avgValue);
        curveList.add(minValue);

        return curveList;
    }


    /**
     * 判断List不为空,非空返回true,空则返回false
     *
     * @param list
     * @return boolean
     */
    public static boolean isNotNull(List<?> list) {
        if (null != list) {
            return list.size() > 0;
        }
        return false;
    }

    /**
     * 判断List是为空,为空返回true,非空则返回false
     *
     * @param list
     * @return boolean
     */
    public static boolean isNull(List<?> list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param list
     * @return
     * @Description: 去除集合中重复的内容
     */
    public static <T> List<T> removeDuplicate(List<T> list) {
        if (list != null && list.size() > 0) {
            HashSet<T> hashSet = new HashSet<>(list);
            list.clear();
            list.addAll(hashSet);
        }
        return list;
    }

    /**
     * @param list
     * @return
     */
    public static List<String> toStringList(List<Long> list) {
        List<String> result = new ArrayList<String>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        for (Long item : list) {
            if (item == null) {
                continue;
            }
            result.add(item + "");
        }
        return result;
    }

    /**
     * 遍历列表抽取字段
     *
     * @param list
     * @param field
     * @param <T>
     * @return
     */
    public static <T> List<String> toStringList(List<T> list, String field) {
        List<String> result = new ArrayList<String>();
        if (list == null || list.isEmpty()) {
            return result;
        }
        Class<?> clazz = list.get(0).getClass();
        for (T o : list) {
            try {
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field, clazz);
                } catch (Exception e) {
                    pd = new PropertyDescriptor(StringUtil.toLHCase(field), clazz);
                }
                Method method = pd.getReadMethod();
                Object value = method.invoke(o);
                if (value != null) {
                    result.add(value.toString());
                }
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 转换为int数组
     * @author zhangxh
     * @date 2016-8-11 下午10:00:05
     * @lastModifier
     */
    public static <T> List<Integer> toIntegerList(List<T> list, String field) {
        List<Integer> result = new ArrayList<Integer>();
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
                result.addAll(toIntegerList(value.toString()));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * @param list
     * @param field
     * @return
     * @Description 转换为int数组  兼容 下划线和驼峰
     * @author zhangxh
     * @date 2016-8-11 下午10:00:05
     * @lastModifier
     */
    public static <T> List<Long> toLongList(List<T> list, String field) {
        List<Long> result = new ArrayList<Long>();
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
                result.addAll(toLongList(value.toString()));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * @param list
     * @return
     * @Description 转换为long数组
     * @author zhangxh
     * @date 2016-8-12 上午10:31:04
     * @lastModifier
     */
    public static List<Long> toLongList(List<String> list) {
        List<Long> result = new ArrayList<Long>();
        for (String id : list) {
            result.add(LongUtil.toLong(id));
        }
        return result;
    }

    /**
     * @param list
     * @return
     * @Description 转换为int数组
     * @author zhangxh
     * @date 2016-8-12 上午10:30:59
     * @lastModifier
     */
    public static List<Integer> toIntegerList(String list) {
        return toList(list, Integer.class);
    }

    /**
     * @param ids
     * @return
     * @Description 转换为long数组
     * @author zhangxh
     * @date 2016-8-12 上午10:30:59
     * @lastModifier
     */
    public static List<Long> toLongList(String ids) {
        return toList(ids, Long.class);
    }

    /**
     * @param ids
     * @return
     * @Description 转换为数组
     * @author zhangxh
     * @date 2016-8-12 上午10:30:59
     * @lastModifier
     */
    public static <T> List<T> toList(String ids, Class<T> clazz) {
        if (ids == null) {
            return new ArrayList<T>();
        }
        return toList(ids.split(","), clazz);
    }

    /**
     * @param ids
     * @return
     * @Description
     * @author zhangxh
     * @date 2016-8-12 上午10:31:02
     * @lastModifier
     */
    public static List<Integer> toIntegerList(String[] ids) {
        return ListUtil.toList(ids, Integer.class);
    }

    /**
     * @param ids
     * @return
     * @Description
     * @author zhangxh
     * @date 2016-8-12 上午10:31:02
     * @lastModifier
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(String[] ids, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        Method method;
        try {
            method = clazz.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException e) {
            return result;
        }
        try {
            for (String id : ids) {
                if (id == null) {
                    result.add((T) method.invoke(null, "0"));
                }
                result.add((T) method.invoke(null, id));
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * @param ids
     * @return
     * @Description 转换为int数组
     * @author zhangxh
     * @date 2016-8-12 上午10:31:04
     * @lastModifier
     */
    public static List<Integer> toIntegerList(List<String> ids) {
        List<Integer> result = new ArrayList<Integer>();
        for (String id : ids) {
            result.add(IntUtil.toInt(id));
        }
        return result;
    }

    /**
     * Set转成List
     */
    public static <T> List<T> toList(Set<T> idSet) {
        return new ArrayList<T>(idSet);
    }

    /**
     * 把数组拼接成字符串
     *
     * @param strs
     * @param split - 分隔符
     * @return
     */
    public static String join(String[] strs, String split) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            sb.append(split + strs[i]);
        }
        return sb.toString();
    }

    /**
     * 把数组拼接成字符串
     *
     * @param list
     * @param split - 分隔符
     * @return
     */
    public static String join(List<String> list, String split) {
        return join(list.toArray(), split);
    }


    /**
     * 把数组拼接成字符串
     *
     * @param arr
     * @param split - 分隔符
     * @return
     */
    public static String join(Object[] arr, String split) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                result.append(arr[i].toString()).append(split);
            } else {
                result.append(arr[i].toString());
            }
        }
        return result.toString();
    }

    /**
     * 把数组拼接成字符串
     *
     * @param arr
     * @return
     */
    public static String join(Object[] arr) {
        return join(arr, ",");
    }

    /**
     * 把数组拼接成字符串
     *
     * @param list
     * @return
     */
    public static String join(List<String> list) {
        return join(list.toArray());
    }

    /**
     * 增加前后符号
     * @param list
     * @param open
     * @param close
     * @return
     */
    public static List<String> replaceKeys(List<String> list, String open, String close) {
        List<String> liResult = new ArrayList<String>(list.size());
        for (String key : list) {
            liResult.add(open + key + close);
        }
        return liResult;
    }


    @SuppressWarnings("rawtypes")
    public static int size(List list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /**
     * 截取
     * @param list
     * @param start
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> limit(List<T> list, int start, int size) {
        List<T> result = new ArrayList<T>(0);

        if (list == null) {
            return result;
        }
        int last = start + size;
        if (list.size() < last) {
            last = list.size();
        }
        for (int i = start; i < last; i++) {
            result.add(list.get(i));
        }
        return result;

    }

    /**
     * 过滤list中的f
     * @param list
     * @param f
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, T f) {
        List<T> result = new ArrayList<T>();
        if (list == null) {
            return result;
        }
        for (T t : list) {
            if (t.equals(f)) {
                continue;
            }
            result.add(t);
        }
        return result;
    }

    /**
     * 获取list中fieldName字段值等于value的一项
     * @param list
     * @param fieldName
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T filterOne(List<T> list, String fieldName, Object value) {
        List<T> result = filter(list, fieldName, value);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    /**
     * 只保留list中fieldName字段值等于value的项
     * @param list
     * @param fieldName
     * @param value
     * @param <T>
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> filter(List<T> list, String fieldName, Object value) {
        List<T> result = new ArrayList<T>();
        if (list == null) {
            return result;
        }
        for (T t : list) {
            Field field = BeanUtil.getField(t.getClass(), fieldName);
            if (field == null) {
                continue;
            }
            field.setAccessible(true);
            Object v = null;
            try {
                v = field.get(t);
            } catch (Exception e) {
            }finally {
                field.setAccessible(false);
            }
            if (v == null) {
                continue;
            }
            if (value instanceof List) {
                List liValue = (List) value;
                if (!liValue.contains(v)) {
                    continue;
                }
            } else {
                if (!v.equals(value)) {
                    continue;
                }
            }
            result.add(t);
        }
        return result;
    }

    /**
     * 取差集
     *
     * @param source
     * @param list
     * @return
     */
    public static <T> List<T> differenceSet(List<T> source, List<T> list) {
        List<T> result = new ArrayList<T>(0);
        for (T t : source) {
            if (list.contains(t)) {
                continue;
            }
            result.add(t);
        }
        return result;
    }

    public static <T> List<T> get() {
        return new ArrayList<T>();
    }

    public static <T> List<T> desc(List<T> list, String field) {
        return ListUtil.sort(list, field, "DESC");
    }

    public static <T> List<T> sort(List<T> list, String fieldName, String type) {
        list.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field field;
                try {
                    field = BeanUtil.getField(o1.getClass(), fieldName);
                    if (field == null) {
                        return 0;
                    }
                    field.setAccessible(true);
                    Object o1Value = field.get(o1);
                    Object o2Value = field.get(o2);
                    if (o1Value == null || o2Value == null) {
                        return 0;
                    }
                    Method method = o1Value.getClass().getDeclaredMethod("compareTo", o1Value.getClass());
                    int r = (int) method.invoke(o1Value, o2Value);

                    if ("DESC".equalsIgnoreCase(type)) {
                        if (r < 0) {
                            r = 1;
                        } else if (r > 0) {
                            r = -1;
                        }
                    }
                    return r;
                } catch (Exception e) {
                    log.error("[Exception 出错啦!]", e);
                }
                return 0;
            }
        });
        return list;
    }

    /**
     * 数组分割，分段
     * @param list
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        checkNotNull(list);
        checkArgument(size > 0);
        return (list instanceof RandomAccess)
                ? new RandomAccessPartition<T>(list, size)
                : new Partition<T>(list, size);
    }

    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        RandomAccessPartition(List<T> list, int size) {
            super(list, size);
        }
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        final List<T> list;
        final int size;

        Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            checkElementIndex(index, size());
            int start = index * size;
            int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }

        @Override
        public int size() {
            return IntMath.divide(list.size(), size, RoundingMode.CEILING);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}
