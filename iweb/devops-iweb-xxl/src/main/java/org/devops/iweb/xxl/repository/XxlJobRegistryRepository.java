package org.devops.iweb.xxl.repository;

import java.util.List;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.emun.ModelOperator;
import org.devops.iweb.xxl.model.XxlJobRegistry;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobRegistryRepository extends AbstractModelRepository<XxlJobRegistry, Integer>{

	@Override
	protected Model<XxlJobRegistry, Integer> getModel() {
		return (Model<XxlJobRegistry, Integer>)super.getModel().closeLog();
	}
	
	public long registryUpdate(XxlJobRegistry xxlJobRegistry){
		return this
				.closeLog()
				.include(XxlJobRegistry.UPDATE_TIME)
				.where(XxlJobRegistry.REGISTRY_GROUP,xxlJobRegistry.getRegistryGroup())
				.where(XxlJobRegistry.REGISTRY_KEY,xxlJobRegistry.getRegistryKey())
				.where(XxlJobRegistry.REGISTRY_VALUE,xxlJobRegistry.getRegistryValue())
				.update(xxlJobRegistry);
	}
	
	public long deleteByWhere(XxlJobRegistry xxlJobRegistry){
		return this
				.closeLog()
				.where(XxlJobRegistry.REGISTRY_GROUP,xxlJobRegistry.getRegistryGroup())
				.where(XxlJobRegistry.REGISTRY_KEY,xxlJobRegistry.getRegistryKey())
				.where(XxlJobRegistry.REGISTRY_VALUE,xxlJobRegistry.getRegistryValue())
				.delete();
	}
	
	public long removeDead(int timeout){
		return this
			.closeLog()
			.where(XxlJobRegistry.UPDATE_TIME,"< DATE_ADD(NOW(),INTERVAL - "+timeout+" SECOND)",ModelOperator.PLAIN)
			.delete();
	}
	
	public List<XxlJobRegistry> findAll(int timeout){
		return this
			.closeLog()
			.where(XxlJobRegistry.UPDATE_TIME,"> DATE_ADD(NOW(),INTERVAL - "+timeout+" SECOND)",ModelOperator.PLAIN)
			.list();
	}
}
