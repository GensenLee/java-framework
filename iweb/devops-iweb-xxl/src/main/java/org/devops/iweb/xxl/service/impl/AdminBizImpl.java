package org.devops.iweb.xxl.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.devops.iweb.xxl.model.XxlJobInfo;
import org.devops.iweb.xxl.model.XxlJobLog;
import org.devops.iweb.xxl.model.XxlJobRegistry;
import org.devops.iweb.xxl.repository.XxlJobInfoRepository;
import org.devops.iweb.xxl.repository.XxlJobLogRepository;
import org.devops.iweb.xxl.repository.XxlJobRegistryRepository;
import org.devops.iweb.xxl.thread.JobTriggerPoolHelper;
import org.devops.iweb.xxl.trigger.TriggerTypeEnum;
import org.devops.iweb.xxl.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

/**
 * @author xuxueli 2017-07-27 21:54:20
 */
@Service
public class AdminBizImpl implements AdminBiz {
    private static Logger logger = LoggerFactory.getLogger(AdminBizImpl.class);

    @Autowired
    private XxlJobLogRepository xxlJobLogRepository;
    
    @Autowired
    private XxlJobInfoRepository xxlJobInfoRepository;
    
    @Autowired
    private XxlJobRegistryRepository xxlJobRegistryRepository;


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        for (HandleCallbackParam handleCallbackParam: callbackParamList) {
            ReturnT<String> callbackResult = callback(handleCallbackParam);
            logger.debug(">>>>>>>>> JobApiController.callback {}, handleCallbackParam={}, callbackResult={}",
                    (callbackResult.getCode()==IJobHandler.SUCCESS.getCode()?"success":"fail"), handleCallbackParam, callbackResult);
        }

        return ReturnT.SUCCESS;
    }

    private ReturnT<String> callback(HandleCallbackParam handleCallbackParam) {
        // valid log item
        XxlJobLog log = xxlJobLogRepository.closeLog().get(handleCallbackParam.getLogId());
        if (log == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "log item not found.");
        }
        if (log.getHandleCode() > 0) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "log repeate callback.");     // avoid repeat callback, trigger child job etc
        }

        // trigger success, to trigger child job
        String callbackMsg = null;
        if (IJobHandler.SUCCESS.getCode() == handleCallbackParam.getExecuteResult().getCode()) {
            XxlJobInfo xxlJobInfo = xxlJobInfoRepository.get(log.getJobId());
            if (xxlJobInfo!=null && xxlJobInfo.getChildJobId()!=null && xxlJobInfo.getChildJobId().trim().length()>0) {
                callbackMsg = "<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>"+ I18nUtil.getString("jobconf_trigger_child_run") +"<<<<<<<<<<< </span><br>";

                String[] childJobIds = xxlJobInfo.getChildJobId().split(",");
                for (int i = 0; i < childJobIds.length; i++) {
                    int childJobId = (childJobIds[i]!=null && childJobIds[i].trim().length()>0 && isNumeric(childJobIds[i]))?Integer.valueOf(childJobIds[i]):-1;
                    if (childJobId > 0) {

                        JobTriggerPoolHelper.trigger(childJobId, TriggerTypeEnum.PARENT, -1, null, null);
                        ReturnT<String> triggerChildResult = ReturnT.SUCCESS;

                        // add msg
                        callbackMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg1"),
                                (i+1),
                                childJobIds.length,
                                childJobIds[i],
                                (triggerChildResult.getCode()==ReturnT.SUCCESS_CODE?I18nUtil.getString("system_success"):I18nUtil.getString("system_fail")),
                                triggerChildResult.getMsg());
                    } else {
                        callbackMsg += MessageFormat.format(I18nUtil.getString("jobconf_callback_child_msg2"),
                                (i+1),
                                childJobIds.length,
                                childJobIds[i]);
                    }
                }

            }
        }

        // handle msg
        StringBuffer handleMsg = new StringBuffer();
        if (log.getHandleMsg()!=null) {
            handleMsg.append(log.getHandleMsg()).append("<br>");
        }
        if (handleCallbackParam.getExecuteResult().getMsg() != null) {
            handleMsg.append(handleCallbackParam.getExecuteResult().getMsg());
        }
        if (callbackMsg != null) {
            handleMsg.append(callbackMsg);
        }

        // success, save log
        log.setHandleTime(new Date());
        log.setHandleCode(handleCallbackParam.getExecuteResult().getCode());
        log.setHandleMsg(handleMsg.toString());
        xxlJobLogRepository.updateHandleInfo(log);

        return ReturnT.SUCCESS;
    }

    private boolean isNumeric(String str){
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
    	XxlJobRegistry xxlJobRegistry = new XxlJobRegistry();
    	xxlJobRegistry.setRegistryGroup(registryParam.getRegistGroup());
    	xxlJobRegistry.setRegistryKey(registryParam.getRegistryKey());
    	xxlJobRegistry.setRegistryValue(registryParam.getRegistryValue());
    	xxlJobRegistry.setUpdateTime(new Date());
        long ret = xxlJobRegistryRepository.registryUpdate(xxlJobRegistry);
        if (ret < 1) {
        	xxlJobRegistryRepository.insert(xxlJobRegistry);
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
    	XxlJobRegistry xxlJobRegistry = new XxlJobRegistry();
    	xxlJobRegistry.setRegistryGroup(registryParam.getRegistGroup());
    	xxlJobRegistry.setRegistryKey(registryParam.getRegistryKey());
    	xxlJobRegistry.setRegistryValue(registryParam.getRegistryValue());
    	xxlJobRegistryRepository.deleteByWhere(xxlJobRegistry);
        return ReturnT.SUCCESS;
    }

}
