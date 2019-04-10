package cn.zhiu.framework.base.api.core.annotation.loader;


import cn.zhiu.framework.base.api.core.compoment.loader.ILoaderServiceExtension;

import java.lang.annotation.*;

/**
 * 指明某个类通过指定服务批量载入服务
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoaderServiceBatch {

    /**
     * @return service类型
     */
    Class<?> service();

    /**
     * @return service接口批量载入方法名称
     */
    String method();

    /**
     * @return 用于调用批量载入方法的属性名称
     */
    String attribute();

    /**
     * @return service类型
     */
    Class<? extends ILoaderServiceExtension> extension() default ILoaderServiceExtension.class;
}
