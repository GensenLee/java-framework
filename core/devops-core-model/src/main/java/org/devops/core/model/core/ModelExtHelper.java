package org.devops.core.model.core;

import org.devops.core.model.interfaces.IModel;
import org.devops.core.utils.util.FastJsonUtils;

public class ModelExtHelper {

	public static <T> T getModelExt(IModel<?, ?> model,Class<T> clazz){
		Object result = model.get();
		if(result == null){
			return null;
		}
		Object ext = ModelHelper.getValue(result, "ext");
		if(ext == null){
			return null;
		}
		return FastJsonUtils.getBean(ext.toString(), clazz);
	}
}
