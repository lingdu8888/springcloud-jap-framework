package cn.zhiu.framework.base.api.core.annotation.loader;


import cn.zhiu.framework.base.api.core.compoment.loader.ILoaderServiceExtension;

import java.lang.annotation.*;

/**
 * 指明某个类通过指定服务载入服务
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoaderService {
    /**
     * @return service类型
     */
    Class<?> service();

    /**
     * @return service接口方法名称
     */
    String method();

    /**
     * @return 用于调用方法的属性名称
     */
    String[] attributes();

    /**
     * @return service类型
     */
    Class<? extends ILoaderServiceExtension> extension() default ILoaderServiceExtension.class;
}
