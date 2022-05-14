package org.devops.core.model.core;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.devops.core.model.annotation.Table;
import org.devops.core.model.autoid.ModelAutoIdFactory;
import org.devops.core.model.dto.ModelConfig;
import org.devops.core.model.impl.ModelDaoImpl;
import org.devops.core.model.interfaces.BaseModelDao;
import org.devops.core.utils.util.StringUtil;
import org.springframework.core.annotation.AnnotationUtils;

import com.alibaba.druid.pool.DruidDataSource;

public class ModelFactory {

	private static Map<String,Model<?, ?>> _cache = new HashMap<String,Model<?, ?>>();
	
	private static Map<String,DataSource> _mapDataSrouce = new HashMap<String,DataSource>();

	private static ModelAutoIdFactory modelAutoIdFactory = new ModelAutoIdFactory();

	public static <T, K> Model<T, K> getModel(Class<T> clazz){
		return getModel("",clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T, K> Model<T, K> getModel(String prefix,Class<T> clazz){
		//先获取表名
		Table table = AnnotationUtils.findAnnotation(clazz, Table.class);
		
		String tableName;
		if(table == null){
			tableName = ModelHelper.toUUCase(clazz.getSimpleName());
		}else{
			tableName = table.value().split(",")[0];
			if(StringUtil.isEmpty(prefix)) {
				prefix = table.prefix();
			}
		}
		String cacheKey = tableName+"@@"+clazz.getName();
		Model<?, ?> model = _cache.get(cacheKey);
		if(model == null){
			BaseModelDao modelDao = null;
			
			String key = "".equals(prefix)?"default":prefix;
			
			DataSource ds = _mapDataSrouce.get(key);
			if(ds != null) {
				modelDao = new ModelDaoImpl(ds);
			}
			model = new Model<T, K>(tableName,prefix,table,clazz ,modelDao);
			_cache.put(cacheKey, model);
		}
		return (Model<T, K>)model;
	}
	
	public static void init(ModelConfig modelConfig) throws SQLException {
		
		init("default",modelConfig);
	}
	
	public static void init(String prefix,ModelConfig modelConfig) throws SQLException {
		
		try {
			
			//数据源初始化
			DruidDataSource ds = new DruidDataSource();
			ds.setUrl(modelConfig.getUrl());
			ds.setUsername(modelConfig.getUsername());
			ds.setPassword(modelConfig.getPassword());
			ds.setInitialSize(20);
			ds.setMinIdle(10);
			ds.setMaxActive(200);
			ds.setMaxWait(60000);
			ds.setTimeBetweenEvictionRunsMillis(60000);
			ds.setMinEvictableIdleTimeMillis(300000);
			ds.setValidationQuery("SELECT 1");
			ds.setTestWhileIdle(true);
			ds.setTestOnBorrow(true);
			ds.setTestOnReturn(true);
			ds.setPoolPreparedStatements(true);
			ds.setMaxPoolPreparedStatementPerConnectionSize(20);
			ds.setFilters("stat");
			ds.init();
			_mapDataSrouce.put(prefix, ds);
			
		} catch (SQLException e) {
			throw e;
		}
	}
}
