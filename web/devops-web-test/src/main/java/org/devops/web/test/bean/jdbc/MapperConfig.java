package org.devops.web.test.bean.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.devops.core.utils.datasource.DataSourceAspect;
import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.datasource.DynamicDataSource;
import org.devops.web.test.bean.datasource.DataSourceType;
import org.devops.web.test.model.DevopsWebModel;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@Configuration
@EnableAutoConfiguration
@MapperScan(value = {"org.devops.web.test.dao.*.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")  //mapper扫描
public class MapperConfig {

	private void initDruidDataSource(DruidDataSource druidDataSource){
		WallFilter wallFilter=new WallFilter();
		WallConfig wallConfig =new WallConfig();
		wallConfig.setMultiStatementAllow(true);//允许一次执行多条语句
		wallConfig.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
		wallFilter.setConfig(wallConfig);
		List<Filter> filterList=new ArrayList<Filter>();
		filterList.add(wallFilter);
		druidDataSource.setProxyFilters(filterList);
	}
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		initDruidDataSource(druidDataSource);
		return druidDataSource;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
	public DataSource druidSlaveDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		initDruidDataSource(druidDataSource);
		return druidDataSource;
	}
	
	
	@Bean
	@Primary
	@DependsOn({ "druidDataSource","druidSlaveDataSource" })
	public DynamicDataSource dynamicDataSource(
			@Qualifier("druidDataSource") DataSource druidDataSource,
			@Qualifier("druidSlaveDataSource") DataSource druidSlaveDataSource) {
		
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		
		targetDataSources.put(DataSourceType.MASTER, druidDataSource);
		targetDataSources.put(DataSourceType.SLAVE1, druidSlaveDataSource);
		
		DynamicDataSource dataSource = new DynamicDataSource(DataSourceType.NAME);
		dataSource.setTargetDataSources(targetDataSources);			// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(druidDataSource);	// 默认的datasource设置为druidDataSource
		
		return dataSource;
		
	}
	    
    
    /**
	 * 选择数据源，配合 @Datasource 一起使用
	 * @return
	 */
	@Bean
	public DataSourceAspect dataSourceAspect(){
		return new DataSourceAspect();
	}
    
    
    //mybatis扫描
    @Bean
    @Primary
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] gatewayRes = resolver.getResources("classpath*:org/devops/web/test/dao/**/*.xml");
		sqlSessionFactoryBean.setMapperLocations(gatewayRes);
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:spring-mybatis.xml"));
		
		
		DataSourceMapper.setPackage(DevopsWebModel.class.getPackage().getName(), DataSourceType.NAME);
		return sqlSessionFactoryBean.getObject();
	}
    
    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //事务管理
    @Bean
    @Primary
	public PlatformTransactionManager polyvTransactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
		return new DataSourceTransactionManager(dynamicDataSource);
	}
} 
    
