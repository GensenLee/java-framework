package org.devops.core.model.main;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.devops.core.model.annotation.Table;
import org.devops.core.model.core.Model;
import org.devops.core.model.core.ModelFactory;
import org.devops.core.utils.datasource.DataSourceContextHolder;
import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.ReflectionUtil;
import org.devops.core.utils.util.StringUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractModelMain implements InitializingBean, ApplicationContextAware {

	@Override
	public void afterPropertiesSet() throws Exception {
		final List<Field> fields = BeanUtil.getAllField(this.getClass());
		final Object self = this;
		//系统表
		new Thread(new Runnable() {
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				for(Field field : fields){
					if(!field.getType().equals(Model.class)){
						continue;
					}
					try {
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(),self.getClass());
						Method method = pd.getReadMethod();
						Object o = method.invoke(self);
						if(o != null && o instanceof Model){
							Model model = (Model)o;
							String dataSourceName = getDataSource();
							String packageName = model.getClazz().getPackage().getName();
							String oldDataSourceName = DataSourceMapper.getName(packageName);
							
							if(StringUtil.isEmpty(oldDataSourceName) && StringUtil.isNotEmpty(dataSourceName)) {
								DataSourceMapper.setPackage(packageName, dataSourceName);
							}
							DataSourceContextHolder.setDataSourceType(dataSourceName, null);
							model.init();
							DataSourceContextHolder.setDataSourceType(dataSourceName,null);
						}
						
					} catch (Exception e) {
						log.error("[Exception 出错啦!]",e);
					} 
				}
			}
		}).start();
		
	}
	
	protected abstract String getDataSource();
	
	protected String getPackage() {
		return "";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		final String myPackage = getPackage(); 
		
		if(StringUtil.isEmpty(myPackage)) {
			return;
		}

		new Thread(new Runnable() {
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				Set<Class<?>> setClazz = ReflectionUtil.getClasses(myPackage);
				for(Class<?> clazz : setClazz) {
					try{
						if(AnnotationUtils.findAnnotation(clazz, Table.class) == null) {
							continue;
						}
						Model model = ModelFactory.getModel(clazz);
						model.init();
					}catch(Exception e){}
				}
			}
		}).start();
	}
}
