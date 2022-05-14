package org.devops.iweb.file.main;

import org.devops.core.model.main.AbstractModelMain;
import org.devops.iweb.file.datasource.FileDynamicDataSource;
import org.devops.iweb.file.model.IFileModel;
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
public class FileInitMain extends AbstractModelMain{

	@Override
	protected String getDataSource() {
		return FileDynamicDataSource.getDataSource();
	}
	
	@Override
	protected String getPackage() {
		return IFileModel.class.getPackage().getName();
	}
}
