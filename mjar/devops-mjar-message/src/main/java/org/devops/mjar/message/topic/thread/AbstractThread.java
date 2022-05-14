package org.devops.mjar.message.topic.thread;

import org.devops.mjar.message.topic.redis.JobRedisDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public abstract class AbstractThread extends Thread implements InitializingBean {
	
	private boolean bInit = false;
	
	protected abstract boolean job() throws Exception;
	
	@Autowired
	private JobRedisDao jobRedisDao;
	
	private long logTime = System.currentTimeMillis();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.bInit = true;
		log.info(this.getClass().getName()+" spring初始化完毕");
		this.runAfterPropertiesSet();
	}
	
	public void runAfterPropertiesSet(){
		
	}

	@Override
	public void run(){
		log.info(this.getName()+" begin runing ...");
		try{
			while(true){
				if(!bInit){
					Thread.sleep(100);
					continue;
				}
				if(!jobRedisDao.isRun()){
					if(System.currentTimeMillis() - logTime > 10000){
						log.info(this.getName()+" suspend 暂时挂起,JOB");
						logTime = System.currentTimeMillis();
					}
					Thread.sleep(1000);
					continue;
				}
				if(job()){
					continue;
				}else{
					break;
				}
			}
			log.info(this.getName()+" end runing ... ");
		}catch(Exception e){
			log.error("出错啦",e);
			log.info("重启jobThread");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				log.error("出错啦",e);
			}
			this.run();
		}
	}
	
}
