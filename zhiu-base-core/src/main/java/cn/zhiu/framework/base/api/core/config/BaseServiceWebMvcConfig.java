package cn.zhiu.framework.base.api.core.config;

import cn.zhiu.framework.base.api.core.annotation.request.HandlerMethodBodyArgumentResolver;
import cn.zhiu.framework.base.api.core.interceptor.api.BaseServiceRequestHeaderApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class BaseServiceWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerMethodBodyArgumentResolver());
    }

    @Autowired
    private BaseServiceRequestHeaderApiInterceptor requestHeaderApiInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHeaderApiInterceptor);
    }

}
