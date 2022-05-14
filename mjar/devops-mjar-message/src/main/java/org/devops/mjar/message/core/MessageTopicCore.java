package org.devops.mjar.message.core;

import java.util.Date;

import org.devops.core.utils.util.FastJsonUtils;
import org.devops.mjar.message.model.MessageTopicMsg;
import org.devops.mjar.message.repository.MessageTopicMsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageTopicCore {

	@Autowired
	private MessageTopicMsgRepository messageTopicMsgRepository;
	
	public void push(String topic,String tag,Object body){
		try{
			body = FastJsonUtils.toJsonString(body);
		}catch(Exception e){
			
		}
		if(body == null){
			body = "";
		}
		
		MessageTopicMsg messageTopicMsg = new MessageTopicMsg();
		messageTopicMsg.setBody(body.toString());
		messageTopicMsg.setCreateTime(new Date());
		messageTopicMsg.setStatus((byte)0);
		messageTopicMsg.setTag(tag);
		messageTopicMsg.setTopic(topic);
		messageTopicMsgRepository.insert(messageTopicMsg);
	}
}
