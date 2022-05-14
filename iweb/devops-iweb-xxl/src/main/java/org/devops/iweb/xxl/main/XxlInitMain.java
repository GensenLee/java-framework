package org.devops.iweb.xxl.main;

import org.devops.core.model.main.AbstractModelMain;
import org.devops.iweb.xxl.datasource.XxlDynamicDataSource;
import org.devops.iweb.xxl.model.XxlJobModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Lazy(false)
@Order(Integer.MIN_VALUE)
@Data
@EqualsAndHashCode(callSuper=false)
public class XxlInitMain extends AbstractModelMain{
	
	@Override
	protected String getDataSource() {
		return XxlDynamicDataSource.getDataSource();
	}
	
	@Override
	protected String getPackage() {
		return XxlJobModel.class.getPackage().getName();
	}
}
