package org.devops.core.utils.spring;

import org.devops.core.utils.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

import java.util.*;

/**
 * Spring context util
 * 获得 Spring context 的 context
 *
 */
public class SpringContextUtil {

	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
	
	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		SpringContextUtil.context = context;
		if(logger.isDebugEnabled()){
			String[] names = context.getBeanDefinitionNames();
			for(String name : names){
				logger.debug("****************"+name);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String prefix, Class<T> clazz) {
		if(StringUtil.isEmpty(prefix)) {
			return null;
		}
		String[] names = context.getBeanNamesForType(clazz);
		for(String name : names) {
			if(name.startsWith(prefix)) {
				return (T)context.getBean(name);
			}
		}
		return null;
	}

	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}														
	
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
	
	public static <T> List<T> getBeanList(Class<T> clazz){
		Map<String,T> mapBean = context.getBeansOfType(clazz);
		Map<Integer,T> treeMap = new TreeMap<Integer,T>();
		List<T> listBean = new ArrayList<T>();
		
		Set<Integer> setIndex = new HashSet<Integer>();

		for(String key : mapBean.keySet()){
			T obj = mapBean.get(key);
			Order order = AnnotationUtils.findAnnotation(obj.getClass(), Order.class);
			
			int index = 0;
			if(order == null){
				index = Integer.MAX_VALUE;
			}else{
				index = order.value();
			}
			while(setIndex.contains(index)){
				if(index > Integer.MAX_VALUE / 2){
					index--;
				}else{
					index++;
				}
			}
			setIndex.add(index);
			treeMap.put(index, obj);
		}
		
		for(Integer key : treeMap.keySet()){
			listBean.add(treeMap.get(key));
		}
		
		return listBean;
	}

}
