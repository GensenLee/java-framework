package org.devops.mjar.message.topic.thread;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.IPUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.mjar.message.datasource.MessageDynamicDataSource;
import org.devops.mjar.message.model.MessageTopicMsg;
import org.devops.mjar.message.model.MessageTopicMsgLog;
import org.devops.mjar.message.repository.MessageTopicMsgLogRepository;
import org.devops.mjar.message.repository.MessageTopicMsgRepository;
import org.devops.mjar.message.topic.customer.Customer;
import org.devops.mjar.message.topic.customer.ICustomer;
import org.devops.mjar.message.topic.customer.ThreadCustomer;
import org.devops.mjar.message.topic.redis.JobRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Scope("prototype") //多例
public class TopicThread  extends AbstractThread {
	
	private String topic;
	
	private boolean isRun = true;
	
	private static ExecutorService pool;
	
	
	private List<ThreadCustomer> liThreadCustomer = new ArrayList<>();
	
	@Autowired
	private MessageTopicMsgRepository messageTopicMsgRepository;
	
	@Autowired
	private MessageTopicMsgLogRepository messageTopicMsgLogRepository;
	
	@Autowired
	private JobRedisDao jobRedisDao;
	
	public void initCustomer() {
		List<ICustomer> liICustomer = SpringContextUtil.getBeanList(ICustomer.class);
		
		for(ICustomer iCustomer : liICustomer){
			Customer customer = AnnotationUtils.findAnnotation(iCustomer.getClass(),Customer.class);
			if(customer == null){
				continue;
			}
			if(!customer.topic().equals(topic)){
				continue;
			}
			ThreadCustomer threadCustomer = new ThreadCustomer();
			threadCustomer.setICustomer(iCustomer);
			threadCustomer.setTag(customer.tag());
			threadCustomer.setName(iCustomer.getClass().getSimpleName());
			threadCustomer.setTopic(topic);
			liThreadCustomer.add(threadCustomer);
		}
		
		pool = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	}
	
	@Override
	protected boolean job() throws Exception {
		
		if(jobRedisDao.tryLock(topic)) {
			try{
				Date now = new Date();
				ModelWhere mw = new ModelWhere();
				mw.add(MessageTopicMsg.STATUS,MessageTopicMsg.Dict.Status.WAIT);
				
				List<MessageTopicMsg> liMessageTopicMsg = messageTopicMsgRepository
					.closeLog()
					.where(MessageTopicMsg.TOPIC,topic)
					.where(mw)
					.limit(10)
					.list();
				
				final String localIP =  IPUtil.getLocalIP();
				
				String execCustomer = ListUtil.join(ListUtil.toStringList(liThreadCustomer, "name"));
				
				for(MessageTopicMsg messageTopicMsg : liMessageTopicMsg){
					
					messageTopicMsg.setExecTime(now);
					messageTopicMsg.setStatus(MessageTopicMsg.Dict.Status.FINASH);
					messageTopicMsg.setExecClient(localIP);
					messageTopicMsg.setExecCustomer(execCustomer);
					
					for(ThreadCustomer threadCustomer : liThreadCustomer){
						if(!threadCustomer.checked(messageTopicMsg.getTag())){
							continue;
						}
						pool.execute(new Runnable() {
							@Override
							public void run() {
								// 需要启动一个线程池
								Date start = new Date();
								MessageTopicMsgLog messageTopicMsgLog = new MessageTopicMsgLog();
								messageTopicMsgLog.setCustomer(threadCustomer.getName());
								messageTopicMsgLog.setMsgId(messageTopicMsg.getId());
								messageTopicMsgLog.setTag(messageTopicMsg.getTag());
								messageTopicMsgLog.setTopic(messageTopicMsg.getTopic());
								messageTopicMsgLog.setExecTime(start);
								messageTopicMsgLog.setExecClient(localIP);
								messageTopicMsgLog.setExecStatus(MessageTopicMsgLog.Dict.ExecStatus.RUNNING);
								
								// 开启事务
								DataSourceTransactionManager dataSourceTransactionManager = SpringContextUtil.getBean(MessageDynamicDataSource.getDataSource(), DataSourceTransactionManager.class);
								DefaultTransactionDefinition def = new DefaultTransactionDefinition();
								def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
								TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(def);
								
								// 这个地方需要开启事务，要不然可能获取不要id
								messageTopicMsgLogRepository.closeLog().insert(messageTopicMsgLog);
								
								// 提交事务
								dataSourceTransactionManager.commit(transactionStatus);
								
								TransactionStatus onMessageTransactionStatus = null;
								try{
									
									// 开启事务
									def = new DefaultTransactionDefinition();
									def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
									onMessageTransactionStatus = dataSourceTransactionManager.getTransaction(def);		
									
									boolean result = threadCustomer.onMessage(messageTopicMsg.getTag(), messageTopicMsg.getBody());
									dataSourceTransactionManager.commit(onMessageTransactionStatus);
									
									messageTopicMsgLog.setExecStatus(MessageTopicMsgLog.Dict.ExecStatus.SUCCESS);
								}catch(Exception e){
									dataSourceTransactionManager.rollback(onMessageTransactionStatus);
									
									StringWriter stringWriter = new StringWriter();
								    PrintWriter writer = new PrintWriter(stringWriter);
									e.printStackTrace(writer);
									messageTopicMsgLog.setLog(stringWriter.getBuffer().toString());
									messageTopicMsgLog.setExecStatus(MessageTopicMsgLog.Dict.ExecStatus.FAILED);
									
								}finally{
									Date end = new Date();
									messageTopicMsgLog.setExecEndTime(end);
									messageTopicMsgLog.setExecSpanTime(end.getTime() - start.getTime());
									messageTopicMsgLogRepository.closeLog().update(messageTopicMsgLog);			
								}
								
							}
						});
						
					}
				}
				
				if(!CollectionUtils.isEmpty(liMessageTopicMsg)){
					messageTopicMsgRepository.closeLog().update(liMessageTopicMsg);
				}
				
				Thread.sleep(1000);
			}catch (Exception e) {
				throw e;
			} finally {
				jobRedisDao.releaseLock(topic);
			}
		} else {
			Thread.sleep(1000);
		}
		return isRun;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void stopJob(){
		isRun = false;
	}
	
	

}
