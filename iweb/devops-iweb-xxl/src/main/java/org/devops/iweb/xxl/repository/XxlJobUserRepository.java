package org.devops.iweb.xxl.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.iweb.xxl.model.XxlJobUser;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobUserRepository extends AbstractModelRepository<XxlJobUser, Integer>{

	@Override
	protected Model<XxlJobUser, Integer> getModel() {
		return (Model<XxlJobUser, Integer>)super.getModel().closeLog();
	}
	
	public XxlJobUser loadByUserName(String username){
		return this
				.closeLog()
				.where(XxlJobUser.USERNAME,username)
				.get();
	}
}
