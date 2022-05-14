package org.devops.mjar.live.polyv.feign.configuration;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.vo.BaseBean;
import org.devops.mjar.live.core.sign.RequestHandleBean;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/3/19 10:13
 * @description：参数转换工具
 */
public class ParameterConverterHelper {

    /**
     * 参数转换
     * @param param
     * @return
     */
    public static Map<String, Object> converter(Object param) {
        Map<String, Object> map;
        if (param instanceof RequestHandleBean) {
            map = ((RequestHandleBean) param).toMap();
        } else if (param instanceof BaseBean) {
            map = ((BaseBean) param).toMap();
        } else {
            map = FastJsonUtils.getMap(param);
        }
        return map;
    }

}
