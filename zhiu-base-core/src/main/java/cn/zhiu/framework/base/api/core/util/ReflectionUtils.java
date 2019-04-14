package cn.zhiu.framework.base.api.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Reflection utils.
 *
 * @author zhuzz
 * @time 2019 /04/02 15:05:35
 */
public class ReflectionUtils {

    public static <E, T extends Annotation> T getAnnotatedClazzAnnotatedWith(Class<E> clazz, Class<T> annotationType) {
        T annotation = clazz.getAnnotation(annotationType);
        return annotation;
    }

    /**
     * 获取一个类中包含指定注解类型的字段集合
     */
    public static <E, T extends Annotation> Map<Field, T> getFieldsAnnotatedWith(Class<E> clazz, Class<T> annotationType) {
        Field[] fields = clazz.getDeclaredFields();
        Map<Field, T> fieldAnnotationMap = new HashMap<>();
        for (Field field : fields) {
            T annotation = field.getDeclaredAnnotation(annotationType);
            if (annotation != null) {
                fieldAnnotationMap.put(field, annotation);
            }
        }
        return fieldAnnotationMap;
    }

    /**
     * 获取一个类中的指定方法
     *
     * @param clazz
     * @param name
     * @param parameterTypes
     *
     * @return
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) throws SecurityException, NoSuchMethodException {
        Method m;
        try {
            m = clazz.getDeclaredMethod(name, parameterTypes);
        } catch (SecurityException e) {
            m = null;
            throw e;
        } catch (NoSuchMethodException e) {
            m = null;
        }
        if (m == null) {
            if (clazz.equals(Object.class)) {
                throw new NoSuchMethodException("Method not found in any super class.");
            }
            return getMethod(clazz.getSuperclass(), name, parameterTypes);
        }
        return m;
    }
    
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> supperClass = obj
                .getClass(); supperClass != Object.class; supperClass = supperClass
                .getSuperclass()) {
            try {
                return supperClass.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        }
        return null;
    }
    public static void setValueByFieldName(Object obj, String fieldName,
                                           Object value)
            throws IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }
}
