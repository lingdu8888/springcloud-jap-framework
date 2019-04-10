//package cn.zhiu.framework.configuration.config;
//
//import cn.zhiu.framework.configuration.properties.DataSourceProperties;
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//
///**
// * The type Data source config.
// *
// * @author zhuzz
// * @time 2019 /04/09 14:47:30
// */
//@Configuration
//public class DataSourceConfig {
//
//
//    @Autowired
//    private DataSourceProperties dataSourceProperties;
//
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        if (!StringUtils.isEmpty(dataSourceProperties.getJdbcUrlPrefix())) {
//            dataSource.setUrl(dataSourceProperties.getJdbcUrlPrefix() + dataSourceProperties.getJdbcUrlDb() + dataSourceProperties.getJdbcUrlParam());
//            dataSource.setUsername(dataSourceProperties.getJdbcUsername());
//            dataSource.setPassword(dataSourceProperties.getJdbcPassword());
//            dataSource.setDriverClassName(dataSourceProperties.getJdbcDriverClassName());
//            dataSource.setValidationQuery("select 1");
//            dataSource.setMaxActive(dataSourceProperties.getJdbcMaxActive());
//        }
//        return dataSource;
//    }
//
////    @Bean
////    public EntityManagerFactory entityManagerFactory() {
////        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
////        entityManagerFactoryBean.setDataSource(dataSource());
////        LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
////        entityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver);
////        return entityManagerFactoryBean.getNativeEntityManagerFactory();
////    }
//
//}
