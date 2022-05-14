package org.devops.iweb.api.main;

import org.devops.core.model.main.AbstractModelMain;
import org.devops.iweb.api.datasource.IWebApiDynamicDataSource;
import org.devops.iweb.api.model.IWebApiModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Lazy(false)
@Data
@EqualsAndHashCode(callSuper=false)
public class IWebApiInitMain extends AbstractModelMain{

	@Override
	protected String getPackage() {
		return IWebApiModel.class.getPackage().getName();
	}
	
	@Override
	protected String getDataSource() {
		return IWebApiDynamicDataSource.getDataSource();
	}
	
}
