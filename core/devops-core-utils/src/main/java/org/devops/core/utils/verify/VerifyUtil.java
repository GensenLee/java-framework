package org.devops.core.utils.verify;

import org.apache.commons.collections.CollectionUtils;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.StringUtil;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xhz
 * 验证类
 */
public class VerifyUtil {

    /**
     * @param arr
     * @throws VerifyException
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:01
     * @lastModifier
     */
    public static void verify(Object[] arr)
            throws VerifyException {
        for (Object o : arr) {
            if (o == null) {
                throw new VerifyException("null");
            } else if (o instanceof String) {
                if (o.toString() == null || o.toString().equals("")) {
                    throw new VerifyException("null");
                }
            } else if (o instanceof Integer) {
                if (IntUtil.isZero(o)) {
                    throw new VerifyException("null");
                }
            }
        }
    }

    /**
     * @param vo
     * @throws VerifyException
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:20
     * @lastModifier
     */
    public static void verify(Object vo)
            throws VerifyException {
        verify(vo, new String[]{}, true);
    }

    /**
     * @param vo
     * @param fields
     * @throws VerifyException
     * @Description 验证值
     * @author xhz
     * @date 2017年5月23日 上午10:23:57
     * @lastModifier
     */
    @SuppressWarnings("rawtypes")
    public static void verify(Object vo, Field[] fields)
            throws VerifyException {
        for (Field field : fields) {
            try {
                VerifyField verifyField = AnnotationUtils.findAnnotation(field, VerifyField.class);
                if (verifyField != null && verifyField.ignore()) {
                    continue;
                }
                String msg = verifyField == null ? field.getName() : verifyField.value();
                field.setAccessible(true);
                Object value = field.get(vo);
                if (value == null) {
                    throw new VerifyException(field.getName(), msg + "不能为空");
                } else if (value instanceof String) {
                    if ("".equals(value.toString())) {
                        throw new VerifyException(field.getName(), msg + "不能为空");
                    }
                    if (verifyField != null && !"".equals(verifyField.regex())) {
                        if (!value.toString().matches(verifyField.regex())) {
                            throw new VerifyException(field.getName(), msg + "验证不通过");
                        }
                    }
                } else if (value instanceof Integer) {
                    if (Integer.parseInt(value.toString()) == 0 && (verifyField != null && verifyField.notZero())) {
                        throw new VerifyException(field.getName(), msg + "不能为空或0");
                    }
                } else if (value instanceof Long) {
                    if (Long.parseLong(value.toString()) == 0 && (verifyField != null && verifyField.notZero())) {
                        throw new VerifyException(field.getName(), msg + "不能为空或0");
                    }
                } else if (value instanceof Byte) {
                    if (Byte.parseByte(value.toString()) == 0 && (verifyField != null && verifyField.notZero())) {
                        throw new VerifyException(field.getName(), msg + "不能为空或0");
                    }
                } else if (value instanceof BigDecimal) {
                    if (BigDecimal.valueOf(Double.parseDouble(value.toString())).equals(BigDecimal.ZERO) && (verifyField != null && verifyField.notZero())) {
                        throw new VerifyException(field.getName(), msg + "不能为空或0");
                    }
                } else if (value instanceof List) {
                    if (((List) value).isEmpty()) {
                        throw new VerifyException(field.getName(), msg + "不能为空");
                    }
                } else if (value instanceof Map) {
                    if (((Map) value).isEmpty()) {
                        throw new VerifyException(field.getName(), msg + "不能为空");
                    }
                } else {
                    if ("".equals(value.toString())) {
                        throw new VerifyException(field.getName(), msg + "不能为空");
                    }
                }
            } catch (VerifyException ve) {
                throw ve;
            } catch (Exception e) {
                throw new VerifyException(field.getName());
            }
        }
    }

    public static void verify(Object vo, String fields)
            throws VerifyException {
        verify(vo, fields.split(","));
    }

    private static Field getField(String name, Class<?> clazz)
            throws NoSuchFieldException {
        String clazzName = (clazz == null ? "" : clazz.getName());
        while (clazz != null && !"java.lang.Object".equals(clazzName)) {
            try {
                return clazz.getDeclaredField(name);
            } catch (Exception e) {
                try {
                    return clazz.getDeclaredField(StringUtil.toLHCase(name));
                } catch (Exception ee) {

                }
            }
            clazz = clazz.getSuperclass();
            clazzName = (clazz == null ? "" : clazz.getName());
        }
        throw new NoSuchFieldException();
    }

    public static <T> void verifyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new VerifyException("list", "列表不能为空");
        }
        for (T o : list) {
            verify(o);
        }
    }

    public static <T> void verifyList(List<T> list, String... fields) {
        if (CollectionUtils.isEmpty(list)) {
            throw new VerifyException("list", "列表不能为空");
        }
        for (T o : list) {
            verify(o, fields);
        }
    }

    /**
     * @param vo
     * @param fields
     * @throws VerifyException
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:40
     * @lastModifier
     */
    public static void verify(Object vo, String... fields)
            throws VerifyException {
        if (vo == null) {
            throw new VerifyException("VO", "输入值为null");
        }
        Class<?> clazz = vo.getClass();
        List<Field> liField = new ArrayList<Field>();
        for (String field : fields) {
            try {
                liField.add(getField(field, clazz));
            } catch (Exception e) {
                throw new VerifyException(field, e.getMessage());
            }
        }
        Field[] arrField = new Field[liField.size()];
        arrField = liField.toArray(arrField);
        verify(vo, arrField);
    }

    /**
     * @param vo
     * @throws VerifyException
     * @Description 判断是否有空值
     * @author xhz
     * @date 2016-9-2 下午5:56:20
     * @lastModifier
     */
    public static void verify(Object vo, String[] ignoreFields, boolean ignore)
            throws VerifyException {
        if (vo == null) {
            throw new VerifyException("VO", "输入值为null");
        }
        List<String> liIgnoreField = Arrays.asList(ignoreFields);
        Class<?> clazz = vo.getClass();
        List<Field> fields = BeanUtil.getAllField(clazz);
        List<Field> liField = new ArrayList<Field>();
        for (Field field : fields) {
            try {
                if (liIgnoreField.contains(field.getName())) {
                    continue;
                }
                liField.add(field);
            } catch (Exception e) {
                throw new VerifyException(field.getName(), e.getMessage());
            }
        }
        verify(vo, liField.toArray(new Field[0]));

    }
}
