package cn.zhiu.framework.base.api.core.compoment.loader;

import cn.zhiu.framework.base.api.core.annotation.loader.LoaderService;
import cn.zhiu.framework.base.api.core.annotation.loader.LoaderServiceBatch;
import cn.zhiu.framework.base.api.core.exception.loader.LoaderServiceException;
import cn.zhiu.framework.base.api.core.util.ReflectionUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Loader组件, 通过注解载入对应属性
 */
public class LoaderServiceComponent {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 载入一个对象中所有需要动态加载的内容
     *
     * @param object 要加载内容的对象
     *
     * @throws LoaderServiceException
     */
    public void loadAll(Object object) throws LoaderServiceException {
        if (object == null) {
            return;
        }
        try {
            Class<?> objectClass = object.getClass();

            // 找到所有标记了注解的Field
            Map<Field, LoaderService> fieldAnnotationMap = ReflectionUtils.getFieldsAnnotatedWith(objectClass, LoaderService.class);

            for (Field field : fieldAnnotationMap.keySet()) {
                // 取到注解对象
                LoaderService annotation = fieldAnnotationMap.get(field);

                if (!annotation.extension().equals(ILoaderServiceExtension.class)) {
                    // 包含非默认扩展
                    Object extension = this.getBeanQuietly(annotation.extension());
                    if (extension != null) {
                        // 取到了扩展, 逐个类型处理
                        if (extension instanceof ILoaderServiceGroupByExtension) {
                            // 分组扩展
                            ILoaderServiceGroupByExtension groupByExtension = (ILoaderServiceGroupByExtension) extension;
                            if (!groupByExtension.isMatchGroup(object, field.getName())) {
                                // 不符合分组条件, 跳过处理
                                continue;
                            }
                        }
                    }
                }

                Object invokeResult = invokeLoad(object, objectClass, annotation);

                PropertyUtils.setProperty(object, field.getName(), invokeResult);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LoaderServiceException(e);
        }
    }

    /**
     * 载入一个对象中指定需要动态加载的内容
     *
     * @param object   要加载内容的对象
     * @param property 要加载内容的属性名
     *
     * @throws LoaderServiceException
     */
    public void load(Object object, String property) throws LoaderServiceException {
        if (object == null) {
            return;
        }
        try {
            Class<?> objectClass = object.getClass();

            Field field = objectClass.getDeclaredField(property);

            // 取到注解对象
            LoaderService annotation = field.getDeclaredAnnotation(LoaderService.class);

            if (!annotation.extension().equals(ILoaderServiceExtension.class)) {
                // 包含非默认扩展
                Object extension = this.getBeanQuietly(annotation.extension());
                if (extension != null) {
                    // 取到了扩展, 逐个类型处理
                    if (extension instanceof ILoaderServiceGroupByExtension) {
                        // 分组扩展
                        ILoaderServiceGroupByExtension groupByExtension = (ILoaderServiceGroupByExtension) extension;
                        if (!groupByExtension.isMatchGroup(object, field.getName())) {
                            // 不符合分组条件, 跳过处理
                            return;
                        }
                    }
                }
            }

            Object invokeResult = invokeLoad(object, objectClass, annotation);

            PropertyUtils.setProperty(object, field.getName(), invokeResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LoaderServiceException(e);
        }
    }

    protected Object invokeLoad(Object object, Class<?> objectClass, LoaderService annotation) throws Exception {
        Class<?> serviceClass = annotation.service();

        // 通过Spring Context拿到Service对象
        Object serviceObject = applicationContext.getBean(serviceClass);

        // 通过属性名称反射拿到对应的Field的类型和值
        Class[] methodParameterTypes = new Class[annotation.attributes().length];
        Object[] methodParameters = new Object[annotation.attributes().length];
        for (int i = 0; i < annotation.attributes().length; i++) {
            String attribute = annotation.attributes()[i];
            Field attributeField = objectClass.getDeclaredField(attribute);

            methodParameterTypes[i] = attributeField.getType();
            methodParameters[i] = PropertyUtils.getProperty(object, attribute);
        }

        // 反射调用对应Service的指定方法, 并将返回值set到原Field
        Method method = serviceClass.getMethod(annotation.method(), methodParameterTypes);


        return method.invoke(serviceObject, methodParameters);
    }

    /**
     * 载入一个对象中所有需要动态加载的内容
     *
     * @param iterable    要加载内容的对象集
     * @param objectClass 要加载内容的对象的类型描述
     * @param <T>         单一对象的类型
     *
     * @throws LoaderServiceException
     */
    public <T> void loadAllBatch(Iterable<T> iterable, Class<T> objectClass) throws LoaderServiceException {
        if (iterable == null) {
            return;
        }
        try {
            // 找到所有标记了注解的Field
            Map<Field, LoaderServiceBatch> fieldAnnotationMap = ReflectionUtils.getFieldsAnnotatedWith(objectClass, LoaderServiceBatch.class);

            for (Field field : fieldAnnotationMap.keySet()) {
                // 取到注解对象
                LoaderServiceBatch annotation = fieldAnnotationMap.get(field);

                this.loadBatch(iterable, objectClass, field, annotation);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LoaderServiceException(e);
        }
    }

    /**
     * 载入一个对象中所有需要动态加载的内容
     *
     * @param iterable    要加载内容的对象集
     * @param objectClass 要加载内容的对象的类型描述
     * @param property    要加载内容的属性名
     * @param <T>         单一对象的类型
     *
     * @throws LoaderServiceException
     */
    public <T> void loadBatch(Iterable<T> iterable, Class<T> objectClass, String property) throws LoaderServiceException {
        if (iterable == null) {
            return;
        }
        try {
            Field field = objectClass.getDeclaredField(property);

            // 取到注解对象
            LoaderServiceBatch annotation = field.getDeclaredAnnotation(LoaderServiceBatch.class);

            this.loadBatch(iterable, objectClass, field, annotation);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LoaderServiceException(e);
        }
    }

    protected <T> void loadBatch(Iterable<T> iterable, Class<T> objectClass, Field field, LoaderServiceBatch annotation) throws Exception {
        String attribute = annotation.attribute();
        //Field attributeField = objectClass.getDeclaredField(attribute);

        Object extension = null;
        if (!annotation.extension().equals(ILoaderServiceExtension.class)) {
            // 包含非默认扩展
            extension = this.getBeanQuietly(annotation.extension());
        }

        List<Object> attributeValueList = new ArrayList<>();

        for (T object : iterable) {
            if (extension != null) {
                // 取到了扩展, 逐个类型处理
                if (extension instanceof ILoaderServiceGroupByExtension) {
                    // 分组扩展
                    ILoaderServiceGroupByExtension groupByExtension = (ILoaderServiceGroupByExtension) extension;
                    if (!groupByExtension.isMatchGroup(object, field.getName())) {
                        // 不符合分组条件, 跳过处理
                        continue;
                    }
                }
            }
            attributeValueList.add(PropertyUtils.getProperty(object, attribute));
        }

        Class<?> serviceClass = annotation.service();

        // 通过Spring Context拿到Service对象
        Object serviceObject = applicationContext.getBean(serviceClass);

        // 通过属性名称反射拿到对应的Field的类型和值
        Class[] methodParameterTypes = new Class[]{Iterable.class};
        Object[] methodParameters = new Object[]{attributeValueList};

        // 反射调用对应Service的指定方法, 并将返回值set到原Field
        Method method = serviceClass.getMethod(annotation.method(), methodParameterTypes);

        Object invokeResult = method.invoke(serviceObject, methodParameters);

        Map<?, ?> resultMap = (Map<?, ?>) invokeResult;

        for (T object : iterable) {
            PropertyUtils.setProperty(object, field.getName(), resultMap.get(PropertyUtils.getProperty(object, attribute)));
        }
    }

    protected <T> T getBeanQuietly(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (BeansException e) {
            logger.error("获取Bean出错, class={}, exception={}", clazz, e.getMessage());
            //logger.error(e.getMessage(), e);
            return null;
        }
    }
}
