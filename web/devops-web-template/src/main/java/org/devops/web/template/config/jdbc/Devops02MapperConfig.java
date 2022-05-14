package org.devops.web.template.config.jdbc;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.devops.core.model.configuration.EnableModel;
import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.datasource.DynamicDataSource;
import org.devops.web.template.config.datasource.DevopsDataSourceType;
import org.devops.web.template.model.demo02.Demo02Model;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@MapperScan(value = {"org.devops.web.template.dao.*.mapper"}, sqlSessionFactoryRef = "devops02SqlSessionFactory")
@EnableModel
public class Devops02MapperConfig {


    //<editor-fold desc="从配置文件读取数据源信息">

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.devops02")
    public DataSource devops02DataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filterList = new ArrayList<Filter>();
        filterList.add(devops02WallFilter());
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;
    }

    @Bean
    public WallFilter devops02WallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(devops02WallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig devops02WallConfig() {
        WallConfig config = new WallConfig();
        //允许一次执行多条语句
        config.setMultiStatementAllow(true);
        //允许非基本语句的其他语句
        config.setNoneBaseStatementAllow(true);
        return config;
    }
    //</editor-fold>

    //<editor-fold desc="构造spring数据源">

    @Bean
    @DependsOn({"devops02DataSource"})
    public DynamicDataSource devops02DynamicDataSource(
            @Qualifier("devops02DataSource") DataSource devops02DataSource) {

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DevopsDataSourceType.MASTER, devops02DataSource);
        targetDataSources.put(DevopsDataSourceType.SLAVE1, devops02DataSource);
        DynamicDataSource devopsDynamicDataSource = new DynamicDataSource(DevopsDataSourceType.DEVOPS_02);
        // 该方法是AbstractRoutingDataSource的方法
        devopsDynamicDataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为myTestDbDataSource
        devopsDynamicDataSource.setDefaultTargetDataSource(devops02DataSource);

        return devopsDynamicDataSource;
    }

    /**
     * mybatis扫描
     * @param devops02DynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory devops02SqlSessionFactory(@Qualifier("devops02DynamicDataSource") DataSource devops02DynamicDataSource) throws Exception {
        SqlSessionFactoryBean devops02SqlSessionFactory = new SqlSessionFactoryBean();
        devops02SqlSessionFactory.setDataSource(devops02DynamicDataSource);
        DataSourceMapper.setPackage(Demo02Model.class.getPackage().getName(), DevopsDataSourceType.DEVOPS_02);
        return devops02SqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate devops02SqlSessionTemplate(
            @Qualifier("devops02SqlSessionFactory") SqlSessionFactory devops02SqlSessionFactory) {
        return new SqlSessionTemplate(devops02SqlSessionFactory);
    }

    /**
     * 事务管理
     * @param devops02DynamicDataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager devops02TransactionManager(@Qualifier("devops02DynamicDataSource") DynamicDataSource devops02DynamicDataSource) {
        return new DataSourceTransactionManager(devops02DynamicDataSource);
    }
    //</editor-fold>

}
    
