package org.devops.web.template.config.jdbc;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.devops.core.model.configuration.EnableModel;
import org.devops.core.utils.datasource.DataSourceAspect;
import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.datasource.DynamicDataSource;
import org.devops.web.template.config.datasource.DevopsDataSourceType;
import org.devops.web.template.model.demo01.Demo01Model;
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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@MapperScan(value = {"org.devops.web.template.dao.*.mapper"}, sqlSessionFactoryRef = "devops01SqlSessionFactory")
@EnableModel
public class Devops01MapperConfig {


    //<editor-fold desc="从配置文件读取数据源信息">

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.devops01")
    public DataSource devops01DataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filterList = new ArrayList<Filter>();
        filterList.add(devops01WallFilter());
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;
    }

    @Bean
    public WallFilter devops01WallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(devops01WallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig devops01WallConfig() {
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
    @Primary
    @DependsOn({"devops01DataSource"})
    public DynamicDataSource devops01DynamicDataSource(
            @Qualifier("devops01DataSource") DataSource devops01DataSource) {

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put(DevopsDataSourceType.MASTER, devops01DataSource);
        targetDataSources.put(DevopsDataSourceType.SLAVE1, devops01DataSource);
        DynamicDataSource devopsDynamicDataSource = new DynamicDataSource(DevopsDataSourceType.DEVOPS_01);
        // 该方法是AbstractRoutingDataSource的方法
        devopsDynamicDataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为myTestDbDataSource
        devopsDynamicDataSource.setDefaultTargetDataSource(devops01DataSource);

        return devopsDynamicDataSource;
    }

    /**
     * mybatis扫描
     * @param devops01DynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory devops01SqlSessionFactory(@Qualifier("devops01DynamicDataSource") DataSource devops01DynamicDataSource) throws Exception {
        SqlSessionFactoryBean devops01SqlSessionFactory = new SqlSessionFactoryBean();
        devops01SqlSessionFactory.setDataSource(devops01DynamicDataSource);
        DataSourceMapper.setPackage(Demo01Model.class.getPackage().getName(), DevopsDataSourceType.DEVOPS_01);
        return devops01SqlSessionFactory.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate devops01SqlSessionTemplate(
            @Qualifier("devops01SqlSessionFactory") SqlSessionFactory devops01SqlSessionFactory) {
        return new SqlSessionTemplate(devops01SqlSessionFactory);
    }

    /**
     * 事务管理
     * @param devops01DynamicDataSource
     * @return
     */
    @Bean
    @Primary
    public PlatformTransactionManager devops01TransactionManager(@Qualifier("devops01DynamicDataSource") DynamicDataSource devops01DynamicDataSource) {
        return new DataSourceTransactionManager(devops01DynamicDataSource);
    }
    //</editor-fold>

    //<editor-fold desc="数据源切换支持切面">

    /**
     * 选择数据源，配合 @Datasource 一起使用
     * @return
     */
    @Bean
    public DataSourceAspect dataSourceAspect() {
        return new DataSourceAspect();
    }
    //</editor-fold>

    //<editor-fold desc="durid数据库连接监控">
    /**
     * 1、配置一个管理后台的Servlet
     * 访问 http://{域名}:{端口}/druid 即可打开管理页面
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        //默认就是允许所有访问
        initParams.put("allow", "");
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 2、配置一个web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
    //</editor-fold>
} 
    
