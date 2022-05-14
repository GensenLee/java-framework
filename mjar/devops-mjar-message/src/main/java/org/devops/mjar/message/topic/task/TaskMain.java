package org.devops.mjar.message.topic.task;

import org.devops.mjar.message.topic.thread.MainThread;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Lazy(false)
@Slf4j
public class TaskMain implements InitializingBean{

	@Autowired
	private MainThread mainThread;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		mainThread.start();
	}
}
