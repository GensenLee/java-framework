package org.devops.core.model.core;

import java.lang.reflect.Method;

import org.devops.core.model.interfaces.ModelAware;

public abstract class AbstractModelAware implements ModelAware {

	protected void invoke(String type,Model<?, ?> model,String tableSuffix){
		String tableName = model.getRealTable();
		String methodName = type+"_" + tableName;
		try {
			Method method = this.getClass().getDeclaredMethod(methodName,model.getClass(),String.class);
			method.invoke(this, model,tableSuffix);
		} catch (Exception e) {
		}
	}
}
