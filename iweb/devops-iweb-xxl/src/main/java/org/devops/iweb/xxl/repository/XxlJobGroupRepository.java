package org.devops.iweb.xxl.repository;

import java.util.List;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.interfaces.IModel;
import org.devops.iweb.xxl.model.XxlJobGroup;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobGroupRepository extends AbstractModelRepository<XxlJobGroup, Integer>{

	@Override
	protected Model<XxlJobGroup, Integer> getModel() {
		return (Model<XxlJobGroup, Integer>)super.getModel().closeLog();
	}
	
	public List<XxlJobGroup> findByAddressType(int type){
		return this
				.closeLog()
				.where(XxlJobGroup.ADDRESS_TYPE,type)
				.orderBy(XxlJobGroup.ORDER,"ASC")
				.list();
	}
	
	public List<XxlJobGroup> findAll(){
		return this
				.closeLog()
				.orderBy(XxlJobGroup.ORDER,"ASC")
				.list();
	}
}
