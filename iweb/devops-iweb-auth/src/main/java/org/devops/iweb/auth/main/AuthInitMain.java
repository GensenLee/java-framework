package org.devops.iweb.auth.main;

import org.devops.core.model.main.AbstractModelMain;
import org.devops.iweb.auth.datasource.AuthDynamicDataSource;
import org.devops.iweb.auth.model.AuthModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Lazy(false)
@Data
@EqualsAndHashCode(callSuper=false)
public class AuthInitMain extends AbstractModelMain{

	@Override
	protected String getDataSource() {
		return AuthDynamicDataSource.getDataSource();
	}
	
	@Override
	protected String getPackage() {
		return AuthModel.class.getPackage().getName();
	}
	
}
