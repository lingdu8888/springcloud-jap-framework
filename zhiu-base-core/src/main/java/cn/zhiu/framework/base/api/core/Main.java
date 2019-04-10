package cn.zhiu.framework.base.api.core;

import cn.zhiu.framework.bean.core.dao.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author zhuzz
 * @time 2019 /04/02 17:35:40
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"cn.zhiu.framework.configuration", "cn.zhiu.framework.base.api.core", "cn.zhiu.base.api", "cn.zhiu.framework.bean.core.dao"})
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class, basePackages = "cn.zhiu.base.api.service")
//@EntityScan("cn.zhiu.bean")
@ImportResource("classpath:META-INF/spring/*.xml")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
