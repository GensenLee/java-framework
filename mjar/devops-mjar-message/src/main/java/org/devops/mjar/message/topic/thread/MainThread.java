package org.devops.mjar.message.topic.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.DateUtil;
import org.devops.mjar.message.model.MessageTopic;
import org.devops.mjar.message.model.MessageTopicMsgLog;
import org.devops.mjar.message.repository.MessageTopicMsgLogRepository;
import org.devops.mjar.message.repository.MessageTopicRepository;
import org.devops.mjar.message.topic.constant.MjarMessageTopicConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MainThread extends AbstractThread {
	
	@Autowired
	private MessageTopicRepository messageTopicRepository;
	
	@Autowired
	private MessageTopicMsgLogRepository messageTopicMsgLogRepository;
	
	private Map<String,TopicThread> mapTopicThread = new HashMap<>();
	
	@Override
	protected boolean job() throws Exception {
		
		if(MjarMessageTopicConstant.topic != null && MjarMessageTopicConstant.topic.length > 0) {
			messageTopicRepository.where(MessageTopic.TOPIC,MjarMessageTopicConstant.topic,ModelOperator.IN);
		}
		
		List<MessageTopic> liMessageTopic = messageTopicRepository
				.closeLog()
				.list();
		
		for(MessageTopic messageTopic : liMessageTopic){
			TopicThread currentTopicThread = mapTopicThread.get(messageTopic.getTopic());
			if(messageTopic.getActive().equals(CommonConstant.ACTIVE)){
				if(currentTopicThread == null){
					TopicThread topicThread = SpringContextUtil.getBean(TopicThread.class);
					topicThread.setTopic(messageTopic.getTopic());
					topicThread.initCustomer();
					topicThread.start();
					mapTopicThread.put(messageTopic.getTopic(), topicThread);
				}
			} else {
				if(currentTopicThread != null){
					currentTopicThread.stopJob();
					mapTopicThread.remove(messageTopic.getTopic());
				}
			}
		}
		
		List<String> disableKey = new ArrayList<>();
		
		for(String key : mapTopicThread.keySet()){
			boolean isExist = false;
			for(MessageTopic messageTopic : liMessageTopic){
				if(key.equals(messageTopic.getTopic())){
					isExist = true;
					break;
				}
			}
			if(!isExist){
				disableKey.add(key);
			}
		}
		
		for(String key : disableKey){
			TopicThread currentTopicThread = mapTopicThread.get(key);
			currentTopicThread.stopJob();
			mapTopicThread.remove(key);
		}
		
		// 每天零时，对日志进行清除，自动清除30天之前的日志
		if ((System.currentTimeMillis() - DateUtil.getDayStart(new Date()).getTime()/1000 < 10)) {
			messageTopicMsgLogRepository
					.where(MessageTopicMsgLog.EXEC_TIME,DateUtil.addDay(new Date(), -30),ModelOperator.LT)
					.delete();
		}
		
		// 10秒
		Thread.sleep(10000);
		return true;
	}

}
