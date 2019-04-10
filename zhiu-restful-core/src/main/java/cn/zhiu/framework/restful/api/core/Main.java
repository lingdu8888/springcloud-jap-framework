package cn.zhiu.framework.restful.api.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动入口
 */

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
        , org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
@ComponentScan({"cn.zhiu.framework.configuration", "cn.zhiu.base.api", "cn.zhiu.restful.api", "cn.zhiu.framework.restful.api.core", "cn.zhiu.framework.base.api.core"})
@EnableDiscoveryClient
@EnableFeignClients("cn.zhiu.base.api")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Main {


    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }
}
