package org.devops.mjar.ext.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.ext.annotation.KeyValueExt;
import org.devops.mjar.ext.annotation.KeyValueExtValue;
import org.devops.mjar.ext.model.ExtKeyValue;
import org.devops.mjar.ext.repository.ExtKeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class KeyValueExtCore {

	@Autowired
	private ExtKeyValueRepository extKeyValueRepository;
	
	public <T> T get(Class<T> clazz){
		
		String type = "";
		
		KeyValueExt keyValueExt = AnnotationUtils.findAnnotation(clazz, KeyValueExt.class);
		if(keyValueExt != null){
			type = keyValueExt.type();
		}
		return get(type,clazz);
	}
	
	public <T> T get(String type,Class<T> clazz){
		
		List<ExtKeyValue> liExtKeyValue = extKeyValueRepository
			.where(ExtKeyValue.TYPE,type)
			.where(ExtKeyValue.SHOP_ID,0)
			.where(ExtKeyValue.COMPANY_ID,0)
			.where(ExtKeyValue.APP_INSTANCE_ID,0)
			.list();
		
		if(CollectionUtils.isEmpty(liExtKeyValue)){
			return null;
		}
		return get(liExtKeyValue,clazz);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T get(List<ExtKeyValue> liExtKeyValue,Class<T> clazz){
		try {
			Map<String,Object> tmpMap = new HashMap<String,Object>();
			
			for(ExtKeyValue extKeyValue : liExtKeyValue){
				tmpMap.put(extKeyValue.getKey(), extKeyValue.getValue());
			}
			
			if(clazz.equals(Map.class)){
				return (T)tmpMap;
			}
			
			List<Field> liField = BeanUtil.getAllField(clazz);
			
			for(Field field : liField){
				
				String key = field.getName();
				
				KeyValueExtValue keyValueExtValue = AnnotationUtils.findAnnotation(field, KeyValueExtValue.class);
				if(keyValueExtValue != null){
					key = keyValueExtValue.key();
				}
				Object v = tmpMap.get(key);
				if(v == null){
					continue;
				}
				tmpMap.remove(key);
				tmpMap.put(field.getName(), v);
			}
	
			T result =  FastJsonUtils.getBean(FastJsonUtils.toJsonString(tmpMap), clazz);
			tmpMap.clear();
			return result;
			
		}catch (Exception e) {
			return null;
		}
	}
}
