package org.devops.web.test.bean.model;

import java.util.HashMap;

import org.devops.core.model.core.AbstractModelAware;
import org.devops.core.model.core.Model;
import org.devops.core.model.dto.TableField;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationModelAware extends AbstractModelAware{

	/**
	 * 表创建回调
	 */
	@Override
	public <T extends Model<?,?>> void onCreated(T model,String tableSuffix) {
		//调用 onCreate_tableName
		this.invoke("onCreated",model, tableSuffix);
	}

	@Override
	public <T extends Model<?,?>> void onDataInitialized(T model, String tableSuffix) {
		this.invoke("onDataInitialized",model, tableSuffix);

	}


	@Override
	public <T extends Model<?,?>> void onColumnAdded(T model, String tableSuffix, HashMap<String, TableField> mapTableField) {
		this.invoke("onColumnAdded",model, tableSuffix);
	}


	@Override
	public <T extends Model<?,?>> void onInitialized(T model, String tableSuffix) {
		this.invoke("onInitialized",model, tableSuffix);

	}
	
}
