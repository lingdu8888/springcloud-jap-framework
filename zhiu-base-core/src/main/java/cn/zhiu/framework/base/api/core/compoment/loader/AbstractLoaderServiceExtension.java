package cn.zhiu.framework.base.api.core.compoment.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuzhao on 2016/12/29.
 */
public abstract class AbstractLoaderServiceExtension implements ILoaderServiceExtension {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());
}
