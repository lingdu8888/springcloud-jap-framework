package cn.zhiu.framework.base.api.core.compoment.loader;

import cn.zhiu.framework.base.api.core.exception.loader.LoaderServiceException;
import org.apache.commons.beanutils.PropertyUtils;


/**
 * Created by wuzhao on 2016/12/29.
 */
public abstract class AbstractLoaderServiceGroupByExtension<T> extends AbstractLoaderServiceExtension implements ILoaderServiceGroupByExtension<T> {
    @SuppressWarnings("unchecked")
    @Override
    public T getGroupAttributeValue(Object object) {
        try {
            return (T) PropertyUtils.getProperty(object, this.getGroupAttributeName());
        } catch (Exception e) {
            logger.error("读取分组属性的值出错", e);
            throw new LoaderServiceException("读取分组属性的值出错", e);
        }
    }

    @Override
    public boolean isMatchGroup(Object object, String property) {
        return false;
    }
}
