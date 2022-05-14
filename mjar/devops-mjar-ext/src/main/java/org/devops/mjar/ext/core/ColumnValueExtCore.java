package org.devops.mjar.ext.core;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.ext.annotation.ColumnExt;
import org.devops.mjar.ext.annotation.ColumnExtColumn;
import org.devops.mjar.ext.model.ExtColumnValue;
import org.devops.mjar.ext.repository.ExtColumnValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class ColumnValueExtCore {

	@Autowired
	private ExtColumnValueRepository extColumnValueRepository;
	
	public <T> T getByShopId(Long shopId,Class<T> clazz){
		
		String code = "";
		
		ColumnExt columnExt = AnnotationUtils.findAnnotation(clazz, ColumnExt.class);
		if(columnExt != null){
			code = columnExt.code();
		}
		return getByShopId(code,shopId,clazz);
	}
	
	public <T> T getByCompanyId(Long companyId,Class<T> clazz){
		
		String code = "";
		
		ColumnExt columnExt = AnnotationUtils.findAnnotation(clazz, ColumnExt.class);
		if(columnExt != null){
			code = columnExt.code();
		}
		return getByCompanyId(code,companyId,clazz);
	}


	public <T> T getByAppInstanceId(Long appInstanceId,Class<T> clazz){
		
		String code = "";
		
		ColumnExt columnExt = AnnotationUtils.findAnnotation(clazz, ColumnExt.class);
		if(columnExt != null){
			code = columnExt.code();
		}
		return getByAppInstanceId(code,appInstanceId,clazz);
	}
	
	public <T> T getByShopId(String code,Long shopId,Class<T> clazz){
		
		ExtColumnValue extColumnValue = extColumnValueRepository
				.where(ExtColumnValue.CODE,code)
				.where(ExtColumnValue.SHOP_ID,shopId)
				.where(ExtColumnValue.COMPANY_ID,0)
				.where(ExtColumnValue.APP_INSTANCE_ID,0)
				.get();
		if(extColumnValue == null){
			return null;
		}
		return get(extColumnValue,clazz);
			
		
	}
	
	public <T> T getByCompanyId(String code,Long companyId,Class<T> clazz){
		
		ExtColumnValue extColumnValue = extColumnValueRepository
				.where(ExtColumnValue.CODE,code)
				.where(ExtColumnValue.COMPANY_ID,companyId)
				.where(ExtColumnValue.SHOP_ID,0)
				.where(ExtColumnValue.APP_INSTANCE_ID,0)
				.get();
		if(extColumnValue == null){
			return null;
		}
		return get(extColumnValue,clazz);
			
		
	}
	
	public <T> T getByAppInstanceId(String code,Long appInstanceId,Class<T> clazz){
		
		ExtColumnValue extColumnValue = extColumnValueRepository
				.where(ExtColumnValue.CODE,code)
				.where(ExtColumnValue.APP_INSTANCE_ID,appInstanceId)
				.where(ExtColumnValue.COMPANY_ID,0)
				.where(ExtColumnValue.SHOP_ID,0)
				.get();
		if(extColumnValue == null){
			return null;
		}
		return get(extColumnValue,clazz);
			
		
	}
	
	@SuppressWarnings("unchecked")
	private <T> T get(ExtColumnValue extColumnValue,Class<T> clazz){
		try {
			if(StringUtil.isEmpty(extColumnValue.getConfigValue())){
				return null;
			}
			
			Map<String,Object> tmpMap = FastJsonUtils.getBean(extColumnValue.getConfigValue(), Map.class);
			
			if(clazz.equals(Map.class)){
				return (T)tmpMap;
			}
			
			List<Field> liField = BeanUtil.getAllField(clazz);
			
			for(Field field : liField){
				
				String name = field.getName();
				
				ColumnExtColumn columnExtColumn = AnnotationUtils.findAnnotation(field, ColumnExtColumn.class);
				if(columnExtColumn != null){
					name = columnExtColumn.name();
				}
				Object v = tmpMap.get(name);
				if(v == null){
					continue;
				}
				tmpMap.remove(name);
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
