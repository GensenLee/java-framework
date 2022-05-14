package org.devops.mjar.live.core.sign;

import lombok.EqualsAndHashCode;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.vo.CloneableBean;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/3/6 16:26
 * @description：请求过程id代理
 */
@EqualsAndHashCode(callSuper = false)
public abstract class RequestHandleBean extends CloneableBean implements SignParameter {

    /**
     * 构造签名参数集合
     *
     * @return
     */
    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        Class<? extends RequestHandleBean> clazz = this.getClass();
        List<Field> fieldList = BeanUtil.getAllField(clazz);
        List<String> excludeList = new ArrayList<>();
        for (Field field : fieldList) {
            if (excludeList.contains(field.getName()) || AnnotationUtils.findAnnotation(field, SignIgnore.class) != null){
                excludeList.add(field.getName());
                continue;
            }
            Object value = BeanUtil.getValue(this, field.getName());
            if (value == null || value.toString().isEmpty()){
                continue;
            }

            // 标记为请求体，无需签名
            if (AnnotationUtils.findAnnotation(field, Body.class) != null){
                continue;
            }

            // 标记为集合，合并到签名集合
            if (AnnotationUtils.findAnnotation(field, ParamCollect.class) != null && value instanceof Map) {
                //noinspection rawtypes
                Map tmpMap = (Map)value;
                if (!tmpMap.isEmpty()) {
                    //noinspection unchecked
                    map.putAll(tmpMap);
                }
            }else {
                map.put(field.getName(), value);
            }
            excludeList.add(field.getName());
        }
        map.remove("serialVersionUID");
        map.remove("log");
        return map;
    }

}
