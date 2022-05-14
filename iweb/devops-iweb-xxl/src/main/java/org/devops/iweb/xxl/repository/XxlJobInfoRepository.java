package org.devops.iweb.xxl.repository;

import java.util.List;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.model.interfaces.IModel;
import org.devops.iweb.xxl.model.XxlJobInfo;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobInfoRepository extends AbstractModelRepository<XxlJobInfo, Integer>{

	@Override
	protected Model<XxlJobInfo, Integer> getModel() {
		return (Model<XxlJobInfo, Integer>)super.getModel().closeLog();
	}
	
	public List<XxlJobInfo> scheduleJobQuery(long timestamp){
		return this
				.closeLog()
				.where(XxlJobInfo.TRIGGER_STATUS,1)
				.where(XxlJobInfo.TRIGGER_NEXT_TIME,timestamp,ModelOperator.LT)
				.list();
	}
	
	public List<XxlJobInfo> getJobsByGroup(long jobGroup){
		return this
				.closeLog()
				.where(XxlJobInfo.JOB_GROUP,jobGroup)
				.list();
	}
}
