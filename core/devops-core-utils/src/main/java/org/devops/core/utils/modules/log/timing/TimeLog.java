package org.devops.core.utils.modules.log.timing;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.StringUtil;

@Slf4j
public class TimeLog {
	
	private long timestamp;
	private long lastTimestamp;
	private String tag = "TimeLog";
	
	private TimeLog() {
		
	}
	
	public static TimeLog get(){
		StackTraceElement callInfo = new Throwable().getStackTrace()[1];
		String className[] = callInfo.getClassName().split("\\.");
		String methodName = className[className.length - 1]+"."+callInfo.getMethodName();

		return get(methodName);
	}
	
	public static TimeLog get(String tag){
		TimeLog timeLog = new TimeLog();
		if(StringUtil.isEmpty(tag)) {
			tag = "TimeLog";
		}else {
			tag = "TimeLog." + tag;
		}
		timeLog.tag = tag;
		timeLog.timestamp = System.currentTimeMillis();
		timeLog.lastTimestamp = System.currentTimeMillis();
		return timeLog;
	}
	
	public void mark() {
		lastTimestamp = System.currentTimeMillis();
	}
	public void log() {
		log("$ $$");
	}
	
	public void log(String msg) {
		log(msg,new Object[]{});
	}
	public void log(String msg,Object ...args){
		boolean enable = false;
		
		try{
			TimeLogProperties timeLogProperties = SpringContextUtil.getBean(TimeLogProperties.class);
			enable = timeLogProperties.isEnable();
		}catch(Exception e){
			
		}
		if(!enable) {
			return;
		}
		if(lastTimestamp == 0) {
			lastTimestamp = System.currentTimeMillis();
		}
		msg = msg.replace("$$", "(-- " + (System.currentTimeMillis() - lastTimestamp) + "ms)");
		lastTimestamp = System.currentTimeMillis();
		msg = msg.replace("$", (lastTimestamp - timestamp) + "ms");
		if(StringUtil.isNotEmpty(tag)) {
			msg = "["+tag+"] " + msg;
		}
		log.info(msg,args);
	}
}
