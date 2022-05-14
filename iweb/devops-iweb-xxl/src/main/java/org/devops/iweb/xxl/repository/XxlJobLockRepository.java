package org.devops.iweb.xxl.repository;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.interfaces.IModel;
import org.devops.iweb.xxl.model.XxlJobLock;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobLockRepository extends AbstractModelRepository<XxlJobLock, Long>{

	@Override
	protected Model<XxlJobLock, Long> getModel() {
		return (Model<XxlJobLock, Long>)super.getModel().closeLog();
	}
}
