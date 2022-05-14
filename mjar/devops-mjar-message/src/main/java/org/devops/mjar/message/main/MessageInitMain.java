package org.devops.mjar.message.main;

import org.devops.core.model.main.AbstractModelMain;
import org.devops.mjar.message.datasource.MessageDynamicDataSource;
import org.devops.mjar.message.model.IMessageModel;
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
public class MessageInitMain extends AbstractModelMain{

	@Override
	protected String getDataSource() {
		return MessageDynamicDataSource.getDataSource();
	}
	
	@Override
	protected String getPackage() {
		return IMessageModel.class.getPackage().getName();
	}
	
}
