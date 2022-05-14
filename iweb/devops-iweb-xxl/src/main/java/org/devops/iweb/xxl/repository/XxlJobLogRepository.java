package org.devops.iweb.xxl.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.core.Model;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.util.DateUtil;
import org.devops.iweb.xxl.model.XxlJobLog;
import org.springframework.stereotype.Repository;

@Repository
public class XxlJobLogRepository extends AbstractModelRepository<XxlJobLog, Long>{

	@Override
	protected Model<XxlJobLog, Long> getModel() {
		return (Model<XxlJobLog, Long>)super.getModel().closeLog();
	}
	
	public List<Long> findFailJobLogIds(long size){
		ModelWhere mw = new ModelWhere();
		mw.not();
		ModelWhere mw2 = new ModelWhere();
		mw2.add(XxlJobLog.TRIGGER_CODE,"0,200",ModelOperator.IN);
		mw2.add(XxlJobLog.HANDLE_CODE,0);
		mw.add(mw2);
		mw.or(XxlJobLog.HANDLE_CODE,200);
		
		return this
			.closeLog()
			.include(XxlJobLog.ID)
			.where(mw)
			.where(XxlJobLog.ALARM_STATUS,0)
			.listLong();
	}
	
	public long updateAlarmStatus(long logId,int oldAlarmStatus,int newAlarmStatus){
		return this
				.closeLog()
			.where(XxlJobLog.ID,logId)
			.where(XxlJobLog.ALARM_STATUS,oldAlarmStatus)
			.update(XxlJobLog.ALARM_STATUS,newAlarmStatus);
	}
	
	public long updateHandleInfo(XxlJobLog xxlJobLog){
		return this
				.closeLog()
				.include(XxlJobLog.HANDLE_TIME,XxlJobLog.HANDLE_CODE,XxlJobLog.HANDLE_MSG)
				.update(xxlJobLog);
	}
	
	public long updateTriggerInfo(XxlJobLog xxlJobLog){
		return this
				.closeLog()
				.include(XxlJobLog.TRIGGER_CODE,XxlJobLog.TRIGGER_MSG,XxlJobLog.TRIGGER_TIME,XxlJobLog.EXECUTOR_ADDRESS,XxlJobLog.EXECUTOR_FAIL_RETRY_COUNT,XxlJobLog.EXECUTOR_HANDLER,XxlJobLog.EXECUTOR_PARAM,XxlJobLog.EXECUTOR_SHARDING_PARAM)
				.update(xxlJobLog);
	}
	
	public long triggerCountByHandleCode(int handleCode){
		if(handleCode > 0){
			this.where(XxlJobLog.HANDLE_CODE,handleCode);
		}
		return this.closeLog().count();
	}
	
	public List<Map<String,Object>> triggerCountByDay(Date from,Date to){
		return this.query("SELECT" + 
			" DATE_FORMAT(trigger_time,'%Y-%m-%d') triggerDay,"+
			" COUNT(handle_code) triggerDayCount,"+
			" SUM(CASE WHEN (trigger_code in (0, 200) and handle_code = 0) then 1 else 0 end) as triggerDayCountRunning,"+
			" SUM(CASE WHEN handle_code = 200 then 1 else 0 end) as triggerDayCountSuc"+
		" FROM xxl_job_log"+
		" WHERE trigger_time BETWEEN '"+DateUtil.getDateTimeFormat(from)+"' and '"+DateUtil.getDateTimeFormat(to)+"'"+
		" GROUP BY triggerDay"+
		" ORDER BY triggerDay");
	}
	
	private ModelWhere listModelWhere(int jobGroup,int jobId,Date triggerTimeStart,Date triggerTimeEnd,int logStatus){
		ModelWhere mw = new ModelWhere();
		if(jobId == 0 && jobGroup > 0){
			mw.add(XxlJobLog.JOB_GROUP,jobGroup);
		}
		if(jobId > 0){
			mw.add(XxlJobLog.JOB_ID,jobId);
		}
		if(triggerTimeStart != null){
			mw.add(XxlJobLog.TRIGGER_TIME,triggerTimeStart,ModelOperator.EGT);
		}
		if(triggerTimeEnd != null){
			mw.add(XxlJobLog.TRIGGER_TIME,triggerTimeEnd,ModelOperator.ELT);
		}
		if(logStatus == 1){
			mw.add(XxlJobLog.HANDLE_CODE,200);
		}else if(logStatus == 2){
			ModelWhere mw2 = new ModelWhere();
			mw2.add(XxlJobLog.TRIGGER_CODE,"0,200",ModelOperator.IN);
			mw2.or(XxlJobLog.HANDLE_CODE, "0,200",ModelOperator.IN);
			mw.add(mw2);
		}else if(logStatus == 3){
			mw.add(XxlJobLog.HANDLE_CODE,0);
			mw.add(XxlJobLog.TRIGGER_CODE,200);
		}
		
		return mw;
	}
	
	public List<XxlJobLog> pageList(int offset,int pagesize,int jobGroup,int jobId,Date triggerTimeStart,Date triggerTimeEnd,int logStatus){
		return this
				.closeLog()
				.where(listModelWhere(jobGroup,jobId,triggerTimeStart,triggerTimeEnd,logStatus))
				.limit(offset,pagesize)
				.orderBy(XxlJobLog.TRIGGER_TIME,"DESC")
				.list();
	}
	
	public long pageListCount(int jobGroup,int jobId,Date triggerTimeStart,Date triggerTimeEnd,int logStatus){
		return this
				.closeLog()
				.where(listModelWhere(jobGroup,jobId,triggerTimeStart,triggerTimeEnd,logStatus))
				.count();
	}
	
	public void clearLog(int jobGroup,int jobId,Date clearBeforeTime,int clearBeforeNum){
		
		if(clearBeforeNum > 0){
			if(jobGroup > 0){
				this.where(XxlJobLog.JOB_GROUP,jobGroup);
			}
			if(jobId > 0){
				this.where(XxlJobLog.JOB_ID,jobId);
			}
			List<Long> ids = this.closeLog().include(XxlJobLog.ID).orderBy(XxlJobLog.TRIGGER_TIME,"DESC").limit(0,clearBeforeNum).listLong();
			if(ids.size() == 0){
				ids.add(-999999L);
			}
			this.where(XxlJobLog.ID,ids,ModelOperator.NOT_IN);
		}
		
		if(jobGroup > 0){
			this.where(XxlJobLog.JOB_GROUP,jobGroup);
		}
		if(jobId > 0){
			this.where(XxlJobLog.JOB_ID,jobId);
		}
		if(clearBeforeTime != null){
			this.where(XxlJobLog.TRIGGER_TIME,clearBeforeTime,ModelOperator.ELT);
		}
		this.closeLog()
			.delete();
	}
}
