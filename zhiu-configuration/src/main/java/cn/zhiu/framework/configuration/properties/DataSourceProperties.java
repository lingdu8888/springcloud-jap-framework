package cn.zhiu.framework.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceProperties {
    
    @Value("${jdbc.driverClassName:}")
    private String jdbcDriverClassName;

    @Value("${jdbc.url.prefix:}")
    private String jdbcUrlPrefix;

    @Value("${jdbc.url.db:}")
    private String jdbcUrlDb;

    @Value("${jdbc.url.param:}")
    private String jdbcUrlParam;

    @Value("${jdbc.username:}")
    private String jdbcUsername;

    @Value("${jdbc.password:}")
    private String jdbcPassword;

    @Value("${jdbc.maxActive:0}")
    private int jdbcMaxActive;


    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getJdbcUrlPrefix() {
        return jdbcUrlPrefix;
    }

    public void setJdbcUrlPrefix(String jdbcUrlPrefix) {
        this.jdbcUrlPrefix = jdbcUrlPrefix;
    }

    public String getJdbcUrlDb() {
        return jdbcUrlDb;
    }

    public void setJdbcUrlDb(String jdbcUrlDb) {
        this.jdbcUrlDb = jdbcUrlDb;
    }

    public String getJdbcUrlParam() {
        return jdbcUrlParam;
    }

    public void setJdbcUrlParam(String jdbcUrlParam) {
        this.jdbcUrlParam = jdbcUrlParam;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public int getJdbcMaxActive() {
        return jdbcMaxActive;
    }

    public void setJdbcMaxActive(int jdbcMaxActive) {
        this.jdbcMaxActive = jdbcMaxActive;
    }
}
