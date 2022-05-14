package org.devops.core.utils.helper;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.MD5;
import org.devops.core.utils.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 验证签名
 */
public class PolyvSignHelper {
    private static final Logger logger = LoggerFactory.getLogger(PolyvSignHelper.class);

    public static final String SIGN_KEY = "sign";
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String USER_ID = "userId";



    /**
     * 前面
     *
     * @param param
     * @param secret
     * @return
     */
    public static String sign(Map<String, Object> param, String secret) {
        SortedMap<String, Object> parameters = new TreeMap<>(param);
        logger.info("PolyvSignHelper 入参[{}]", param);
        String sign = doCreatePolyvSign(parameters, secret);
        logger.info("PolyvSignHelper 签名[{}]", sign);
        return sign;
    }

    /**
     * 把参数转为map
     *
     * @param param
     * @return
     * @throws CommonRuntimeException
     */
    public static Map<String, Object> obj2Map(Object param) throws CommonRuntimeException {
        Class cls = param.getClass();

        List<Field> fields = BeanUtil.getAllField(cls);

        Map<String, Object> requestPara = new TreeMap<>();
        // 将请求的Bean类值转换为Map数据类型
        for (Field fieldElement : fields) {

            // 获取字段名称
            String fieldName = fieldElement.getName();
            if ("".equals(fieldName) || "serialVersionUID".equals(fieldName)) {
                continue;
            }
            fieldElement.setAccessible(true);
            // 取出对应字段的值
            Object objValue = null;
            try {
                objValue = fieldElement.get(param);
            } catch (Exception e) {
                throw new CommonRuntimeException("转化为map出错");
            }
            // 对Value进行赋值
            Object value = objValue != null ? objValue : "";

            if ("".equals(value)) {
                continue;
            }
            requestPara.put(fieldName, value);
        }

        if (requestPara.size() == 0) {
            logger.info("参数数量为空");
//            throw new CommonRuntimeException("参数数量为空");
        }
        logger.info("转化的参数[{}]", requestPara);
        return requestPara;
    }


    /**
     * 生成保利威签名
     *
     * @param parameters（参数）
     * @param clientSecret
     * @return
     */
    private static String doCreatePolyvSign(SortedMap<String, Object> parameters, String clientSecret) {
        StringBuffer sb = new StringBuffer();
        sb.append(clientSecret);
        Set set = parameters.entrySet();
        //除去签名和签名的密钥，其他参数参与签名，
        for (Object e : set) {
            Map.Entry entry = (Map.Entry) e;
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            if (null != v && !"".equals(v) && !SIGN_KEY.equals(k) && !"key".equals(k)) {
                sb.append(k).append(v);
            }
        }
        sb.append(clientSecret);
        logger.info("拼接的字符串[{}]", sb.toString());
        return MD5.getMD5(sb.toString()).toUpperCase();
    }

    /**
     * 保利威回调签名
     *
     * @param timestamp
     * @param appSecret
     * @return
     */
    public static String callbackSign(Long timestamp, String appSecret){
        return MD5.getMD5(appSecret + StringUtil.toString(timestamp));
    }

}
