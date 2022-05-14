package org.devops.iweb.xxl.repository;

import java.util.List;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.interfaces.IModel;
import org.devops.iweb.xxl.model.XxlJobLock;
import org.devops.iweb.xxl.model.XxlJobLogglue;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobLogglueRepository extends AbstractModelRepository<XxlJobLogglue, Integer>{

	@Override
	protected Model<XxlJobLogglue, Integer> getModel() {
		return (Model<XxlJobLogglue, Integer>)super.getModel().closeLog();
	}
	
	public List<XxlJobLogglue> findByJobId(long jobId){
		return this
					.closeLog()
					.where(XxlJobLogglue.JOB_ID,jobId)
					.orderBy(XxlJobLogglue.ID,"DESC")
					.list();
	}
	
	public void removeOld(long jobId,int limit){
		this.query("DELETE FROM xxl_job_logglue"+
		" WHERE id NOT in("+
			" SELECT id FROM("+
				" SELECT id FROM xxl_job_logglue"+
				" WHERE `job_id` = "+ jobId +
				" ORDER BY update_time desc"+
				" LIMIT 0, "+limit+
			" ) t1"+
		" ) AND `job_id` = "+jobId);
	}
}
