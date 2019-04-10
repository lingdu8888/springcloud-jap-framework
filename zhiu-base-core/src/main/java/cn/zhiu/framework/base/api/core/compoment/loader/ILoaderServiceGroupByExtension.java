package cn.zhiu.framework.base.api.core.compoment.loader;

/**
 * LoaderService扩展分组载入接口
 *
 * @param <T> the type parameter
 *
 * @author zhuzz
 * @time 2019 /04/02 15:08:57
 */
public interface ILoaderServiceGroupByExtension<T> extends ILoaderServiceExtension {

    /**
     * 获取分组属性名称
     *
     * @return 对象的属性名 group attribute name
     *
     * @author zhuzz
     * @time 2019 /04/02 15:08:57
     */
    String getGroupAttributeName();

    /**
     * 对应属性执行载入时是否满足当前分组
     *
     * @param object   要载入数据的原始对象
     * @param property 要执行载入的属性名称
     *
     * @return 如果满足分组条件要执行载入则返回true, 否则返回false
     *
     * @author zhuzz
     * @time 2019 /04/02 15:08:57
     */
    boolean isMatchGroup(Object object, String property);

    /**
     * Gets group attribute value.
     *
     * @param object 要载入数据的原始对象
     *
     * @return 返回分组属性的值 group attribute value
     *
     * @author zhuzz
     * @time 2019 /04/02 15:08:57
     */
    T getGroupAttributeValue(Object object);
}
